package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class PrepararMezcla extends Activity {

    TextView harina, mantequilla, huevo, agua;
    float posx = 0, posy = 0;
    int huevoCount = 0, huevoNeeded = 2, aguaCount = 0, aguaNeeded = 3, harinaCount = 0, harinaNeeded = 1, mantequillaCount = 0, mantequillaNeeded = 2, gameCount = 0;
    ImageView img, cupcakeCount, tazon,animationEgg, animationCongrats;
    ImageButton sonido, home;
    boolean huevoOk = false, aguaOk = false, harinaOk = false, mantequillaOk = false, gameFinished = false;
    AnimationDrawable anim,anim2,anim3;
    SharedPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparar_mezcla);

        findViewById(R.id.relativeMezcla).setOnDragListener(new MyOnDragListener(1, false));
        tazon = (ImageView)findViewById(R.id.tazonImage);
        tazon.setOnDragListener(new MyOnDragListener(2, false));
        findViewById(R.id.huevoImage).setOnTouchListener(new MyOnTouchListener(false));
        findViewById(R.id.aguaImage).setOnTouchListener(new MyOnTouchListener(false));
        findViewById(R.id.mantequillarImage).setOnTouchListener(new MyOnTouchListener(false));
        findViewById(R.id.harinaImage).setOnTouchListener(new MyOnTouchListener(false));
        animationEgg = (ImageView)findViewById(R.id.eggAnimation);
        sonido = (ImageButton)findViewById(R.id.soundButtonPrepare);
        home = (ImageButton)findViewById(R.id.homeButtonPrepare);
        animationCongrats = (ImageView)findViewById(R.id.congratsAnimation);


        animationEgg.setBackgroundResource(R.drawable.animatedegg);
        anim = (AnimationDrawable) animationEgg.getBackground();
        animationEgg.setVisibility(View.INVISIBLE);

        animationCongrats.setBackgroundResource(R.drawable.animatedcongrats);
        anim3 = (AnimationDrawable) animationCongrats.getBackground();
        animationCongrats.setVisibility(View.INVISIBLE);

        harina = (TextView) findViewById(R.id.harinaCount);
        mantequilla = (TextView) findViewById(R.id.mantequillaCount);
        huevo = (TextView) findViewById(R.id.huevoCount);
        agua = (TextView) findViewById(R.id.aguaCount);

        harina.setText(String.valueOf(harinaNeeded));
        mantequilla.setText(String.valueOf(mantequillaNeeded));
        huevo.setText(String.valueOf(huevoNeeded));
        agua.setText(String.valueOf(aguaNeeded));

        cupcakeCount = (ImageView) findViewById(R.id.cupcakeCount);

        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSoundActive = appPreferences.getBoolean("isSoundActive", false);
        General.manageBackgroundMusic(isSoundActive);

        if (isSoundActive) {
            sonido.setImageResource(R.drawable.btn_sonido_on);
        } else {
            sonido.setImageResource(R.drawable.btn_sonido_off);
        }
//        Toast.makeText(PrepararMezcla.this,"Vamos a preparar un cupcake.", Toast.LENGTH_LONG).show();

        activityHint();


        sonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                appPreferences = PreferenceManager.getDefaultSharedPreferences(PrepararMezcla.this);
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

    }

    public void activityHint(){
        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeIn).duration(3000).playOn(findViewById(R.id.cupcakeCount));
                YoYo.with(Techniques.FadeOut).delay(3000).duration(1500).playOn(findViewById(R.id.cupcakeCount));
            }
        }, 0);
    }

    public void finishActivity(){
        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();;
            }
        }, 5000);
    }

    public void animateEgg(){

        animationEgg.setVisibility(View.VISIBLE);
        anim.setVisible(true, true);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationEgg.setVisibility(View.INVISIBLE);
            }
        },2500);

    }

    public void animateBowl(){
        tazon.setImageDrawable(null);
        tazon.setBackgroundResource(R.drawable.animatedbowl);
        anim2 = (AnimationDrawable)tazon.getBackground();
        anim2.start();
    }


    class MyOnTouchListener implements View.OnTouchListener{

        boolean visible;

        public MyOnTouchListener(boolean visible) {
            this.visible = visible;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            img = (ImageView) findViewById(v.getId());
            posx = v.getX();
            posy = v.getY();
            Log.i("posxy", posx + " " + posy);

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

    public void acceptRecipee(boolean egg, boolean water, boolean flour, boolean butter) {

        boolean finished = false;
        if (egg && water && flour && butter && !gameFinished && gameCount == 0) {
//            Toast.makeText(PrepararMezcla.this, "Felicidades ya has completado la receta", Toast.LENGTH_LONG).show();
            huevoCount = 0;
            aguaCount = 0;
            harinaCount = 0;
            mantequillaCount = 0;
            Random r = new Random();
            final int multiplicador = r.nextInt(4 - 1) + 1;

            switch (multiplicador) {
                case 1:

                    String uri = "@drawable/cupcakeuno";

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    cupcakeCount.setImageDrawable(res);

                    Handler h = new Handler();

                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activityHint();

                        }
                    }, 2000);
                    break;

                case 2:
                    String uri2 = "@drawable/cupcakedos";

                    int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
                    Drawable res2 = getResources().getDrawable(imageResource2);
                    cupcakeCount.setImageDrawable(res2);
                    Handler h2 = new Handler();

                    h2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activityHint();

                        }
                    }, 2000);                    break;

                case 3:

                    String uri3 = "@drawable/cupcaketres";

                    int imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
                    Drawable res3 = getResources().getDrawable(imageResource3);
                    cupcakeCount.setImageDrawable(res3);
                    Handler h3 = new Handler();

                    h3.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activityHint();

                        }
                    }, 2000);
                    break;

                default:
                    break;
            }

