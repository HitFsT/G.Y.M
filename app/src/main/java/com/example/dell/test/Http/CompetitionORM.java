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

public class CompetitionORM {
    private static final String TAG = "CompetitionORM";

    private static final String TABLE_NAME = "competition";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE competition ( competition_id INTEGER PRIMARY KEY" +
            ", competition_user_id INTEGER, competition_gym_id INTEGER, competition_name TEXT, competition_start TEXT," +
            "competition_end TEXT, competition_equip_id INTEGER)";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static void insertGames(Context context, JSONArray games) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        database.delete(CompetitionORM.TABLE_NAME, null, null);
        try {
            for (int i = 0; i < games.length(); i++) {
                CompetitionORM.insertCompetition(context, games.getJSONObject(i));
            }
        } catch (Exception e) {
            DialogUtil.showDialog(context, e.getMessage());
        }
    }

    public static void insertCompetition(Context context, JSONObject compet) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();

        ContentValues values = gymToContentValues(compet);
        long competId = database.insert(CompetitionORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted new Post with ID: " + competId);

    }


    private static ContentValues gymToContentValues(JSONObject compet) {
        ContentValues values = new ContentValues();
        try{
            values.put("competition_id", compet.getInt("competition_id"));
            values.put("competition_user_id", compet.getInt("competition_user_id"));
            values.put("competition_gym_id", compet.getInt("competition_gym_id"));
            values.put("competition_name", compet.getString("competition_name"));
            values.put("competition_start", compet.getString("competition_start"));
            values.put("competition_end", compet.getString("competition_end"));
            values.put("competition_equip_id", compet.getInt("competition_equip_end"));
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }


        return values;
    }


    public static JSONArray getGames(Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + CompetitionORM.TABLE_NAME, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Posts...");
        JSONArray games = new JSONArray();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                JSONObject game = cursorToPost(cursor);
                games.put(game);
                cursor.moveToNext();
            }
            Log.i(TAG, "Posts loaded successfully.");
        }


        return games;
    }


    public static JSONObject cursorToPost(Cursor cursor) {
        JSONObject game = new JSONObject();
        try{
            game.put("competition_id", cursor.getInt(cursor.getColumnIndex("competition_id")))
                    .put("competition_user_id", cursor.getInt(cursor.getColumnIndex("competition_user_id")))
                    .put("competition_gym_id", cursor.getInt(cursor.getColumnIndex("competition_gym_id")))
                    .put("competition_name", cursor.getString(cursor.getColumnIndex("competition_name")))
                    .put("competition_start", cursor.getString(cursor.getColumnIndex("competition_start")))
                    .put("competition_end", cursor.getString(cursor.getColumnIndex("competition_end")))
                    .put("competition_equip_id", cursor.getInt(cursor.getColumnIndex("competition_equip_id")));
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }

        return game;
    }
}
