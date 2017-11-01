package com.example.cmd.testproject.Fragments;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cmd.testproject.Activitys.FacebookAuthActivity;
import com.example.cmd.testproject.Activitys.ProductListActivity;
import com.example.cmd.testproject.Database.DbHelper;
import com.example.cmd.testproject.JavaObjects.Product;
import com.example.cmd.testproject.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.File;
import java.io.IOException;

/**
 * Created by cmd on 31.10.17.
 */

public class FabFragment extends Fragment {
    private EditText title,description,price;
    private ImageView image;
    private final int IMAGE_REQUEST = 1;
    private StorageReference mStorageRef;
    private Uri filePath;


    public static Fragment get() {
        return new FabFragment();
    }

    public FabFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStorageRef = FirebaseStorage.getInstance().getReference("images");

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
        ImageView icon6 = new ImageView(getContext());

        icon1.setImageResource(R.mipmap.ic_shop_black_24dp);
        icon2.setImageResource(R.mipmap.ic_shopping_cart_black_24dp);
        icon3.setImageResource(R.mipmap.ic_email_black_24dp);
        icon4.setImageResource(R.mipmap.ic_settings_black_24dp);
        if(AccessToken.getCurrentAccessToken() != null) {
            icon5.setImageResource(R.mipmap.ic_exit_to_app_black_24dp);
        } else {
            icon5.setImageResource(R.mipmap.ic_launcher);
        }
        icon6.setImageResource(R.mipmap.ic_add_black_24dp);

        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(builder.setContentView(icon1).build())
                .addSubActionView(builder.setContentView(icon2).build())
                .addSubActionView(builder.setContentView(icon3).build())
                .addSubActionView(builder.setContentView(icon4).build())
                .addSubActionView(builder.setContentView(icon5).build())
                .addSubActionView(builder.setContentView(icon6).build())
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
        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    private void addToStorage() {
        if(filePath != null) {
            StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }
    }


    private void createDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Add new product");
        title = (EditText)dialog.findViewById(R.id.produtTitle);
        description  = (EditText)dialog.findViewById(R.id.productDesc);
        price  = (EditText)dialog.findViewById(R.id.productPrice);

        Button btnSelectImg = (Button)dialog.findViewById(R.id.btnSelectImage);
        Button btnAdd  = (Button)dialog.findViewById(R.id.btnAdd);
        Button btnCancel  = (Button)dialog.findViewById(R.id.btnCancel);

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:  Maybe upload the image to storage from here, and when
                //TODO: user presses upload image it will upload it to db and pars the img Url from storage?
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,IMAGE_REQUEST);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(title.getText().toString()) &&
                        TextUtils.isEmpty(description.getText().toString()) &&
                        TextUtils.isEmpty(price.getText().toString())) {
                    Toast.makeText(getActivity(), " Fill out all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    String mTitle = title.getText().toString();
                    String mDesc = description.getText().toString();
                    String mPrice = price.getText().toString();
                    //TODO: add img url from firebase storage to upload to database.
                    String imgUrl = filePath.getLastPathSegment();
                    Product pr = new Product(1,mTitle,mDesc,imgUrl,mPrice);
                    DbHelper.get(getContext()).addProduct(pr);

                    Toast.makeText(getActivity(), pr.getTitle() + " was added", Toast.LENGTH_SHORT).show();
                    title.setText("");
                    description.setText("");
                    price.setText("");

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




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if(requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filePath);
                    image.setImageBitmap(bitmap);
                    //TODO: setImageAcordingly
                } catch (IOException e) {
                    e.getLocalizedMessage();
                }
            }
        }
    }
}
