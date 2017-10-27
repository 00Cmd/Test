package com.example.cmd.testproject.Database;

import android.content.Context;

import com.example.cmd.testproject.JavaObjects.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class DbOps {
    private static DbOps sDbOps;
    private FirebaseDatabase mDb;
    private DatabaseReference dbRef;
    private List<Product> mProducts;
    private Product pr;

    public static DbOps get(Context ctx) {
        if (sDbOps == null) {
            sDbOps = new DbOps(ctx);
        }
        return sDbOps;
    }

    private DbOps(Context ctx) {
        mDb = FirebaseDatabase.getInstance();
        dbRef = mDb.getReference().child("products");
        if(mProducts == null) {
            getProducts();
        }
    }

    public Product getProduct(final String mId) {
        Query query = dbRef.child(mId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot obj: dataSnapshot.getChildren()) {
                        if(obj.getValue(Product.class).getId().equals(mId)) {
                            pr = obj.getValue(Product.class);
                            break;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return pr;
    }

    public void addProduct(Product product) {
        dbRef.child(product.getId()).setValue(product);
    }


    public List<Product> getProducts() {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot mObject : dataSnapshot.getChildren());
                mProducts = new ArrayList<>();
                Product pr = dataSnapshot.getValue(Product.class);
                mProducts.add(pr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(listener);
        return mProducts;
    }

}
