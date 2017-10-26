package com.example.cmd.testproject.Database;

import android.content.Context;

import com.example.cmd.testproject.JavaObjects.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cmd on 26.10.17.
 */

public class DbOps {
    private static DbOps sDbOps;
    private FirebaseDatabase mDb;
    private DatabaseReference dbRef;
    public static DbOps newInstance() {
        if (sDbOps == null) {
            sDbOps = new DbOps();
        }
        return sDbOps;
    }

    private DbOps() {
        dbRef = FirebaseDatabase.getInstance().getReference("products");
    }

    public void addProduct(Product product) {
        if(dbRef.child(product.getId().toString()) == null ) {
            dbRef.child(product.getId().toString()).setValue(product);
            return;
        }
    }

}
