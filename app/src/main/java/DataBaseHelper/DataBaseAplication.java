package DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import Class.ShemaDataBase;

/**
 * Created by DIEGO CASALLAS on 11/03/2016.
 */
public class DataBaseAplication extends SQLiteOpenHelper {

    public DataBaseAplication(Context context){
        super(context, ShemaDataBase.DATABASE_NAME,null,ShemaDataBase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ShemaDataBase.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ShemaDataBase.DROP_TABLE+ShemaDataBase.FeedEntry.TABLE_NAME);
        onCreate(db);

    }
}
