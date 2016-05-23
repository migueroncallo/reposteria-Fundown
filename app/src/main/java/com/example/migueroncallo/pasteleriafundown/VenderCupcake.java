package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class VenderCupcake extends Activity {

    ImageView cupcakeVenta, pago;
    SharedPreferences preferences;
    int venta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_cupcake);

        cupcakeVenta = (ImageView)findViewById(R.id.cupVenta);
        pago = (ImageView)findViewById(R.id.clientePago);

        preferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        venta = preferences.getInt("venta", 1);

        assignCupcake(venta);



        clientEnter();

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(VenderCupcake.this, EntregarVuelto.class);
                startActivity(intent);
                finish();
            }
        }, 7000);

    }

    public void clientEnter(){

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeInLeft).duration(4000).playOn(pago);

            }
        }, 0);
    }

    public void assignCupcake(int venta){

        switch (venta){
            case 1:
                String uri = "@drawable/cupcakeone";

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                cupcakeVenta.setImageDrawable(res);
                break;
            case 2:
                String uri2 = "@drawable/cupcaketwo";

                int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
                Drawable res2 = getResources().getDrawable(imageResource2);
                cupcakeVenta.setImageDrawable(res2);
                break;
            case 3:
                String uri3 = "@drawable/cupcakethree";

                int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
                Drawable res3 = getResources().getDrawable(imageResource3);
                cupcakeVenta.setImageDrawable(res3);
                break;

            default: break;
        }
    }
}
