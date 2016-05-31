package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
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
    ImageButton sonido, home;

    private boolean mSoundState = false;
    SharedPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasteleria);

        sonido = (ImageButton)findViewById(R.id.soundButton);
        home = (ImageButton) findViewById(R.id.homeButton);


        imageView = (ImageView)findViewById(R.id.backgroundImagePasteleria);
        batidora = (ImageView)findViewById(R.id.batidoraImage);
        registradora = (ImageView)findViewById(R.id.registradoraImage);


        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSoundActive = appPreferences.getBoolean("isSoundActive", false);
        General.manageBackgroundMusic(isSoundActive);

        if (isSoundActive) {
            sonido.setImageResource(R.drawable.btn_sonido_on);
        } else {
            sonido.setImageResource(R.drawable.btn_sonido_off);
        }
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



        sonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences = PreferenceManager.getDefaultSharedPreferences(Pasteleria.this);
                boolean isSoundActive = appPreferences.getBoolean("isSoundActive", false);
                if (isSoundActive){
                    sonido.setImageResource(R.drawable.btn_sonido_off);
                } else {
                    sonido.setImageResource(R.drawable.btn_sonido_on);
                }
                isSoundActive = !isSoundActive;

                SharedPreferences.Editor editor = appPreferences.edit();
                editor.putBoolean("isSoundActive", isSoundActive);
                editor.commit();
                General.manageBackgroundMusic(isSoundActive);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




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
