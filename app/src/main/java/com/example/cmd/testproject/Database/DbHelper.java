package com.example.cmd.testproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cmd.testproject.JavaObjects.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmd on 28.10.17.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static DbHelper sHelper;
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mHelper;

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
                    COLUMN_IMG_URL + " TEXT " +
                    ")";

    private static final String[] columns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_TITLE,
            DbHelper.COLUMN_DESCRIPTION,
            DbHelper.COLUMN_PRICE,
            DbHelper.COLUMN_IMG_URL
    };

    public static DbHelper get(Context ctx) {
        if(sHelper == null) {
            sHelper = new DbHelper(ctx);
        }
        return sHelper;
    }

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
    public void deleteTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS);
        db.close();
    }

    public void addProduct(Product product){
        mDatabase = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE,product.getTitle());
        values.put(DbHelper.COLUMN_DESCRIPTION,product.getDesc());
        values.put(DbHelper.COLUMN_PRICE, product.getPrice());
        values.put(DbHelper.COLUMN_IMG_URL, product.getImgUrl());
        mDatabase.insert(DbHelper.TABLE_PRODUCTS,null,values);
    }

    public Product getProduct(long id) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.query(DbHelper.TABLE_PRODUCTS,columns,DbHelper.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Product e = new Product(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        cursor.close();
        return e;
    }

    public List<Product> getAllProducts() {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.query(DbHelper.TABLE_PRODUCTS,columns,null,null,null, null, null);

        List<Product> products = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Product product = new Product();
                product.setmId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
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

    public int updateProduct(Product product) {
        mDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_TITLE, product.getTitle());
        values.put(DbHelper.COLUMN_DESCRIPTION, product.getDesc());
        values.put(DbHelper.COLUMN_PRICE, product.getPrice());
        values.put(DbHelper.COLUMN_IMG_URL, product.getImgUrl());

        // updating row
        return mDatabase.update(DbHelper.TABLE_PRODUCTS, values,
                DbHelper.COLUMN_ID + "=?",new String[] { String.valueOf(product.getmId())});
    }

    public void removeProduct(Product product) {
        mDatabase = this.getWritableDatabase();
        mDatabase.delete(DbHelper.TABLE_PRODUCTS, DbHelper.COLUMN_ID + "=" + product.getmId(), null);
    }

}
