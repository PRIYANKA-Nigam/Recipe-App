package com.example.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
   final static String DBNAME = "mydatabase.db";
    public DBHelper(@Nullable Context context ) {
        super(context,DBNAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders " + "(name text,phone text,price int,image int," +
                "description text,foodname text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP table if exists orders");
onCreate(db);
    }
    public boolean insertOrder(String name,String phone,int price,int image,String desc,String foodname){
        SQLiteDatabase database=getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("price",price);
        cv.put("image",image);
        cv.put("description",desc);
        cv.put("foodname",foodname);
      long id=  database.insert("orders",null,cv);
      if (id<=0){
          return false;
      }else {
          return true;
      }
    }
    public Cursor getOrders(){
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select * from orders",null);
        return cursor;
    }
}
