/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Database to hold the information that was added in the current student activity.
So information wil be saved when they exit the application.
*/
package cpsc4150.allaroundclemson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "classes.db";
    private static final int VERSION = 1;

    public ClassDatabase(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class ClassTable{
        private static final String TABLE = "classes";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_CODE = "code";
        private static final String COL_SECTION = "section";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ClassTable.TABLE + " (" +
                ClassTable.COL_ID + " integer primary key autoincrement, " +
                ClassTable.COL_NAME + " text, " +
                ClassTable.COL_CODE + " text, " +
                ClassTable.COL_SECTION + " integer) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ClassTable.TABLE);
        onCreate(db);
    }

    public long addClass(clemsonClass c){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ClassTable.COL_CODE, c.getClassCode());
        values.put(ClassTable.COL_NAME, c.getName());
        values.put(ClassTable.COL_SECTION, c.getSection());

        return db.insert(ClassTable.TABLE, null, values);
    }

    public void deleteClass(String name, String code){
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(ClassTable.TABLE,
                ClassTable.COL_CODE + " =? AND " + ClassTable.COL_NAME + " =?",
                new String[] {code, name});
    }

    public void popAdapter(clemsonClassAdapter ad){
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from " + ClassTable.TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do{
                String name = cursor.getString(1);
                String code = cursor.getString(2);
                int section = cursor.getInt(3);
                clemsonClass temp = new clemsonClass(name, code, section);
                ad.add(temp);
            }while(cursor.moveToNext());
        }

    }
}
