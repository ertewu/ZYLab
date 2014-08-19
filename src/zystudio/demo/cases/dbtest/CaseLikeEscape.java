package zystudio.demo.cases.dbtest;

import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * 这个case用来展示SQLite的转义问题怎么拼,是按照这个弄的
 * http://www.2cto.com/database/201212/175598.html 而且发现'
 * 在insert时需要转义（''而不用自定义escape符）,在like时不需要转义
 * 
 */
public class CaseLikeEscape {
    private static final String TABLE_NAME = "caseEscape";
    private static final String CREATE_TEST_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME
            + " ( "
            + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + " NAME TEXT NOT NULL DEFAULT '',"
            + " VALUE TEXT NOT NULL DEFAULT '' "
 + ")";

    private Context mContext;

    private static CaseLikeEscape sCase;

    public static CaseLikeEscape obtain(Activity act) {
        if (sCase == null) {
            sCase = new CaseLikeEscape();
            sCase.mContext = act;
        }
        return sCase;
    }

    public void work() {
        createTable();
        insertData();
        queryCase("%");
        LogUtil.log("------------------");
        // String str = " '\\%' escape '\' ";
        // String str = "\\%  escape \\";
        String str = "/%";
        queryCase(str);
        LogUtil.log("------------------");
        // 逗号这里发现，在insert的时候必须用''转义，但是在query这里却不用转义..
        String douhao = "'";
        queryCase(douhao);
    }

    private void createTable() {
        SQLiteDatabase db = DbOpenHelper.getInstance(mContext)
                .getWritableDatabase();
        db.execSQL(CREATE_TEST_TABLE_SQL);
        db.close();
    }

    private void insertData() {
        final String INSERT_SQL_1 = "INSERT INTO " + TABLE_NAME
                + " (NAME,VALUE)  VALUES ('%','this is danyin demo')";

        final String INSERT_SQL_2 = "INSERT INTO " + TABLE_NAME
                + " (NAME,VALUE)  VALUES ('wzy','this is wzy')";

        final String INSERT_SQL_3 = "INSERT INTO " + TABLE_NAME
                + " (NAME,VALUE)  VALUES ('qq','this is qq case')";

        final String INSERT_SQL_4 = "INSERT INTO " + TABLE_NAME
                + " (NAME,VALUE)  VALUES ('''','this is douhao case')";

        SQLiteDatabase db = DbOpenHelper.getInstance(mContext)
                .getWritableDatabase();

        db.execSQL(INSERT_SQL_1);
        db.execSQL(INSERT_SQL_2);
        db.execSQL(INSERT_SQL_3);
        db.execSQL(INSERT_SQL_4);
    }

    private void queryCase(String selectionArgs) {
        SQLiteDatabase db = DbOpenHelper.getInstance(mContext)
                .getWritableDatabase();
        // 最后还是这句话管用了，like　后边的　escape
        String selection = "NAME LIKE  ? escape '/' ";
        Cursor cur = db.query(TABLE_NAME, new String[] { "NAME", "VALUE" },
                selection,
                new String[] { selectionArgs }, null, null, null);
        if (cur != null) {
            try {
                if (cur.getCount() <= 0) {
                    LogUtil.log("this query null");
                }

                while (cur.moveToNext()) {
                    String name = cur.getString(0);
                    String value = cur.getString(1);
                    LogUtil.log(name + "|" + value);
                }
            } finally {
                cur.close();
            }
        }
    }

}
