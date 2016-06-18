package com.example.ivicof.camaraandroid;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    private Uri fileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciamos el botón que hará funcionar la cámara.
        Button btn_foto = (Button) findViewById(R.id.btn_foto);
        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un String que utilizaremos para poner nombre a la foto, en este caso cogemos la fecha de hoy, añadiremos un IMG_ delante y un .jpg detrás.
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
                File mediaFile;
                //Creamos un variable del tipo 'file' que servirá para indicar al intent que el resultado de la foto lo debe guardar en un archivo. Mediante el constructor de File especificamos
                //la ruta que va a tener nuestro archivo, y el nombre.
                mediaFile = new File("/sdcard/DCIM" + File.separator +
                        "IMG_" + timeStamp + ".jpg");
                //Guardamos la URI del archivo para poder rescatarlo de forma facil después, así podremos mostrarlo en el listview
                fileUri = Uri.fromFile(mediaFile);
                //Creamos un intent del tipo Action_Image_Capture. A este le añadiremos en el putExtra el resultado de tomar la foto (la foto en sí), y la URI.
                Intent capturarImagenIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                capturarImagenIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                //Empezamos el activity para que se nos abra la cámara.
                startActivityForResult(capturarImagenIntent,
                        REQUEST_CODE);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Instanciamos el imageView en esta parte de codigo. El método StartActivityForResult devuelve un booleano en función de
        //si puede completar el intent o no, aquí podemos comprobar si ha finalizado correctamente.
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // imageView.setImageBitmap(bitmap);
        imageView.setImageURI(fileUri);


    }}
