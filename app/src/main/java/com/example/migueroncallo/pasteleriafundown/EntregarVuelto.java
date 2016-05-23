package com.example.migueroncallo.pasteleriafundown;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class EntregarVuelto extends Activity {

    SharedPreferences preferences;
    int venta,saldo;
    ImageView cupcake,img,caja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregarvuelto);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        preferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        venta = preferences.getInt("venta", 1);
        findViewById(R.id.manoCliente).setOnDragListener(new MyOnDragListener(1, false));
        findViewById(R.id.relativeVuelto).setOnDragListener(new MyOnDragListener(2, false));
        findViewById(R.id.billete1).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.billete2).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.moneda).setOnLongClickListener(new MyOnLongClickListener(false));
        cupcake = (ImageView)findViewById(R.id.cupSale);
        caja = (ImageView)findViewById(R.id.cajaValue);
        saldo = (5000 - (venta*1000));
        assignValueCaja(saldo);

        assignCupcake(venta);

    }

    public void assignCupcake(int venta){

        switch (venta){
            case 1:
                String uri = "@drawable/cupcakeone";

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                cupcake.setImageDrawable(res);
                break;
            case 2:
                String uri2 = "@drawable/cupcaketwo";

                int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
                Drawable res2 = getResources().getDrawable(imageResource2);
                cupcake.setImageDrawable(res2);
                break;
            case 3:
                String uri3 = "@drawable/cupcakethree";

                int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
                Drawable res3 = getResources().getDrawable(imageResource3);
                cupcake.setImageDrawable(res3);
                break;

            default: break;
        }
    }

    public void checkSaldo(int saldo){
        if(!(saldo>0)){
            Toast.makeText(EntregarVuelto.this, "Felicidades! Has entregado el cambio correctamente", Toast.LENGTH_SHORT).show();
            Handler h = new Handler();

            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);

        }
    }
    public void assignValueCaja(int saldo){

        switch (saldo){
            case 4000:
                String uri = "@drawable/caja4000";

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                caja.setImageDrawable(res);
                break;
            case 3500:
                String uri4 = "@drawable/caja3500";

                int imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
                Drawable res4 = getResources().getDrawable(imageResource4);
                caja.setImageDrawable(res4);
                break;
            case 3000:
                String uri2 = "@drawable/caja3000";

                int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
                Drawable res2 = getResources().getDrawable(imageResource2);
                caja.setImageDrawable(res2);
                break;
            case 2500:
                String uri5 = "@drawable/caja2500";

                int imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
                Drawable res5 = getResources().getDrawable(imageResource5);
                caja.setImageDrawable(res5);
                break;
            case 2000:
                String uri3 = "@drawable/caja2000";

                int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
                Drawable res3 = getResources().getDrawable(imageResource3);
                caja.setImageDrawable(res3);
                break;
            case 1500:
                String uri6 = "@drawable/caja1500";

                int imageResource6 = getResources().getIdentifier(uri6, null, getPackageName());
                Drawable res6 = getResources().getDrawable(imageResource6);
                caja.setImageDrawable(res6);
                break;
            case 1000:
                String uri7 = "@drawable/caja1000";

                int imageResource7 = getResources().getIdentifier(uri7, null, getPackageName());
                Drawable res7 = getResources().getDrawable(imageResource7);
                caja.setImageDrawable(res7);
                break;
            case 500:
                String uri8 = "@drawable/caja500";

                int imageResource8 = getResources().getIdentifier(uri8, null, getPackageName());
                Drawable res8 = getResources().getDrawable(imageResource8);
                caja.setImageDrawable(res8);
                break;
            case 0:
                String uri9 = "@drawable/caja0000";

                int imageResource9 = getResources().getIdentifier(uri9, null, getPackageName());
                Drawable res9 = getResources().getDrawable(imageResource9);
                caja.setImageDrawable(res9);
                break;

            default: break;
        }
    }

    class MyOnLongClickListener implements View.OnLongClickListener {
        boolean visible;

        public MyOnLongClickListener(boolean visible) {
            this.visible = visible;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onLongClick(View v) {

            img = (ImageView) findViewById(v.getId());


            ClipData data = ClipData.newPlainText("simple text", "text");

            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);

            v.startDrag(data, sb, v, 0);
            if (visible == false) {
                v.setVisibility(View.INVISIBLE);
            } else {
                v.setVisibility(View.VISIBLE);
            }

            return true;
        }
    }

    class MyOnDragListener implements View.OnDragListener {

        private int num;
        private boolean drop;
        int actualNum = 0;

        public MyOnDragListener(int num, boolean drop) {
            super();
            this.num = num;
            this.drop = drop;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return true;
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    actualNum = num;
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Script", num + " - ACTION_DRAG_LOCATION " + event.getX() + " " + event.getY());
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Script", num + " - ACTION_DRAG_EXITED");
                    if (num == 1) {
                        Log.i("Script", num + " - drop fuera de limite");
                        img.setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DROP:

                    Log.i("Script", num + " - ACTION_DRAG_DROP");
                    drop = true;

                    if (num == 1) {

                        View view = (View) event.getLocalState();

                        switch (view.getId()){

                            case R.id.billete1:

                                if(saldo>=1000){
                                    saldo = saldo - 1000;
                                    assignValueCaja(saldo);
                                }else{
                                    Toast.makeText(EntregarVuelto.this, "Debes entregar menos dinero al cliente", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case R.id.billete2:

                                if (saldo >= 2000){
                                    saldo = saldo - 2000;
                                    assignValueCaja(saldo);
                                }else{
                                    Toast.makeText(EntregarVuelto.this, "Debes entregar menos dinero al cliente", Toast.LENGTH_SHORT).show();
                                }

                                break;
                            case R.id.moneda:
                                if (saldo >= 500){
                                    saldo = saldo - 500;
                                    assignValueCaja(saldo);
                                }else{
                                    Toast.makeText(EntregarVuelto.this, "Debes entregar menos dinero al cliente", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            default:break;
                        }

                        view.setVisibility(View.VISIBLE);

                        checkSaldo(saldo);

                    } else {
                        img.setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }

            return true;
        }
    }
}
