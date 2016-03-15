package DataBaseHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

import Class.ShemaDataBase;


/**
 * Created by DIEGO CASALLAS on 22/02/2016.
 */
public class DaoUserEntity  {

    private SQLiteDatabase database;
    private ContentValues contentValues;

    public DaoUserEntity(SQLiteDatabase db) {
        this.database = db;
        this.contentValues = null;
    }

    public int insertUser(DtoUser dtoUser) throws SQLException {

        contentValues = new ContentValues();
        contentValues.put(ShemaDataBase.USER_NAME, dtoUser.getsName());

       return (int) database.insert(ShemaDataBase.FeedEntry.TABLE_NAME, null, contentValues);

    }

    public ArrayList<DtoUser> consultUser(DtoUser dtoUser) throws SQLException {
        ArrayList<DtoUser> PersonList = new ArrayList<>();
        String  sSQL = ShemaDataBase.SELECT_ALL_PERSON + ShemaDataBase.WHERE + ShemaDataBase.FeedEntry.TABLE_USER[1] + "=" + "'" + dtoUser.getsName() + "'";
        try {
            Cursor personCursor = database.rawQuery(sSQL, null);
            if (personCursor.moveToFirst()) {
                do {
                    DtoUser personaBO = new DtoUser(
                            personCursor.getInt(0),
                            personCursor.getString(1));
                    PersonList.add(personaBO);
                } while (personCursor.moveToNext());
            }
        }
        catch (Exception e)
        {
            Log.e("SQL",e.getMessage());
        }
        return PersonList;
    }
    public int deleteUser(DtoUser dtoUser) throws SQLException {

        return (int) database.delete(ShemaDataBase.FeedEntry.TABLE_NAME, ShemaDataBase.FeedEntry.TABLE_USER[1] + "=" + "'" + dtoUser.getsName() + "'", null);

    }



}
