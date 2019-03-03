package com.example.geetinder.efficientfarmingapp;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import android.widget.Toast;

public class Cardpay extends AppCompatActivity {

    private TextInputLayout cardno1;
    private TextInputLayout cardnam1;
    private TextInputLayout carddate1;
    private TextInputLayout cardcvv1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpay);
        img=findViewById(R.id.imageView3);

        cardno1=findViewById(R.id.cardno);
        cardnam1=findViewById(R.id.cardnam);
        cardcvv1=findViewById(R.id.cardcvv);
        carddate1=findViewById(R.id.carddate);


    }
    private boolean validatecardnumber() {
        String  cardname = cardno1.getEditText().getText().toString().trim();
        if(cardname.isEmpty()){
            cardno1.setError("Field can't be empty");
            return false;
        }else if (cardname.length() != 16) {
            cardno1.setError("Card number must have 16 digits");
            return false;
        } else{
            cardno1.setError(null);
            return true;
        }
    }

    private boolean validateuserName() {
        String  username = cardnam1.getEditText().getText().toString().trim();
        if(username.isEmpty()){
            cardnam1.setError("Field can't be empty");
            return false;
        } else{
            cardnam1.setError(null);
            return true;
        }
    }

    private boolean validateuserdate() {
        String  username2 = carddate1.getEditText().getText().toString().trim();
        if(username2.isEmpty()){
            carddate1.setError("Field can't be empty");
            return false;
        } else{
            carddate1.setError(null);
            return true;
        }
    }

    private boolean validatecvv() {
        String  username3 = cardcvv1.getEditText().getText().toString().trim();
        if(username3.isEmpty()){
            cardcvv1.setError("Field can't be empty");
            return false;
        }else if (username3.length() != 3) {
            cardcvv1.setError("CVV must have 3 digits");
            return false;
        } else{
            cardcvv1.setError(null);
            return true;
        }
    }


    public void confirmInput(View v) {
        if(!validatecardnumber() | !validateuserName() | !validateuserdate() | !validatecvv()){
            return;
        }
        String input = "Cardnois : "+cardno1.getEditText().getText().toString();
        input += "\n";
        input+="CardNameis : "+cardnam1.getEditText().getText().toString();
        input += "\n";
        input+="Cardcvvis :"+cardcvv1.getEditText().getText().toString();
        input += "\n";
        input+="CardDateis :"+carddate1.getEditText().getText().toString();

        Toast.makeText(this, "input", Toast.LENGTH_SHORT).show();


    }
}

