package com.example.video;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String USER_DB_NAME = "register.db";
    private static final String USER_TABLE_NAME = "users";
    private static final String PLAYLIST_TABLE_NAME = "PLAYLIST_DATA";
    private static final String COL_2 = "PLAYLIST";

    public DBHelper(Context context) {
        super(context, USER_DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + "(username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + PLAYLIST_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYLIST_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUserData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert(USER_TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUser(String username, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ? AND password = ?", new String[]{username, pwd});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean insertPlaylistData(String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, url);
        long result = db.insert(PLAYLIST_TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor getAllPlaylistData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + PLAYLIST_TABLE_NAME, null);
    }
}
