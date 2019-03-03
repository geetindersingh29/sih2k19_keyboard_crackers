package com.example.geetinder.efficientfarmingapp.farming_tech_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.geetinder.efficientfarmingapp.R;

public class ScrollingActivity extends AppCompatActivity {

    private static ImageView imgA;
    private static ImageView imgB;
    private static ImageView imgC;
    private static ImageView imgD;
    private static ImageView imgE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        click1();
        click2();
        click3();
        click4();
        click5();
    }
    public void click1()
    {
        imgA  = (ImageView)findViewById(R.id.img1);
        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.soil_types.soil_tech");
                startActivity(intent);

            }
        });
    }

    public void click2()
    {
        imgB  = (ImageView)findViewById(R.id.img2);
        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.mOrganic_production.mOrganic_production");
                startActivity(intent);

            }
        });
    }
    public void click3()
    {
        imgC  = (ImageView)findViewById(R.id.img3);
        imgC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.oFertiliser.oFertiliser");
                startActivity(intent);

            }
        });
    }
    public void click4()
    {
        imgD  = (ImageView)findViewById(R.id.img4);
        imgD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.credit.kisan");
                startActivity(intent);

            }
        });
    }
    public void click5()
    {
        imgE  = (ImageView)findViewById(R.id.img5);
        imgE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.fertiliser.liquid");
                startActivity(intent);

            }
        });
    }

}
