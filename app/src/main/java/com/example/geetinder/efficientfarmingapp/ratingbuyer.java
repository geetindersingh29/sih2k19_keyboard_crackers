package com.example.geetinder.efficientfarmingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class ratingbuyer extends AppCompatActivity {
    Spinner spinner1;
    Spinner spinner2;
    Button submit;
    Button chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbuyer);
        spinner1=findViewById(R.id.product_rate);
        spinner2=findViewById(R.id.buyer_rate);
        submit=findViewById(R.id.button);
        chat = findViewById(R.id.buttonchat);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ratingbuyer.this);
                builder.setMessage("Rating is successful.")
                        .setCancelable(false)

                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        });

                AlertDialog reset = builder.create();
                reset.setTitle("Rating");
                reset.show();



            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ratingbuyer.this, chat.class);
                startActivity(intent);
            }
        });
    }
}
