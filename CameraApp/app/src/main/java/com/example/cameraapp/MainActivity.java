package com.example.cameraapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 22;//utahitaji hii
    Uri cameraUri;
    Button button;
    private ImageView imageView;
    private String CameraPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.photo);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        //hivi ndo unalaunch camera the rest itadepend na vile your app iko structured
                        try {
                            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(camera, CAMERA_REQUEST);
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"failed to open camera for some reason",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    //hii method utahitaji, hii ndo inareceive picha umepiga
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            switch (requestCode){
                case CAMERA_REQUEST:
                if (resultCode == RESULT_OK){
                    //hapa itadepend na vile your activities ziko organised
                    try {
                        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                        imageView.setImageBitmap(bitmap);
                    }catch (Exception e){
                        Toast.makeText(this, "Couldn't load the photo for some reason", Toast.LENGTH_LONG).show();
                    }
                }
                break;
                default:
                    break;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}