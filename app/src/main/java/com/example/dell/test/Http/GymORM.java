package com.example.dell.test.Http;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 龙 on 2017/12/7.
 */

public class GymORM {
    private static final String TAG = "GymORM";

    private static final String TABLE_NAME = "gym";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE gym ( gym_id INTEGER " +
            ", gym_name TEXT, gym_address TEXT, gym_contact TEXT, gym_phone TEXT, gym_picture TEXT)";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void insertGyms(Context context, JSONArray equips) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        database.delete(GymORM.TABLE_NAME, null, null);
        try {
            for (int i = 0; i < equips.length(); i++) {
                GymORM.insertgym(context, equips.getJSONObject(i));
            }
        } catch (Exception e) {
            DialogUtil.showDialog(context, e.getMessage());
        }
    }

    public static void insertgym(Context context, JSONObject equip) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        ContentValues values = gymToContentValues(equip);
        long equipId = database.insert(GymORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted new Post with ID: " + equipId);

    }


    private static ContentValues gymToContentValues(JSONObject equip) {
        ContentValues values = new ContentValues();
        try{
            values.put("gym_id", equip.getInt("equip_id"));
            values.put("gym_name", equip.getString("gym_name"));
            values.put("gym_address", equip.getString("gym_address"));
            values.put("gym_contact", equip.getString("gym_contact"));
            values.put("gym_phone", equip.getString("gym_phone"));
            values.put("gym_picture", equip.getString("gym_picture"));
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }


        return values;
    }


    public static JSONArray getGyms(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + GymORM.TABLE_NAME, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Posts...");
        JSONArray gyms = new JSONArray();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                JSONObject gym = cursorToPost(cursor);
                gyms.put(gym);
                cursor.moveToNext();
            }
            Log.i(TAG, "Posts loaded successfully.");
        }
        cursor.close();
        return gyms;
    }


    public static JSONObject cursorToPost(Cursor cursor) {
        JSONObject gym = new JSONObject();
        try{
            gym.put("gym_id", cursor.getInt(cursor.getColumnIndex("gym_id")))
                    .put("gym_name", cursor.getString(cursor.getColumnIndex("gym_name")))
                    .put("gym_address", cursor.getString(cursor.getColumnIndex("gym_address")))
                    .put("gym_phone", cursor.getString(cursor.getColumnIndex("gym_phone")))
                    .put("gym_contact", cursor.getString(cursor.getColumnIndex("gym_contact")))
                    .put("gym_picture", cursor.getString(cursor.getColumnIndex("gym_picture")));
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }

        return gym;
    }
}
