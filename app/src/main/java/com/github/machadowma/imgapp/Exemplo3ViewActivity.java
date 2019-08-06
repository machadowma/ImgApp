package com.github.machadowma.imgapp;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;

public class Exemplo3ViewActivity extends AppCompatActivity {
    public SQLiteDatabase bancoDados;
    public TextView textViewEx3Nome;
    public Integer idSelecionado;
    public ImageView imageViewEx3View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo3_view);

        Intent intent = getIntent();
        idSelecionado = intent.getIntExtra("id", 0);
        imageViewEx3View = (ImageView) findViewById(R.id.imageViewEx3View);

        textViewEx3Nome = (TextView) findViewById(R.id.textViewEx3Nome);

        carregarDado();
    }

    public void carregarDado() {
        try {
            bancoDados = openOrCreateDatabase("imageapp_ex3", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT id,nome,image_path FROM objeto WHERE id = " + idSelecionado, null);
            cursor.moveToFirst();
            textViewEx3Nome.setText(cursor.getString(cursor.getColumnIndex("nome")));
            if (cursor.getString(cursor.getColumnIndex("image_path")) != null) {
                String pathToPicture = cursor.getString(cursor.getColumnIndex("image_path"));
                File f = new File(pathToPicture);
                if (f.exists()) {
                    imageViewEx3View.setImageDrawable(Drawable.createFromPath(f.toString()));
                }
            }
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
