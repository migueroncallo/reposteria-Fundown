package com.example.migueroncallo.pasteleriafundown;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by migueroncallo on 5/30/16.
 */
public class General {

    public static MediaPlayer bgm;


    static void  initialize(Context context){
        bgm = MediaPlayer.create(context, R.raw.background);
        bgm.setLooping(true);
        bgm.start();
    }

    static void  manageBackgroundMusic(boolean state){
        if (state){
            bgm.start();
        } else {
            bgm.pause();
        }
    }


}
