package com.example.cmd.testproject.JavaObjects;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cmd.testproject.Database.DbOps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cmd on 25.10.17.
 */

public class ProductHolder {
    private static final String TAG = "ProductHolder";
    private static ProductHolder sProductHolder;
    private List<Product> mProducts;

    public static ProductHolder get(Context ctx) {
        if(sProductHolder == null) {
            sProductHolder = new ProductHolder(ctx);
        }
        return sProductHolder;
    }

    private ProductHolder(Context ctx) {
        //TODO: Look if it will require a seprate thread;
        mProducts = DbOps.get().getProducts();
    }

    public Product getProduct(UUID prodId) {
        for (Product pr:mProducts) {
            if(pr.getId().equals(prodId));
            return pr;
        }
        return null;
    }

    public List<Product> getProducts() {
        return mProducts;
    }
}
