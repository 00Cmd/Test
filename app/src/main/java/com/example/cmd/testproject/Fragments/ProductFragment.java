package com.example.cmd.testproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmd.testproject.Database.DbHelper;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;


public class ProductFragment extends Fragment {
    private static final String TAG = "ProductFragment";
    private static final String ARGUMENT_PROD_ID = "prod_id";
    private TextView mTitle,mDesc,mImgUrl,mPrice;
    private Product product;




    public static Fragment newInstance(int productID) {
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_PROD_ID,productID);
        ProductFragment frag = new ProductFragment();
        frag.setArguments(args);
        return frag;
    }

    public ProductFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {


            int mId = getArguments().getInt(ARGUMENT_PROD_ID);
            product = DbHelper.get(getContext()).getProduct(mId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_fragment,container,false);
        setWidgets(v);
        setDataOnText();
        return v;
    }

    private void setDataOnText(){
        mTitle.setText(product.getTitle());
        mDesc.setText(product.getDesc());
        mPrice.setText(product.getPrice());
    }

    private void setWidgets(View v) {
        mTitle = (TextView) v.findViewById(R.id.title);
        mDesc = (TextView) v.findViewById(R.id.desc);
        mPrice = (TextView) v.findViewById(R.id.price);
    }
}
