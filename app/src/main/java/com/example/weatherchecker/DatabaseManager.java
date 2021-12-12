package com.example.weatherchecker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Weather_Checker.db";
    private static final int DATABASE_VERSION = 2;
    Context context;
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table City ("
                + "    idName integer primary key autoincrement,"
                + "    name text not null"
                + ")";
        db.execSQL(strSql);
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertCity(String name) {
        name = name.replace("'", "''");
        String strSql = "insert into City (name) values ('"
                + name + "'"+ ")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "insertCity invoked");
    }
    public void deleteCity(String name) {
        name = name.replace("'", "''");
        String strSql = "delete from City where name ="+name;
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "deleteCity invoked");
    }
   public ArrayAdapter<String> readCity() {

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(context,R.layout.support_simple_spinner_dropdown_item);


       String strSql = "select name from City";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
        String ch=cursor.getString(0);
         arrayAdapter.add(ch);
            cursor.moveToNext();
        }
        cursor.close();

        return arrayAdapter;
    }
}
