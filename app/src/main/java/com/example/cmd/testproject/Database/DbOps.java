package com.example.cmd.testproject.Database;

import android.content.Context;

import com.example.cmd.testproject.JavaObjects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmd on 26.10.17.
 */

public class DbOps {
    private static DbOps sDbOps;
    private FirebaseDatabase mDb;
    private DatabaseReference dbRef;
    private List<Product> mProducts;
    public static DbOps get() {
        if (sDbOps == null) {
            sDbOps = new DbOps();
        }
        return sDbOps;
    }

    private DbOps() {
        dbRef = FirebaseDatabase.getInstance().getReference("products");
    }

    public void addProduct(Product product) {
        for (int i = 0; i < getProducts().size() ; i++) {
            if (getProducts().get(i).getId() != product.getId()) {
                dbRef.child(product.getId().toString()).setValue(product);
                return;
            }
        }
    }

    public List<Product> getProducts() {
        mProducts = new ArrayList<>();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot product: dataSnapshot.getChildren()) {
                    Product pr = product.getValue(Product.class);
                    mProducts.add(pr);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mProducts;
    }

}
