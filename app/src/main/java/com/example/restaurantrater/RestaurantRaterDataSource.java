package com.example.restaurantrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RestaurantRaterDataSource {
    private SQLiteDatabase database;

    private RestaurantRaterDBHelper dbHelper;

    public RestaurantRaterDataSource(Context context) {
        dbHelper = new RestaurantRaterDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertRestaurant(Restaurant r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("restaurant_name", r.getRestaurantName());
            initialValues.put("street_address", r.getStreetAddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zipcode", r.getZipcode());

            didSucceed = database.insert("restaurant", null, initialValues) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public boolean insertDish(Dish d) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("dish_name", d.getDishName());
            initialValues.put("dish_type", d.getDishType());
            initialValues.put("dish_rating", d.getDishRating());
            initialValues.put("restaurant_id", d.getRestaurantId());

            didSucceed = database.insert("dish", null, initialValues) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public boolean updateRestaurant(Restaurant r) {
        boolean didSucceed = false;
        try {
            long rowId = (long) r.getRestaurantId();
            ContentValues updateValues = new ContentValues();

            updateValues.put("restaurant_name", r.getRestaurantName());
            updateValues.put("street_address", r.getStreetAddress());
            updateValues.put("city", r.getCity());
            updateValues.put("state", r.getState());
            updateValues.put("zipcode", r.getZipcode());

            didSucceed = database.update("restaurant", updateValues,
                    "restaurant_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public boolean updateDish (Dish d) {
        boolean didSucceed = false;
        try {
            long rowId = (long) d.getDishId();
            ContentValues updateValues = new ContentValues();

            updateValues.put("dish_name", d.getDishName());
            updateValues.put("dish_type", d.getDishType());
            updateValues.put("dish_rating", d.getDishRating());
            updateValues.put("restaurant_id", d.getRestaurantId());

            didSucceed = database.update("dish", updateValues,
                    "dish_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public int getLastRestaurantId() {
        int lastRestaurantId;
        try {
            String query = "SELECT MAX(restaurant_id) from restaurant";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastRestaurantId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastRestaurantId = -1;
        }
        return lastRestaurantId;
    }

    public int getLastDishId() {
        int lastDishId;
        try {
            String query = "SELECT MAX(dish_id) FROM dish";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastDishId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastDishId = -1;
        }
        return lastDishId;
    }

}
