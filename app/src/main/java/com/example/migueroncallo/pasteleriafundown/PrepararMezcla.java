package com.example.migueroncallo.pasteleriafundown;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PrepararMezcla extends Activity {

    ImageView huevo,agua, mantequilla, mezcla;
    float posx = 0, posy = 0;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparar_mezcla);

        findViewById(R.id.relativeMezcla).setOnDragListener(new MyOnDragListener(1, false));
        findViewById(R.id.tazonImage).setOnDragListener(new MyOnDragListener(2, false));

        findViewById(R.id.huevoImage).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.aguaImage).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.mantequillarImage).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.harinaImage).setOnLongClickListener(new MyOnLongClickListener(false));

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
            posx = v.getX();
            posy = v.getY();
            Log.i("posxy", posx + " " + posy);

            ClipData data = ClipData.newPlainText("simple text", "text");

            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);

            v.startDrag(data, sb, v, 0);
            if(visible==false){
                v.setVisibility(View.INVISIBLE);}else{v.setVisibility(View.VISIBLE);}

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
                    if(num==1){
                        Log.i("Script", num + " - drop fuera de limite");
                        img.setVisibility(View.VISIBLE);}
                    break;
                case DragEvent.ACTION_DROP:

                    Log.i("Script",num+" - ACTION_DRAG_DROP");
                    drop=true;

                    if(num!=1) {

                        View view = (View) event.getLocalState();
                        if (R.id.huevoImage == view.getId()){
                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Un huevo añadido");
                        }

                        if (R.id.aguaImage == view.getId()){
                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Un vaso de agua añadido");
                        }

                        if (R.id.harinaImage == view.getId()){

                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Una taza de harina añadida");

                        }

                        if (R.id.mantequillarImage == view.getId()){

                            view.setVisibility(View.VISIBLE);
                            Log.i("element dropped", "Una medida de mantquilla añadida");

                        }

                    }else{
                        img.setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.BLUE);
                    break;
            }

            return true;
        }
    }
}
