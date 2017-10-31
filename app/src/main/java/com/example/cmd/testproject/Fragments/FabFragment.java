package com.example.cmd.testproject.Fragments;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cmd.testproject.Activitys.FacebookAuthActivity;
import com.example.cmd.testproject.Activitys.ProductListActivity;
import com.example.cmd.testproject.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by cmd on 31.10.17.
 */

public class FabFragment extends Fragment {

    public static Fragment get() {
        return new FabFragment();
    }

    public FabFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        final ImageView icon = new ImageView(getContext()); // Create an icon
        icon.setImageResource(R.mipmap.ic_add_black_24dp);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(getActivity())
                .setContentView(icon)
                .build();
        SubActionButton.Builder builder = new SubActionButton.Builder(getActivity());
        ImageView icon1 = new ImageView(getContext());
        ImageView icon2 = new ImageView(getContext());
        ImageView icon3 = new ImageView(getContext());
        ImageView icon4 = new ImageView(getContext());
        ImageView icon5 = new ImageView(getContext());

        icon1.setImageResource(R.mipmap.ic_shop_black_24dp);
        icon2.setImageResource(R.mipmap.ic_shopping_cart_black_24dp);
        icon3.setImageResource(R.mipmap.ic_email_black_24dp);
        icon4.setImageResource(R.mipmap.ic_settings_black_24dp);
        if(AccessToken.getCurrentAccessToken() != null) {
            icon5.setImageResource(R.mipmap.ic_exit_to_app_black_24dp);
        } else {
            icon5.setImageResource(R.mipmap.ic_launcher);
        }

        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(builder.setContentView(icon1).build())
                .addSubActionView(builder.setContentView(icon2).build())
                .addSubActionView(builder.setContentView(icon3).build())
                .addSubActionView(builder.setContentView(icon4).build())
                .addSubActionView(builder.setContentView(icon5).build())
                .attachTo(actionButton)
                .build();

        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                icon.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(icon, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                icon.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(icon, pvhR);
                animation.start();
            }
        });
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProductListActivity.class));
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Redirect to shopping cart
            }
        });
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: open mail activity
            }
        });
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: open settings agtivity
            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : return appropriate activity

            }
        });
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    startActivity(new Intent(getContext(), FacebookAuthActivity.class));
                } else {
                    String id = AccessToken.getCurrentAccessToken().getUserId();
                    LoginManager.getInstance().logOut();
                    Toast.makeText(getContext(), "Logged out with " + id, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
