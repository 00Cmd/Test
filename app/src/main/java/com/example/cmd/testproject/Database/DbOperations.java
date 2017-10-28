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
    private SQLiteOpenHelper mDbHandler;
    private SQLiteDatabase mDb;

    private static final String[] columns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_TITLE,
            DbHelper.COLUMN_DESCRIPTION,
            DbHelper.COLUMN_PRICE,
            DbHelper.COLUMN_IMG_URL
    };

    public static DbOperations get(Context ctx) {
        if(sDbOperations == null) {
            sDbOperations = new DbOperations(ctx);
        }
        return sDbOperations;
    }

    public DbOperations(Context context) {
        mDbHandler = new DbHelper(context);
    }

    public void open(){
        Log.i(TAG,"Database Opened");
        mDb = mDbHandler.getWritableDatabase();
    }
    public void close() {
        Log.i(TAG, "Database Closed");
        mDbHandler.close();
    }

    public Product addProduct(Product product){
        ContentValues values  = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE,product.getTitle());
        values.put(DbHelper.COLUMN_DESCRIPTION,product.getDesc());
        values.put(DbHelper.COLUMN_PRICE, product.getPrice());
        values.put(DbHelper.COLUMN_IMG_URL, product.getImgUrl());
        long insertid = mDb.insert(DbHelper.TABLE_PRODUCTS,null,values);
        product.setmId(insertid);
        return product;
    }

    // Getting single Employee
    public Product getProduct(long id) {

        Cursor cursor = mDb.query(DbHelper.TABLE_PRODUCTS,columns,DbHelper.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product e = new Product(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        // return Employee
        return e;
    }

    public List<Product> getAllProducts() {

        Cursor cursor = mDb.query(DbHelper.TABLE_PRODUCTS,columns,null,null,null, null, null);

        List<Product> products = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Product product = new Product();
                product.setmId(cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
                product.setTitle(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_TITLE)));
                product.setDesc(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_DESCRIPTION)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_PRICE)));
                product.setImgUrl(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_IMG_URL)));
                products.add(product);
            }
        }
        cursor.close();
        // return All Employees
        return products;
    }

    // Updating Employee
    public int updateProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE, product.getTitle());
        values.put(DbHelper.COLUMN_DESCRIPTION, product.getDesc());
        values.put(DbHelper.COLUMN_PRICE, product.getPrice());
        values.put(DbHelper.COLUMN_IMG_URL, product.getImgUrl());

        // updating row
        return mDb.update(DbHelper.TABLE_PRODUCTS, values,
                DbHelper.COLUMN_ID + "=?",new String[] { String.valueOf(product.getmId())});
    }

    // Deleting Employee
    public void removeProduct(Product product) {

        mDb.delete(DbHelper.TABLE_PRODUCTS, DbHelper.COLUMN_ID + "=" + product.getmId(), null);
    }
}
