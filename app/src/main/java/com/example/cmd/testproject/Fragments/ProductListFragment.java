package com.example.cmd.testproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmd.testproject.Adapters.ListFragmentAdapter;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.JavaObjects.ProductHolder;
import com.example.cmd.testproject.R;

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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.listRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();

        return view;
    }

    private void updateUI() {
        ProductHolder holder = ProductHolder.get(getActivity());
        mProducts = holder.getProducts();

        if (mAdapter == null) {
            ListFragmentAdapter adapter = new ListFragmentAdapter(mProducts);
            mRecyclerView.setAdapter(adapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
