package com.example.cmd.testproject.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.cmd.testproject.Database.DbOps;
import com.example.cmd.testproject.Fragments.ProductFragment;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;

import java.util.List;



public class ProductPageActivity extends AppCompatActivity {
    private static final String PRODUCT_ID = "com.example.cmd.testproject.Activitys.ProductPageActivity.product_id";
    private String productId;

    private ViewPager mPager;
    private List<Product> mProducts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page_activity);

        productId = getIntent().getStringExtra(PRODUCT_ID);

        mPager = (ViewPager) findViewById(R.id.viewPager);

        mProducts = DbOps.get(getApplicationContext()).getProducts();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                //TODO: for some reason keeps looping....do later!
                Product pr = mProducts.get(position);
                return ProductFragment.newInstance(pr.getId());
            }

            @Override
            public int getCount() {
                return mProducts.size();
            }
        });

        for (int i = 0; i < mProducts.size(); i++) {
            if (mProducts.get(i).getId().equals(productId)) {
                mPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, String productID) {
        Intent intent = new Intent(packageContext, ProductPageActivity.class);
        intent.putExtra(PRODUCT_ID, productID);
        return intent;
    }
}
