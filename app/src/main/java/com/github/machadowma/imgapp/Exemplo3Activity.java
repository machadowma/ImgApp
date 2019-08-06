package com.github.machadowma.imgapp;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Exemplo3Activity extends AppCompatActivity {
    public SQLiteDatabase bancoDados;
    public ListView listViewEx3;
    public ArrayList<Integer> arrayIds;
    public Integer idSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarDado();
            }
        });

        listViewEx3 = (ListView) findViewById(R.id.listViewEx3);

        listViewEx3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                verDado(i);
            }
        });

        listViewEx3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluirDado(i);
                return true;
            }
        });

        criarBancoDados();
        listarDados();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarDados();
    }

    public void criarBancoDados(){
        try {
            bancoDados = openOrCreateDatabase("imageapp_ex3", MODE_PRIVATE, null);
            //bancoDados.execSQL("DROP TABLE ingrediente");
            //bancoDados.execSQL("DROP TABLE receita");
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS objeto(" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    " , nome VARCHAR" +
                    " , image_path VARCHAR)");
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarDados(){
        try {
            bancoDados = openOrCreateDatabase("imageapp_ex3", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT id,nome,image_path FROM objeto",null);
            ArrayList<String> linhas = new ArrayList<String>();
            ArrayAdapter adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    linhas
            );
            listViewEx3.setAdapter(adapter);
            cursor.moveToFirst();
            arrayIds = new ArrayList<Integer>();
            while(cursor!=null){
                arrayIds.add(cursor.getInt(cursor.getColumnIndex("id")));
                linhas.add(cursor.getString(cursor.getColumnIndex("nome")));
                cursor.moveToNext();
            }
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrarDado(){
        Intent intent = new Intent(this,Exemplo3AddActivity.class);
        startActivity(intent);
    }

    public void verDado(Integer i){
        idSelecionado = arrayIds.get(i);
        Intent intent = new Intent(this,Exemplo3ViewActivity.class);
        intent.putExtra("id",idSelecionado);
        startActivity(intent);
    }

    public void excluirDado(Integer i){
        idSelecionado = arrayIds.get(i);
        try {
            bancoDados = openOrCreateDatabase("imageapp_ex3", MODE_PRIVATE, null);
            String sql = "DELETE FROM objeto WHERE id = ?";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);
            stmt.bindLong(1, idSelecionado);
            stmt.executeUpdateDelete();
            bancoDados.close();
            listarDados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
