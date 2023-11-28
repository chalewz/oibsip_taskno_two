package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskerManager";
    private static final String TABLE_NAME = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_TASKNAME = "taskName";
    private static final String KEY_STATUS = "status";
    public DBHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + KEY_ID + "INTEGER PRIMARY KEY, "
                + KEY_TASKNAME+ " TEXT, "
                + KEY_STATUS + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Boolean insertData(int id , String name, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(KEY_TASKNAME,name);
        cv.put(KEY_STATUS,status);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean check_ID(String id){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from TABLE_NAME where KEY_ID = ?",new String[]{id});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else{
            return false;
        }
    }


}


