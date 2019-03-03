package com.example.geetinder.efficientfarmingapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.geetinder.efficientfarmingapp.R;

public class Welcome extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2500;
    MediaPlayer ourSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ourSound = MediaPlayer.create(this,R.raw.speechtwo);
        ourSound.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent= new Intent(Welcome.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    @Override
    protected void onPause(){
        super.onPause();
        ourSound.release();
        finish();
    }
}
