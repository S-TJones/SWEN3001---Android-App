package com.example.taskmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.taskmanagement.Classes.MainTask;
import com.example.taskmanagement.Classes.SubTask;

import java.util.ArrayList;
import java.util.List;

public class MainTaskDatabase extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "taskManagementDatabase";
    public static final String table_name = "Main_Tasks";
    public static final String table_column_ID = "ID";
    public static final String table_column_TITLE = "Title";
    public static final String table_column_DATE = "Date";
    public static final String table_column_TIME = "Time";
    public static final String table_column_HOUR = "Hour";
    public static final String table_column_MINUTE = "Minute";
    public static final String table_name2 = "Sub_Tasks";
    public static final String table_column_MainID = "MainID"; // Remember to add this as primary key
    public static final String table_column_SID = "ID";
    public static final String table_column_NAME = "Name";
    public static final String table_column_STATUS = "Status";

    private SQLiteDatabase db;

    public MainTaskDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTable = "CREATE TABLE " + table_name + " (" + table_column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + table_column_TITLE + " TEXT, " + table_column_DATE + " VARCHAR, " + table_column_HOUR + " VARCHAR, " + table_column_MINUTE + " VARCHAR)";
        String createTable = "CREATE TABLE " + table_name + " (" + table_column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + table_column_TITLE + " TEXT, " + table_column_DATE + " VARCHAR, " + table_column_TIME + " VARCHAR)";
        String createTable2 = "CREATE TABLE " + table_name2 + " (" + table_column_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + table_column_MainID + " INTEGER, " + table_column_NAME + " TEXT, " + table_column_STATUS + " INTEGER)";
        db.execSQL(createTable);
        db.execSQL(createTable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table to create a new one
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + table_name);

        // Create the new table
        onCreate(db);
    }

    // MainTask methods ----------------------------------------------------------------------------

    // Method to add a MainTask
    public boolean addOneMainTask(MainTask mainTask){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Makes key-value pairs
        //contentValues.put(table_column_ID, mainTask.getId());
        contentValues.put(table_column_TITLE, mainTask.getTitle());
        contentValues.put(table_column_DATE, mainTask.getDate());
        contentValues.put(table_column_TIME, mainTask.getTime());

        long insert = database.insert(table_name, null, contentValues);

        if (insert == -1){
            return false;
        } else {
            return true;
        }

    }

    // Deletes a MainTask
    public boolean deleteOneMainTask(MainTask mainTask){
        // Find MainTask in DB, if found delete it and return true
        int mainTaskID = mainTask.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + table_name + " WHERE " + table_column_ID + " = " + mainTaskID;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }

    // Gets all MainTasks
    public List<MainTask> getAllMainTasks(){
        List<MainTask> mainTaskList = new ArrayList<>();

        // query to get from Database
        String queryString = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int taskID = cursor.getInt(cursor.getColumnIndex(table_column_ID));
                String taskName = cursor.getString(cursor.getColumnIndex(table_column_TITLE));
                String taskDate = cursor.getString(cursor.getColumnIndex(table_column_DATE));
                String taskTime = cursor.getString(cursor.getColumnIndex(table_column_TIME));

                MainTask newMainTask = new MainTask(taskID, taskName, taskDate, taskTime);
                mainTaskList.add(newMainTask);

            } while (cursor.moveToNext());

        } else {
            // Do nothing
        }

        // Close the Database and Cursor
        cursor.close();
        db.close();
        return mainTaskList;
    }
    //----------------------------------------------------------------------------------------------

    // SubTask methods -----------------------------------------------------------------------------
    public void openDatabase(){
        db = this.getWritableDatabase();
    }

    public void insertSubTask(SubTask task){
        ContentValues cv = new ContentValues();
        cv.put(table_column_NAME, task.getSub_task());
        cv.put(table_column_STATUS, 0);
        db.insert(table_name2, null, cv);
    }

    public List<SubTask> getAllTasks(){
        List<SubTask> taskList = new ArrayList<>();

        db.beginTransaction();
        try (Cursor cursor = db.query(table_name2, null, null, null, null, null, null, null)) {
            if (cursor.moveToFirst()) {
                do {
                    SubTask task = new SubTask(0, cursor.getInt(cursor.getColumnIndex(table_column_SID)));
                    task.setSub_task(cursor.getString(cursor.getColumnIndex(table_column_NAME)));
                    task.setStatus(cursor.getInt(cursor.getColumnIndex(table_column_STATUS)));
                    taskList.add(task);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv =  new ContentValues();
        cv.put(table_column_STATUS, status);
        db.update(table_name2, cv, table_column_SID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateSubTask(int id, String newName){
        ContentValues cv =  new ContentValues();
        cv.put(table_column_NAME, newName);
        db.update(table_name2, cv, table_column_SID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteSubTask(int id){
        db.delete(table_column_NAME, table_column_SID + "=?", new String[] {String.valueOf(id)});
    }
    //----------------------------------------------------------------------------------------------
}
