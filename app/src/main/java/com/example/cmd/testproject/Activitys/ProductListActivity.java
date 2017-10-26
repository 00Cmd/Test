package com.example.cmd.testproject.Activitys;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cmd.testproject.Fragments.ProductListFragment;
import com.example.cmd.testproject.Fragments.SingleFragmentActivity;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;

public class ProductListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ProductListFragment.newInstance();
    }
}
