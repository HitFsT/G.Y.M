package com.example.dell.test.Http;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 龙 on 2017/12/7.
 * This class is a sql version controller.
 *
 */

public class DatabaseWrapper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseWrapper";

    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "Creating database [" + DATABASE_NAME + " v." + DATABASE_VERSION + "]...");
        sqLiteDatabase.execSQL(RefreshORM.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(GymORM.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(CompetitionORM.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(EquipmentORM.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database ["+DATABASE_NAME+" v." + oldVersion+"] to ["+DATABASE_NAME+" v." + newVersion+"]...");
        sqLiteDatabase.execSQL(RefreshORM.SQL_DROP_TABLE);
        sqLiteDatabase.execSQL(GymORM.SQL_DROP_TABLE);
        sqLiteDatabase.execSQL(CompetitionORM.SQL_DROP_TABLE);
        sqLiteDatabase.execSQL(EquipmentORM.SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}