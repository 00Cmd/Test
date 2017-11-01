package com.example.cmd.testproject.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cmd.testproject.Fragments.ProductListFragment;
import com.example.cmd.testproject.Fragments.SingleFragmentActivity;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;

import java.io.ByteArrayOutputStream;

public class ProductListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
          return ProductListFragment.newInstance();
    }

}


