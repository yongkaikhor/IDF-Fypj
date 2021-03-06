package com.navigine.naviginedemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class addPhoto extends AppCompatActivity {
    ImageView mImageView;
    Button mChooseBtn, mUploadBtn;
    Spinner mSpinnerL;


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_img);

        //Views
        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.choose_image_btn);

        //handle button click
        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        // if permission is not granted, request for it
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        // show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        // if permission is granted,
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });
    }

    private void pickImageFromGallery() {
        //intent to pick image from gallery
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle result of runtime permission
    /*@Override
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int ){
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    //if permission was denied
                    Toast.makeText()
                }
            }
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set image to image view
            mImageView.setImageURI(data.getData());
        }
    }
}