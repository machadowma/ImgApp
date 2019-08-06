package com.github.machadowma.imgapp;


import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class Exemplo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo2);

        String pathToPicture = "/storage/emulated/0/DCIM/Camera/IMG_20190803_113622944.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.imageViewEx2);
        File f = new File(pathToPicture);
        imageView.setImageDrawable(Drawable.createFromPath(f.toString()));
    }
}
