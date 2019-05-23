package com.example.city_portal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cityportal.db";
    public static final String TABLE_NAME = "loginn_table";
    public static final String TABLE_NAME2 = "receipt_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MOBILE_NO";
    public static final String COL_4 = "USERNAME";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_12 = "TITLE";
    public static final String COL_13 = "SUB_TITLE";
    public static final String COL_14 = "DATE";
    public static final String COL_15 = "TIME";
    public static final String COL_16 = "NAME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MOBILE_NO TEXT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, SUB_TITLE TEXT, DATE TEXT, TIME TEXT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);

    }

    public boolean insertData(String name, String mobileNo, String username, String password){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,mobileNo);
        contentValues.put(COL_4,username);
        contentValues.put(COL_5,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertReceiptData(String title, String sub_title, String date, String time, String appName){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12,title);
        contentValues.put(COL_13,sub_title);
        contentValues.put(COL_14,date);
        contentValues.put(COL_15,time);
        contentValues.put(COL_16,appName);
        long result = db.insert(TABLE_NAME2,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean loginCheck(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE USERNAME = '"+username+"' AND PASSWORD = '"+password+"'",null);
        if(res.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public Cursor getReceiptAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return res;
    }

    public Cursor getSelectedData(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID="+i,null);
        return cursor;
    }
}
