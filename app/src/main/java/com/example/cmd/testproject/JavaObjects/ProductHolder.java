package com.example.cmd.testproject.JavaObjects;

import android.content.Context;

import com.example.cmd.testproject.Database.DbOps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmd on 28.10.17.
 */

public class ProductHolder {
    private List<Product> mProduct;
    private static ProductHolder sProductHolder;

    public static ProductHolder get(Context ctx) {
        if(sProductHolder == null) {
            sProductHolder = new ProductHolder(ctx);
        }
        return sProductHolder;
    }

    private ProductHolder(Context ctx) {
        if (mProduct == null) {
            mProduct = new ArrayList<>();
            mProduct = DbOps.get(ctx).getProducts();
        }
    }

    public List<Product> getProducts() {
        return mProduct;
    }

    public Product getProduct(String id) {
        for (int i = 0; i < mProduct.size() ; i++) {
            if(mProduct.get(i).getId().equals(id)) {
                return mProduct.get(i);
            }
        }
        return null;
    }


}
