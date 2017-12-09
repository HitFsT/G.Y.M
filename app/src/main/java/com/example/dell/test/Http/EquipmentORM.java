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

public class EquipmentORM {
    private static final String TAG = "EquipORM";

    private static final String TABLE_NAME = "equip";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE equip ( equip_id INTEGER " +
            ", equip_user_id INTEGER, equip_gym_id INTEGER, equip_start TEXT, equip_end TEXT, equip_name TEXT)";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void insertEquips(Context context, JSONArray equips) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        database.delete(EquipmentORM.TABLE_NAME, null, null);
        try {
            for (int i = 0; i < equips.length(); i++) {
                EquipmentORM.insertEquip(context, equips.getJSONObject(i));
            }
        } catch (Exception e) {
            DialogUtil.showDialog(context, e.getMessage());
        }
    }

    public static void insertEquip(Context context, JSONObject equip) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        ContentValues values = equipToContentValues(equip);
        long equipId = database.insert(EquipmentORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted new Post with ID: " + equipId);

    }


    private static ContentValues equipToContentValues(JSONObject equip) {
        ContentValues values = new ContentValues();
        try{
            values.put("equip_id", equip.getInt("equip_id"));
            values.put("equip_user_id", equip.getInt("equip_user_id"));
            values.put("equip_gym_id", equip.getInt("equip_gym_id"));
            values.put("equip_start", equip.getString("equip_start"));
            values.put("equip_end", equip.getString("equip_end"));
            values.put("equip_name", equip.getString("equip_name"));
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }


        return values;
    }


    public static JSONArray getEquips(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + EquipmentORM.TABLE_NAME, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Posts...");
        JSONArray equips = new JSONArray();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                JSONObject equip = cursorToPost(cursor);
                equips.put(equip);
                cursor.moveToNext();
            }
            Log.i(TAG, "Posts loaded successfully.");
        }
        cursor.close();
        return equips;
    }


    public static JSONObject cursorToPost(Cursor cursor) {
        JSONObject equip = new JSONObject();
        try{
            equip.put("equip_id", cursor.getInt(cursor.getColumnIndex("equip_id")))
                    .put("equip_user_id", cursor.getInt(cursor.getColumnIndex("equip_user_id")))
                    .put("equip_gym_id", cursor.getInt(cursor.getColumnIndex("equip_gym_id")))
                    .put("equip_start", cursor.getString(cursor.getColumnIndex("equip_start")))
                    .put("equip_end", cursor.getString(cursor.getColumnIndex("equip_end")))
                    .put("equip_name", cursor.getString(cursor.getColumnIndex("equip_name")));
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }

        return equip;
    }
}
