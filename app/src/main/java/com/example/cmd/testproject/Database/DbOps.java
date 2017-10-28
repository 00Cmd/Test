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
import java.util.Iterator;
import java.util.List;



public class DbOps {
    private static DbOps sDbOps;
    private DatabaseReference dbRef;
    private List<Product> mProducts;
    private Product pr;

//    public static DbOps get(Context ctx) {
//        if (sDbOps == null) {
//            sDbOps = new DbOps(ctx);
//        }
//        return sDbOps;
//    }

    public DbOps(Context ctx) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("products");
        if(mProducts == null) {
            mProducts = new ArrayList<>();
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

<<<<<<< HEAD
        ValueEventListener valueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
=======
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProducts = new ArrayList<>();
>>>>>>> databaseTestingBranch
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    Product pr = dataSnapshot.getValue(Product.class);
                    mProducts.add(pr);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
<<<<<<< HEAD
        };
        dbRef.addValueEventListener(valueListener);
=======
        });
>>>>>>> databaseTestingBranch
        return mProducts;
    }

}
