package com.example.cmd.testproject.JavaObjects;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
            mProducts = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Product product = new Product("Product = " + i, "Desc = "
                        + i, "ImgUrl = " + i, "Price = " + i + 2.2);
                mProducts.add(product);
            }
    }

    public Product getProduct(UUID prodId) {
        for (Product pr:mProducts) {
            if(pr.getId().equals(prodId));
            return pr;
        }
        return null;
    }

    //TODO: make dbQuery <---
    public List<Product> getProducts() {
        return mProducts;
    }
    public void addProduct(Product p) { mProducts.add(p); }
    //TODO: make dbQuery --- >
}
