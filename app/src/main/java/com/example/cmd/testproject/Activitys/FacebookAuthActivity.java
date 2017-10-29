package com.example.cmd.testproject.Activitys;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cmd.testproject.Fragments.FacebookAuthFragment;
import com.example.cmd.testproject.R;

public class FacebookAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_auth);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new FacebookAuthFragment();
        fm.beginTransaction().add(R.id.facebookAuthContainer,fragment)
                                                            .commit();
        finish();

    }

}
