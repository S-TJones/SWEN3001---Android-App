package com.example.taskmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MainTaskDatabase extends SQLiteOpenHelper {

    public static final String table_name = "MAIN_ACTIVITY";
    public static final String table_column_ID = "ID";
    public static final String table_column_TITLE = "Title";
    public static final String table_column_DATE = "Date";
    public static final String table_column_TIME = "Time";

    public MainTaskDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "main-activity.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + table_name + " (" + table_column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + table_column_TITLE + " TEXT, " + table_column_DATE + " VARCHAR, " + table_column_TIME + " VARCHAR)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
