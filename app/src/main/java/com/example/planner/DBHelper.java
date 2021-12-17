package com.example.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_task";
    public static final String table_user = "tb_user";
    public static final String row_userid = "user_id";
    public static final String row_username = "username";
    public static final String row_password = "password";
    public static final String row_name = "name";
    public static final String row_umur = "umur";
    public static final String row_gender = "gender";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + table_user);
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

        if (count > 0)
            return true;
        else
            return false;
    }

    public void insertUser(ContentValues values) {
        database.insert(table_user, null, values);
    }
}
