package com.example.dell.test.Http;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by 龙 on 2017/12/7.
 */

public class RefreshORM {
    private static final String TAG = "RefreshORM";

    private static final String TABLE_NAME = "refresh";
    public static String SQL_CREATE_TABLE = "CREATE TABLE refresh ( name TEXT, refreshable INTEGER)";
    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    /* set the table 'name' to be refreshable */
    public static void settrue(Context context, String name){
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        String selection = "name" + " = ?";
        String[] selectionArgs = { name };
        ContentValues values = new ContentValues();
        values.put("refreshable", 1);
        database.update(RefreshORM.TABLE_NAME, values, selection, selectionArgs);

    }

    /* set the table 'name' not to be refreshable */
    public static void setfalse(Context context, String name){
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        String selection = "name" + " = ?";
        String[] selectionArgs = { name };
        ContentValues values = new ContentValues();
        values.put("refreshable", 0);
        database.update(RefreshORM.TABLE_NAME, values, selection, selectionArgs);
    }

    /* check if the table 'name' is refreshable or not */
    public static int get(Context context, String name){
        int result = -1;
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + RefreshORM.TABLE_NAME, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Posts...");

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if(name.equals(cursor.getString(cursor.getColumnIndex("name")))){
                    result = cursor.getInt(cursor.getColumnIndex("refreshable"));
                    break;
                }
                cursor.moveToNext();
            }
            Log.i(TAG, "Posts loaded successfully.");
        }
        cursor.close();
        return result;

    }

    public static void iniRefresh(Context context, int user_id) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        database.delete(RefreshORM.TABLE_NAME, null, null);
        ContentValues values = new ContentValues();

         /* It looks weired, i actually saved the login user id into the refresh table
            ,in order that i can load it next time. maybe the name 'refresh' is not that
            accurate.
         */
        values.put("name", "user_id");
        values.put("refreshable", user_id);
        Long userId = database.insert(RefreshORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted refreshable gym with ID: " + userId);

        values.clear();

        /* normally set all tables to be refreshable */
        values.put("name", "gym");
        values.put("refreshable", 1);
        Long gymId = database.insert(RefreshORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted refreshable gym with ID: " + gymId);

        values.clear();
        values.put("name", "equip");
        values.put("refreshable", 1);
        Long equipId = database.insert(RefreshORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted refreshable gym with ID: " + equipId);

        values.clear();
        values.put("name", "competition");
        values.put("refreshable", 1);
        Long gameId = database.insert(RefreshORM.TABLE_NAME, "null", values);
        Log.i(TAG, "Inserted refreshable gym with ID: " + gameId);

    }

}
