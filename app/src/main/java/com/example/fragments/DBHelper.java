package com.example.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ordersDB";
    public static final String TABLE_ORDERS = "orders";

    public static final String KEY_ID = "_id";
    public static final String KEY_PRICE = "price";
    public static final String KEY_NAMES = "names";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_ORDERS + "(" + KEY_ID + " integer primary key," +
                KEY_NAMES + " text, " + KEY_PRICE + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_ORDERS);
        onCreate(sqLiteDatabase);
    }
}
