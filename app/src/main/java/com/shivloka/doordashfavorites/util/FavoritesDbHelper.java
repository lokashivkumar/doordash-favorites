package com.shivloka.doordashfavorites.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shivloka.doordashfavorites.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiv.loka on 12/24/16.
 */

public class FavoritesDbHelper extends SQLiteOpenHelper {

    //TODO: Write tests for get and set.
    //TODO: Clean up.

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Favorites.db";

    private static final String TABLE_FAVORITES = "favorites";
    private static final String RESTAURANT_ID = "id";
    private static final String RESTAURANT_NAME = "name";
    private static final String RESTAURANT_DELIVERY_TIME = "deliverytime";
    private static final String RESTAURANT_DELIVERY_FEE = "deliveryfee";
    private static final String CUISINE_TYPE = "cuisinetype";

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "(" +
                RESTAURANT_ID + " INTEGER PRIMARY KEY, " +
                RESTAURANT_NAME + " TEXT, " +
                CUISINE_TYPE + " TEXT, " +
                RESTAURANT_DELIVERY_TIME + " TEXT, " +
                RESTAURANT_DELIVERY_FEE + " REAL )";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favorites");
        this.onCreate(db);
    }

    public void addFavorite(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID, restaurant.getId());
        values.put(RESTAURANT_NAME, restaurant.getName());
        values.put(CUISINE_TYPE, restaurant.getCuisineType());
        values.put(RESTAURANT_DELIVERY_TIME, restaurant.getDeliveryTime());
        values.put(RESTAURANT_DELIVERY_FEE, restaurant.getDeliveryFee());
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public void removeFavorite(Restaurant restaurant) {
        int restaurantId = restaurant.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_FAVORITES + " WHERE ID = " + restaurantId;
        db.execSQL(deleteQuery);
    }

    public List getFavorites() {
        List restaurants = new ArrayList();

        // select restaurant query
        String query = "SELECT  * FROM " + TABLE_FAVORITES;

        // get reference of the Restaurant database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Restaurant restaurant;
        if (cursor.moveToFirst()) {
            do {
                restaurant = new Restaurant();
                restaurant.setId(Integer.parseInt(cursor.getString(0)));
                restaurant.setName(cursor.getString(1));
                restaurant.setCuisineType(cursor.getString(2));
                restaurant.setDeliveryTime(cursor.getString(3));
                restaurant.setDeliveryFee(cursor.getDouble(4));
                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed())
            cursor.close();
        return restaurants;
    }
}
