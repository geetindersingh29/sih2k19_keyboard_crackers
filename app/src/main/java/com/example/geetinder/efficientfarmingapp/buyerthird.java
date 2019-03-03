package com.example.geetinder.efficientfarmingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class buyerthird extends AppCompatActivity {
    Button submit;
    String pricePerDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerthird);
        submit = findViewById(R.id.submit);


        String name = getIntent().getStringExtra("company");
        String imageUrl = getIntent().getStringExtra("image");
        String model = getIntent().getStringExtra("model");
        String type =getIntent().getStringExtra("type");
        final String uID = getIntent().getStringExtra("uid");
        pricePerDay = getIntent().getStringExtra("pp");

        TextView nameText = findViewById(R.id.company);
        TextView typeText = findViewById(R.id.type);
        TextView modelText = findViewById(R.id.model);
        TextView ppDayText = findViewById(R.id.ppDay);

        ImageView img = findViewById(R.id.image_edit);

        nameText.setText(name);
        typeText.setText(type);
        modelText.setText(model);
        ppDayText.setText(pricePerDay);


        Glide.with(this.getApplicationContext()).load(imageUrl).into(img);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buyerthird.this, buyerorderdate.class);
                intent.putExtra("price",pricePerDay);
                intent.putExtra("uid", uID);
                startActivity(intent);
            }
        });



    }
}