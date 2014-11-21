package zystudio.cases.dataprocess.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "zystudio.db";
    private static SQLiteOpenHelper sInstance;

    public synchronized static SQLiteOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbOpenHelper(context);
        }
        return sInstance;
    }

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
