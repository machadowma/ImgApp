package com.github.machadowma.imgapp;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Exemplo3AddActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1;
    public SQLiteDatabase bancoDados;
    public EditText editTextEx3Nome;
    public Button buttonEx3Add;
    public ImageView imageViewEx3;
    String imagePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo3_add);

        buttonEx3Add = (Button) findViewById(R.id.buttonEx3Add);
        editTextEx3Nome = (EditText) findViewById(R.id.editTextEx3Nome);
        imageViewEx3 = (ImageView) findViewById(R.id.imageViewEx3);

        buttonEx3Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        imageViewEx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });
    }

    public void cadastrar(){
        String valueNome = null;


        if(TextUtils.isEmpty(editTextEx3Nome.getText().toString())){
            editTextEx3Nome.setError("Este campo é obrigatório");
            return;
        } else {
            valueNome = editTextEx3Nome.getText().toString();
        }

        try {
            bancoDados = openOrCreateDatabase("imageapp_ex3", MODE_PRIVATE, null);
            String sql = "INSERT INTO objeto (nome,image_path) VALUES (?,?)";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);

            stmt.bindString(1, valueNome);
            if(imagePath==null){
                stmt.bindNull(2);
            } else {
                stmt.bindString(2, imagePath);
            }

            stmt.executeInsert();
            bancoDados.close();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    /*public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Exemplo3AddActivity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    imageViewEx3.setImageURI(selectedImage);
                    break;
            }
    }*/

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Exemplo3AddActivity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    imageViewEx3.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    imagePath = imgDecodableString;
                    break;

            }
    }
}
