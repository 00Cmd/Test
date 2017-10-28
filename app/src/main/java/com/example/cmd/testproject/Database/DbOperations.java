package com.example.cmd.testproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cmd.testproject.JavaObjects.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmd on 28.10.17.
 */

public class DbOperations {
    private static final String TAG = "DbOperations";
    private static DbOperations sDbOperations;
//    private SQLiteOpenHelper mDbHandler;
    private SQLiteDatabase mDb;



    public static DbOperations get(Context ctx) {
        if(sDbOperations == null) {
            sDbOperations = new DbOperations();
        }
        return sDbOperations;
    }





}
