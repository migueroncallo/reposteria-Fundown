package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Pasteleria extends Activity {

    ImageView imageView, batidora, registradora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasteleria);

        imageView = (ImageView)findViewById(R.id.backgroundImagePasteleria);
        batidora = (ImageView)findViewById(R.id.batidoraImage);
        registradora = (ImageView)findViewById(R.id.registradoraImage);
        generarVenta();
        final android.os.Handler handler = new android.os.Handler();

        final Runnable highlight = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Flash).duration(1000).delay(2000).playOn(findViewById(R.id.batidoraImage));
                YoYo.with(Techniques.Flash).duration(1000).delay(3000).playOn(findViewById(R.id.registradoraImage));
                handler.postDelayed(this, 10000);
            }
        };

        handler.postDelayed(highlight, 2000);





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
                Intent intent = new Intent(Pasteleria.this, VenderCupcake.class);
                startActivity(intent);
            }
        });








    }

    @Override
    protected void onResume() {
        super.onResume();
        generarVenta();
    }

    public void generarVenta(){
        Random r = new Random();
        int valor = r.nextInt(4 - 1) + 1;

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("venta", valor);
        editor.commit();
    }


}
