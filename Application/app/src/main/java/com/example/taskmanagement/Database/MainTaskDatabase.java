package com.example.taskmanagement.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MainTaskDatabase extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "taskManagementDatabase";
    public static final String table_name = "Main_Tasks";
    public static final String table_column_ID = "ID";
    public static final String table_column_TITLE = "Title";
    public static final String table_column_DATE = "Date";
    public static final String table_column_HOUR = "Hour";
    public static final String table_column_MINUTE = "Minute";

    public MainTaskDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + table_name + " (" + table_column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + table_column_TITLE + " TEXT, " + table_column_DATE + " VARCHAR, " + table_column_HOUR + " VARCHAR, " + table_column_MINUTE + " VARCHAR)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
