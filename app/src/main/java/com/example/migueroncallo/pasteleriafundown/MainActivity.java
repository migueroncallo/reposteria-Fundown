package com.example.migueroncallo.pasteleriafundown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button startGame;
    SharedPreferences appPreferences;
    boolean isAppInstalled = false;
    private boolean mSoundState = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = (Button)findViewById(R.id.startGameButton);

        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled", false);

        if (isAppInstalled == false) {
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
        }

        // initialize
        General.initialize(getApplicationContext());
        General.manageBackgroundMusic(mSoundState);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pasteleria.class);
                startActivity(intent);
            }
        });
    }
}
