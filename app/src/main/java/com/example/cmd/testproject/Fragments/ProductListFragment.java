package com.example.cmd.testproject.Fragments;


import android.app.Activity;
import android.app.Dialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cmd.testproject.Activitys.FacebookAuthActivity;
import com.example.cmd.testproject.Adapters.ListFragmentAdapter;
import com.example.cmd.testproject.Database.DbHelper;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;
import com.facebook.FacebookActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;


import java.io.IOException;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class ProductListFragment extends Fragment {
    private static final String TAG = "ProductListFragment";
    private RecyclerView mRecyclerView;
    private ListFragmentAdapter mAdapter;
    private List<Product> mProducts;
    private DbHelper mHandler;



    public ProductListFragment() {
        // Required empty public constructor
    }
    public static ProductListFragment newInstance() {
       return new ProductListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new DbHelper(getContext());
        setHasOptionsMenu(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.listRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FragmentManager fm = getFragmentManager();
        Fragment frag = new FabFragment();
        fm.beginTransaction().add(R.id.container,frag).commit();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI() {

        //Todo : add this to a new thread
        //TODO: UI isn't updated after product is added by Admin.
        mProducts = mHandler.getAllProducts();

        if (mAdapter == null) {
            mAdapter = new ListFragmentAdapter(mProducts);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }




}
