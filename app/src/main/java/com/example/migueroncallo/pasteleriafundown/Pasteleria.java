package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class Pasteleria extends Activity {

    ImageView imageView, batidora, registradora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasteleria);

        imageView = (ImageView)findViewById(R.id.backgroundImagePasteleria);
        batidora = (ImageView)findViewById(R.id.batidoraImage);
        registradora = (ImageView)findViewById(R.id.registradoraImage);


        batidora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Pasteleria.this, PrepararMezcla.class);
                startActivity(intent);

            }
        });

        registradora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pasteleria.this, PrepararMezcla.class);
                startActivity(intent);
            }
        });








    }


}
