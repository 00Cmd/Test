package com.example.cmd.testproject.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cmd.testproject.Adapters.ListFragmentAdapter;
import com.example.cmd.testproject.Database.DbHelper;
import com.example.cmd.testproject.Database.DbOperations;
import com.example.cmd.testproject.Database.DbOps;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.JavaObjects.ProductHolder;
import com.example.cmd.testproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ListFragmentAdapter mAdapter;
    private List<Product> mProducts;

    public ProductListFragment() {
        // Required empty public constructor
    }
    public static ProductListFragment newInstance() {
       return new ProductListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.listRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addProduct:
                createDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onStart() {
        super.onStart();
        addToDb();
        updateUI();
    }

    private void addToDb() {
        List<Product> myProducts = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            Product pr = new Product(null, "Title " + i,
                    "Desc " + i, "Price " + i, "imgUrl " + i);
            DbOperations.get(getActivity()).addProduct(pr);
        }
    }

    private void updateUI() {

        mProducts = DbOperations.get(getActivity()).getAllProducts();
        if (mAdapter == null) {
            mAdapter = new ListFragmentAdapter(mProducts);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void createDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Add new product");
        final EditText mTtitle = (EditText)dialog.findViewById(R.id.produtTitle);
        final EditText mDesc  = (EditText)dialog.findViewById(R.id.productDesc);
        final EditText mPrice  = (EditText)dialog.findViewById(R.id.productPrice);
        Button btnAdd  = (Button)dialog.findViewById(R.id.btnAdd);
        Button btnCancel  = (Button)dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mTtitle.getText().toString()) &&
                        TextUtils.isEmpty(mDesc.getText().toString()) &&
                        TextUtils.isEmpty(mPrice.getText().toString())) {
                    Toast.makeText(getActivity(), " Fill out all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    String title = mTtitle.getText().toString();
                    String desc = mDesc.getText().toString();
                    String price = mPrice.getText().toString();
                    String imgUrl = "randomText";
                    Product pr = new Product(null,title,desc,imgUrl,price);
                    DbOperations.get(getActivity()).addProduct(pr);
                    Toast.makeText(getActivity(), pr.getTitle() + " was added", Toast.LENGTH_SHORT).show();
                    mTtitle.setText("");
                    mDesc.setText("");
                    mPrice.setText("");

                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