//            Toast.makeText(PrepararMezcla.this, "Ahora vamos a preparar " + String.valueOf(multiplicador) + " cupcakes.", Toast.LENGTH_LONG).show();

                Handler h = new Handler();

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        huevoNeeded = huevoNeeded * multiplicador;
                        aguaNeeded = aguaNeeded * multiplicador;
                        harinaNeeded = harinaNeeded * multiplicador;
                        mantequillaNeeded = mantequillaNeeded * multiplicador;

                        harina.setText(String.valueOf(harinaNeeded));
                        mantequilla.setText(String.valueOf(mantequillaNeeded));
                        huevo.setText(String.valueOf(huevoNeeded));
                        agua.setText(String.valueOf(aguaNeeded));

                    }
                }, 1000);





            gameFinished = true;
            huevoOk = false;
            aguaOk = false;
            harinaOk = false;
            mantequillaOk = false;
            gameFinished = true;
            gameCount++;

            String uri = "@drawable/tazon";

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            tazon.setImageDrawable(res);
        } else {
            if (egg && water && flour && butter && gameFinished && (gameCount > 0)) {
//                Toast.makeText(PrepararMezcla.this, "Felicidades has finalizado el juego!", Toast.LENGTH_LONG).show();
                String uri = "@drawable/cupcakelisto";

                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                cupcakeCount.setImageDrawable(res);

                animateBowl();



                Handler h = new Handler();

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activityHint();

                    }
                }, 6000);

                Handler h1 = new Handler();

                h1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationCongrats.setVisibility(View.VISIBLE);
                        anim3.setVisible(true, true);
                    }
                }, 9000);

                Handler h2 = new Handler();

                h2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 15000);






            }

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

                    if (num != 1) {

                        View view = (View) event.getLocalState();
                        if (R.id.huevoImage == view.getId()) {
                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Un huevo añadido");

                            if (huevoCount < huevoNeeded) {

                                huevoCount++;
                                huevo.setText(String.valueOf(huevoNeeded - huevoCount));
                                YoYo.with(Techniques.ZoomIn).duration(1000).playOn(huevo);

                                animateEgg();




                                String uri = "@drawable/tazonmezcla";

                                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                Drawable res = getResources().getDrawable(imageResource);
                                tazon.setImageDrawable(res);

                                if (huevoCount == huevoNeeded) {
                                    huevoOk = true;
                                }

                            } else {
                                YoYo.with(Techniques.Wobble).duration(2000).playOn(view);
                                Toast.makeText(PrepararMezcla.this, "Ya agregaste los huevos necesarios", Toast.LENGTH_LONG).show();

                            }
                        }

                        if (R.id.aguaImage == view.getId()) {
                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Un vaso de agua añadido");

                            if (aguaCount < aguaNeeded) {

                                aguaCount++;
                                agua.setText(String.valueOf(aguaNeeded - aguaCount));
                                YoYo.with(Techniques.ZoomIn).duration(1000).playOn(agua);

                                String uri = "@drawable/tazonmezcla";

                                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                Drawable res = getResources().getDrawable(imageResource);
                                tazon.setImageDrawable(res);

                                if (aguaCount == aguaNeeded) {
                                    aguaOk = true;
                                }

                            } else {
                                YoYo.with(Techniques.Wobble).duration(2000).playOn(view);
                                Toast.makeText(PrepararMezcla.this, "Ya agregaste la cantidad de agua necesaria", Toast.LENGTH_LONG).show();
                            }
                        }

                        if (R.id.harinaImage == view.getId()) {

                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Una taza de harina añadida");

                            if (harinaCount < harinaNeeded) {


                                harinaCount++;
                                harina.setText(String.valueOf(harinaNeeded - harinaCount));
                                YoYo.with(Techniques.ZoomIn).duration(1000).playOn(harina);

                                String uri = "@drawable/tazonmezcla";

                                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                Drawable res = getResources().getDrawable(imageResource);
                                tazon.setImageDrawable(res);

                                if (harinaCount == harinaNeeded) {
                                    harinaOk = true;
                                }

                            } else {
                                YoYo.with(Techniques.Wobble).duration(2000).playOn(view);
                                Toast.makeText(PrepararMezcla.this, "Ya agregaste la cantidad de harina necesaria", Toast.LENGTH_LONG).show();
                            }

                        }

                        if (R.id.mantequillarImage == view.getId()) {

                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Una medida de mantquilla añadida");

                            if (mantequillaCount < mantequillaNeeded) {


                                        mantequillaCount++;
                                        mantequilla.setText(String.valueOf(mantequillaNeeded - mantequillaCount));
                                        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(mantequilla);


                                String uri = "@drawable/tazonmezcla";

                                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                Drawable res = getResources().getDrawable(imageResource);
                                tazon.setImageDrawable(res);

                                if (mantequillaCount == mantequillaNeeded) {
                                    mantequillaOk = true;
                                }

                            } else {
                                YoYo.with(Techniques.Wobble).duration(2000).playOn(view);
                                Toast.makeText(PrepararMezcla.this, "Ya agregaste la cantidad de mantequilla necesaria", Toast.LENGTH_LONG).show();
                            }

                        }

                    } else {
                        img.setVisibility(View.VISIBLE);
                    }

                    acceptRecipee(huevoOk, aguaOk, harinaOk, mantequillaOk);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }

            return true;
        }
    }
}
