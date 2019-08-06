package com.github.machadowma.imgapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class Exemplo1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo1);

        String pathToPicture = "/storage/emulated/0/DCIM/Camera/IMG_20190803_113620163.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.imageViewEx1);
        File f = new File(pathToPicture);
        Picasso.with(this).load(f).into(imageView);
    }
}
