package com.example.city_portal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM quotes", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public  Cursor getBeautyData(){
        Cursor cursor = database.rawQuery("SELECT * FROM beauty", null);
        return cursor;
    }
    public  Cursor getEducationData(){
        Cursor cursor = database.rawQuery("SELECT * FROM education", null);
        return cursor;
    }
    public  Cursor getHospitalData(){
        Cursor cursor = database.rawQuery("SELECT * FROM health", null);
        return cursor;
    }
    public  Cursor getRestaurantData(){
        Cursor cursor = database.rawQuery("SELECT * FROM restraunt", null);
        return cursor;
    }
    public Cursor getSelectedHospitalDoctorsData(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM doctor_table WHERE ref_id = "+id, null);
        return cursor;
    }
    public Cursor getSelectedBeautyServicesData(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM restraunt WHERE ref_id = "+id, null);
        return cursor;
    }
    public Cursor getSelectedBeautyData(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM beauty WHERE ID = "+id, null);
        return cursor;
    }
    public Cursor getSelectedEducationData(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM education WHERE ID = "+id, null);
        return cursor;
    }
    public Cursor getSelectedHospitalData(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM health WHERE ID = "+id, null);
        return cursor;
    }
    public Cursor getSelectedRestaurantData(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM restraunt WHERE ID = "+id, null);
        return cursor;
    }
}
