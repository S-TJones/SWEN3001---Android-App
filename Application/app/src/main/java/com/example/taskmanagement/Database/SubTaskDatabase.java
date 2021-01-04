package com.example.taskmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.taskmanagement.Classes.SubTask;

import java.util.ArrayList;
import java.util.List;
//where is the btn
public class SubTaskDatabase extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "taskManagementDatabase";
    public static final String table_name = "Sub_Tasks";
    public static final String table_column_MainID = "MainID"; // Remember to add this as primary key
    public static final String table_column_SID = "SID";
    public static final String table_column_NAME = "Name";
    public static final String table_column_STATUS = "Status";

    private SQLiteDatabase db;

    public SubTaskDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + table_name + " (" + table_column_SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + table_column_MainID + " INTEGER, " + table_column_NAME + " TEXT, " + table_column_STATUS + " INTEGER)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop the old table to create a new one
        db.execSQL("DROP TABLE IF EXISTS " + table_name);

        // Create the new table
        onCreate(db);
    }

    public void openDatabase(){
        db = this.getWritableDatabase();
    }

    public void insertSubTask(SubTask task){
        ContentValues cv = new ContentValues();
        cv.put(table_column_NAME, task.getSub_task());
        cv.put(table_column_STATUS, 0);
        db.insert(table_name, null, cv);
    }

    public List<SubTask> getAllTasks(){
        List<SubTask> taskList = new ArrayList<>();

        db.beginTransaction();
        try (Cursor cursor = db.query(table_name, null, null, null, null, null, null, null)) {
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
        db.update(table_name, cv, table_column_SID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateSubTask(int id, String newName){
        ContentValues cv =  new ContentValues();
        cv.put(table_column_NAME, newName);
        db.update(table_name, cv, table_column_SID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteSubTask(int id){
        db.delete(table_column_NAME, table_column_SID + "=?", new String[] {String.valueOf(id)});
    }
}


