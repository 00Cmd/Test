package com.example.cmd.testproject.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cmd on 28.10.17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PRODUCTS = "employees";
    public static final String COLUMN_ID = "empId";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMG_URL= "img_url";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_PRICE + " TEXT, " +
                    COLUMN_IMG_URL + " NUMERIC, " +
                    ")";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS );
        db.execSQL(TABLE_CREATE);
    }
}
