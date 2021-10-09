package com.example.it21771_assignment2;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocationHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "LOCATIONS_DB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "LOCATIONS";
    public static final String KEY_ID = "_ID";
    public static final String KEY_TIMESTAMP = "_TIMESTAMP";
    public static final String KEY_LAT = "LAT";
    public static final String KEY_LON = "LON";
    public static final String SQL_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" ("+
            KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_TIMESTAMP+" TEXT, "+
            KEY_LAT+" TEXT,  "+ KEY_LON+" TEXT"+
            ");";


    public LocationHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
