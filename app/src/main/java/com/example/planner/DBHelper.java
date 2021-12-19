package com.example.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_task";
    public static final String table_user = "tb_user";
    public static final String row_userid = "user_id";
    public static final String row_username = "username";
    public static final String row_password = "password";
    public static final String row_name = "name";
    public static final String row_umur = "umur";
    public static final String row_gender = "gender";
    public static final String table_daily = "tb_daily";
    public static final String row_dailyId = "row_dailyId";
    public static final String row_dailyPlan = "row_dailyPlan";
    public static final String row_startTime = "row_startTime";
    public static final String row_endTime = "row_endTime";
    public static final String row_date = "row_date";
    public SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE " + table_user + "("
                + row_userid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + row_username + " TEXT,"
                + row_password + " TEXT,"
                + row_name + " TEXT,"
                + row_umur + " TEXT,"
                + row_gender + " TEXT)";
        database.execSQL(query);
        String queryDaily = "CREATE TABLE " + table_daily + "("
                + row_dailyId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
              //  + row_userid + " INTEGER, "
                + row_dailyPlan + " TEXT, "
                + row_startTime + " TIME,"
                + row_endTime + " TIME,"
                + row_date + " DATE)";

        database.execSQL(queryDaily);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + table_user);
        onCreate(database);
        database.execSQL("DROP TABLE IF EXISTS " + table_daily);
        onCreate(database);
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {row_userid};
        SQLiteDatabase database = getReadableDatabase();
        String selection = row_username + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(table_user, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();

        return count > 0;
    }

    public void insertUser(ContentValues values) {
        database.insert(table_user, null, values);
    }

    public void insertDaily(String tambahdailyplan, String tambahstarttime, String tambahendtime) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(row_dailyPlan, tambahdailyplan);
        values.put(row_startTime, tambahstarttime);
        values.put(row_endTime, tambahendtime);


        db.insert(table_daily, null, values);

        db.close();

    }

    public Cursor selectUser(String user){

        SQLiteDatabase db = getReadableDatabase();

        String query = "select name from "+table_user+" WHERE "+row_username+"=?";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }

    public ArrayList<DailyPlaner> readDaily() {
        //memanggil database untuk bisa dibaca
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + table_daily, null);

        ArrayList<DailyPlaner> dailyPlanerArrayList = new ArrayList<DailyPlaner>();

        //menambhakan data ke array (per baris)
        if (cursor.moveToFirst()) {
            do {
                dailyPlanerArrayList.add(new DailyPlaner(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dailyPlanerArrayList;

    }
}
