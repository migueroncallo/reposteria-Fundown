package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.Random;

public class PasteleriaHome extends Activity {

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
        startLightsAnimation(findViewById(R.id.backLightBatidora));
        startLightsAnimation(findViewById(R.id.backLightRegistradora));

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
                appPreferences = PreferenceManager.getDefaultSharedPreferences(PasteleriaHome.this);
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

                Intent intent = new Intent(PasteleriaHome.this, PasteleriaPrepararMezcla.class);
                startActivity(intent);

            }
        });

        registradora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasteleriaHome.this, PasteleriaVenderCupcake.class);
                startActivity(intent);
            }
        });








    }

    public void startLightsAnimation(View mStartButtonLights) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mStartButtonLights, "rotation", 0f, 360f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(6000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        mStartButtonLights.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animator.start();
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
