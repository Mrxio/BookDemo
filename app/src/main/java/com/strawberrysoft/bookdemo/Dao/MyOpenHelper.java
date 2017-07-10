package com.strawberrysoft.bookdemo.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/22.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
    private static MyOpenHelper fdb;
    public MyOpenHelper(Context context, int version) {
        super(context, "bookdemo.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table search_history (_id integer primary key autoincrement, str char(30))");
        sqLiteDatabase.execSQL("create table bookinfo (_id integer primary key autoincrement, type1 text,type2 text,author text,pubdate text,tags text,image text,binding text,translator text,catalog text,pages text,publisher text,isbn13 text,title text,author_intro text,price)");
    }

    public static MyOpenHelper getDBHelper(Context context, int version) {
        if (fdb == null) {
            fdb = new MyOpenHelper(context, version);
        }
        return fdb;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
