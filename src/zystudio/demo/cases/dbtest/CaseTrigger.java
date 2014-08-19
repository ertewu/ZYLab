package zystudio.demo.cases.dbtest;

import zystudio.mylib.utils.LogUtil;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.provider.BaseColumns;

/**
 * 数据库的Trigger的使用方法练习，最早是在小米便签中发现的这一用法，算是挺ＮＢ的<br>
 * 下边是一些解释：<br>
 * http://stackoverflow.com/questions/10639331/new-and-old-trigger-code
 * <P>
 * 目前为止我看到delete ,insert, update的三种操作都能用trigger，用得时机最多的是after，<br>
 * 触发可以作跨表操作,比如delete是table_1的表，trigger的操作却可以在table_2表进行，而且old,new也可以跨表传这个数据
 * </P>
 */
public class CaseTrigger {

    private static CaseTrigger sCase;
    private Activity mActivity;

    public static CaseTrigger obtain(Activity activity) {
        if (sCase == null) {
            sCase = new CaseTrigger();
            sCase.mActivity = activity;
        }
        return sCase;
    }

    public void work() {
        createTablesAndTriggers();
        performTrigger();
    }

    private static final String TABLE_1_NAME = "trigger_table1";
    private static final String TABLE_1_SQL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_1_NAME + " ( " + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + "NAME TEXT NOT NULL DEFAULT '' " + ")";

    private static final String TABLE_2_NAME = "trigger_table2";
    private static final String TABLE_2_SQL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_2_NAME + " ( " + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + " TRIGGER_VALUE TEXT NOT NULL DEFAULT '' " + ")";


    // 这个可以使用，很不错..
    private static final String TRIGGER_SQL = "CREATE TRIGGER insert_value_on_update AFTER insert ON trigger_table1 BEGIN "
            + "insert into trigger_table2(TRIGGER_VALUE)  VALUES(new.NAME);"
            + "  END";

    private void createTablesAndTriggers() {
        SQLiteDatabase db = DbOpenHelper.getInstance(mActivity)
                .getWritableDatabase();
        db.execSQL(TABLE_1_SQL);
        db.execSQL(TABLE_2_SQL);
        db.execSQL(TRIGGER_SQL);
        db.close();
    }

    private static final String INSERT_TABLE1_SQL = "insert into trigger_table1(NAME)  VALUES( 'the insert in table1')";
    private static final String INSERT_TABLE2_SQL = "insert into trigger_table2(TRIGGER_VALUE)  VALUES( 'table2 orginal value')";
    private void performTrigger() {
        SQLiteDatabase db = DbOpenHelper.getInstance(mActivity)
                .getWritableDatabase();
        db.execSQL(INSERT_TABLE2_SQL);
        queryTable2();
        db.execSQL(INSERT_TABLE1_SQL);
        LogUtil.log("----------------------------------------------------");
        (new Handler()).postDelayed(new Runnable() {

            @Override
            public void run() {
                queryTable2();
            }
        }, 1000);
    }

    private void queryTable2() {
        SQLiteDatabase db = DbOpenHelper.getInstance(mActivity)
                .getWritableDatabase();
        Cursor cur = db.query(TABLE_2_NAME, null, null, null, null, null, null);
        int i = 0;
        if (cur != null) {
            try {
                while (cur.moveToNext()) {
                    LogUtil.log(i + ":" + cur.getString(1));
                    i++;
                }
            } finally {
                cur.close();
            }
        }
    }
}
