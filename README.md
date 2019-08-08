# ImageApp
Aplicativo em Android com 3 exemplos de manipulação de imagens salvas no dispositivo.

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Main.png " align="left" height="360" width="180" ></td>
</tr>
<tr align=center>
<td>Tela principal</td>
</tr>
</table>

## Exemplo 1 - Utilizando biblioteca externa Picasso
<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Exemplo1_2.png " align="left" height="360" width="180" ></td>
</tr>
<tr align=center>
<td>Exemplo 1</td>
</tr>
</table>

Inclua no arquivo <b><i>build.gradle (Module: app)</i></b> a chamada para a biblioteca:
```
dependencies {
 . . .
implementation 'com.squareup.picasso:picasso:2.5.2'
}
```

Inclua no arquivo <b><i>AndroidManifest.xml</i></b> a permissão de acesso:
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

Inclua no arquivo <b><i>MainActivity</i></b> a requisição de permissão:
```
...
public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS = 1;
    
    ...
    
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
...
```

Para carregar a imagem do dispositivo e exibir em um <i>ImageView</i>, utlize o código abaixo:
```
        String pathToPicture = "/storage/emulated/0/DCIM/Camera/IMG_20190803_113620163.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.imageViewEx1);
        File f = new File(pathToPicture);
        Picasso.with(this).load(f).into(imageView);
```

## Exemplo 2
Ao contrário do que foi realizado no exemplo anterior, no exemplo 2 não são utilizadas bibliotecas externas. Desse modo, nenhuma alteração é necessária no arquivo <b><i>build.gradle (Module: app)</i></b>.
Entretanto, as permissões do dispositivos ainda precisam ser declaradas.
Para carregar a imagem do dispositivo e exibir em um <i>ImageView</i>, utlize o código abaixo:
```
        String pathToPicture = "/storage/emulated/0/DCIM/Camera/IMG_20190803_113622944.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.imageViewEx2);
        File f = new File(pathToPicture);
        imageView.setImageDrawable(Drawable.createFromPath(f.toString()));
```

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Exemplo1_2.png " align="left" height="360" width="180" ></td>
</tr>
<tr align=center>
<td>Exemplo 2</td>
</tr>
</table>

## Exemplo 3

O exemplo 3 combina os exemplos anteriores com manipulação de banco de dados SQLite. Nessa parte do app, você pode localizar uma imagem gravada no dispositivo, visualizar e salvar o caminho da imagem em um banco de dados SQLite. 
A lista de imagens salvas é exibida na tela inicial do aplicativo. As imagens podem ser visualizadas novamente clicando em um dos itens da lista.

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Exemplo3_Add.png " align="left" height="360" width="180" ></td>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Exemplo3_List.png " align="left" height="360" width="180" ></td>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Exemplo3_View-a.png " align="left" height="360" width="180" ></td>
<td><img src="https://github.com/machadowma/ImgApp/blob/master/Exemplo3_View-b.png " align="left" height="360" width="180" ></td>
</tr>
<tr align=center>
<td>Salvar imagem</td>
<td>Listar images</td>
<td>Exibir imagem salva</td>
<td>Exibir imagem salva</td>
</tr>
</table>



# License
MIT License

Copyright (c) 2019 machadowma

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
