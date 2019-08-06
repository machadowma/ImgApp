package com.github.machadowma.imgapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        Button buttonEx1 = (Button) findViewById(R.id.buttonEx1);
        buttonEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirExemplo1();
            }
        });

        Button buttonEx2 = (Button) findViewById(R.id.buttonEx2);
        buttonEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirExemplo2();
            }
        });

        Button buttonEx3 = (Button) findViewById(R.id.buttonEx3);
        buttonEx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirExemplo3();
            }
        });
    }

    public void requestPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    public void abrirExemplo1(){
        Intent intent = new Intent(this,Exemplo1Activity.class);
        startActivity(intent);
    }

    public void abrirExemplo2(){
        Intent intent = new Intent(this,Exemplo2Activity.class);
        startActivity(intent);
    }

    public void abrirExemplo3(){
        Intent intent = new Intent(this,Exemplo3Activity.class);
        startActivity(intent);
    }
}

