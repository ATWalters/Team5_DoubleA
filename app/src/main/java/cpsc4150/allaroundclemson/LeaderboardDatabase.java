package cpsc4150.allaroundclemson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LeaderboardDatabase extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "leaderboard.db";
        private static final int VERSION = 1;

        public LeaderboardDatabase(Context context){
            super(context, DATABASE_NAME, null, VERSION);
        }

        private static final class LeaderboardTable{
            private static final String TABLE = "leaderboard";
            private static final String COL_ID = "_id";
            private static final String COL_NAME = "name";
            private static final String COL_SCORE = "score";
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + LeaderboardTable.TABLE + " (" +
                    LeaderboardTable.COL_ID + " integer primary key autoincrement, " +
                    LeaderboardTable.COL_NAME + " text, " +
                    LeaderboardTable.COL_SCORE + " integer) ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + LeaderboardTable.TABLE);
            onCreate(db);
        }

        public long addLeaderboard(leaderboard lb){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(LeaderboardTable.COL_SCORE, lb.getScore());
            values.put(LeaderboardTable.COL_NAME, lb.getUsername());

            return db.insert(LeaderboardTable.TABLE, null, values);
        }

        public void deleteClass(String name, int score){
            SQLiteDatabase db = getWritableDatabase();
            int rowsDeleted = db.delete(LeaderboardTable.TABLE,
                    LeaderboardTable.COL_SCORE + " =? AND " + LeaderboardTable.COL_NAME + " =?",
                    new String[] {String.valueOf(score), name});
        }

        public void popAdapter(leaderboardAdapter ad){
            SQLiteDatabase db = getReadableDatabase();

            String query = "select * from " + LeaderboardTable.TABLE;
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                int i = 0;
                do{
                    String name = cursor.getString(1);
                    int score = cursor.getInt(2);

                    leaderboard temp = new leaderboard(name, score);
                    ad.add(temp);
                    i++;
                }while(cursor.moveToNext() && i < 5);
            }

        }
    }
