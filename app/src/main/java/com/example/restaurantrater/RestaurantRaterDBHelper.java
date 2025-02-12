package com.example.restaurantrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantRaterDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurantrater.db";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_RESTAURANT = "create table restaurant " +
            "(restaurant_id integer primary key autoincrement, " +
            "restaurant_name text, " +
            "street_address text, " +
            "city text, state text, " +
            "zipcode text);";

    private static final String CREATE_TABLE_DISH = "create table dish " +
            "(dish_id integer primary key autoincrement, " +
            "restaurant_id integer, " +
            "dish_name text, " +
            "dish_type text, " +
            "dish_rating float, " +
            "foreign key (restaurant_id) references restaurant(restaurant_id));";

    public RestaurantRaterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANT);
        db.execSQL(CREATE_TABLE_DISH);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        db.execSQL("DROP TABLE IF EXISTS dish");
        onCreate(db);

    }
}
