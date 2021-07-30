package com.example.databaseio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static int database_version = 1;
    private static String database_name = "IOcrud";
    private static String table_name = "UserData";
    private static String key_id = "id";
    private static String key_name = "name";
    private static String key_contact = "contactno";
    SQLiteDatabase db;

    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + table_name + "( " + key_id + " integer primary key autoincrement, " + key_name + " text, " + key_contact + " text )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + table_name);
        onCreate(db);
    }
    public int adddata(Contacts c){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(key_name, c.getName());
        cv.put(key_contact,c.getContactno());
        int status = (int)db.insert(table_name,null,cv);
        return status;
    }
    public ArrayList<String> getdata(){
        ArrayList<String> contactlist = new ArrayList<String>();
        String query = "select * from " + table_name;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                contactlist.add(cursor.getString(0) + "|" + cursor.getString(1) + "|" + cursor.getString(2));
            }while (cursor.moveToNext());
        }
        return contactlist;
    }
    public int deleteDataById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int status = db.delete(table_name,key_id + "=?" , new String[]{String.valueOf(id)});
        db.close();
        return status;
    }
}
