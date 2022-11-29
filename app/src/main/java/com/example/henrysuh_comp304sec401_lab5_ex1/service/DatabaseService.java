package com.example.henrysuh_comp304sec401_lab5_ex1.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.henrysuh_comp304sec401_lab5_ex1.dto.RestaurantDto;
import com.example.henrysuh_comp304sec401_lab5_ex1.db.DatabaseHelper;
import com.example.henrysuh_comp304sec401_lab5_ex1.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    public List<String> getCategories(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct category FROM toronto_restaurant", null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext())
        {
            list.add(cursor.getString(0));
        }

        cursor.close();
        dbHelper.close();

        return list;
    }

    public List<RestaurantDto> getRestaurants(Context context, String cuisine) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id, name, pricerange, latitude, longitude" +
                " FROM toronto_restaurant" +
                " WHERE category=?", new String[]{cuisine});
        List<RestaurantDto> list = new ArrayList<>();

        while (cursor.moveToNext())
        {

            list.add(new RestaurantDto(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4)
            ));
        }

        cursor.close();
        dbHelper.close();

        return list;
    }

    public Restaurant getRestaurantDetail(Context context, int restaurantId) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT *" +
                " FROM toronto_restaurant" +
                " WHERE id=?", new String[]{String.valueOf(restaurantId)});

        Restaurant restaurant = null;
        if(cursor.moveToFirst()){
            restaurant = new Restaurant(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getDouble(6),
                    cursor.getDouble(7)
            );
        }

        cursor.close();
        dbHelper.close();

        return restaurant;
    }
}
