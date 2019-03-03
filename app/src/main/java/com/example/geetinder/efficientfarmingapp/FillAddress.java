package com.example.geetinder.efficientfarmingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.example.geetinder.efficientfarmingapp.AccountActivity.ResetPassword;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillAddress extends AppCompatActivity {

    private EditText name,city,state;
    private Button proceed;
    private DatabaseReference rootRef;

    private String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_address);

        phonenum = getIntent().getExtras().getString("Phone Number");

        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        proceed = findViewById(R.id.btn_proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FillAddress.this);
                    builder.setMessage("Please Enter Your Name!!")
                            .setCancelable(false)

                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }

                            });

                    AlertDialog reset = builder.create();
                    reset.setTitle("Enter Name");
                    reset.show();
                }

                else if(TextUtils.isEmpty(city.getText().toString()))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FillAddress.this);
                    builder.setMessage("Please Enter Your City!!")
                            .setCancelable(false)

                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }

                            });

                    AlertDialog reset = builder.create();
                    reset.setTitle("Enter City");
                    reset.show();
                }

                else if(TextUtils.isEmpty(state.getText().toString()))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FillAddress.this);
                    builder.setMessage("Please Enter Your State!!")
                            .setCancelable(false)

                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }

                            });

                    AlertDialog reset = builder.create();
                    reset.setTitle("Enter State");
                    reset.show();
                }
                else {
                    rootRef = FirebaseDatabase.getInstance().getReference("users");

                    String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    String id = rootRef.push().getKey();
                    rootRef.child(id).child("name").setValue(name.getText().toString());
                    rootRef.child(id).child("city").setValue(city.getText().toString());
                    rootRef.child(id).child("uid").setValue(uID);
                    rootRef.child(id).child("state").setValue(state.getText().toString());
                    rootRef.child(id).child("Phone Number").setValue(phonenum);
                    rootRef.child(id).child("seller_rating_total").setValue(0);
                    rootRef.child(id).child("seller_rating_count").setValue(0);
                    rootRef.child(id).child("buyer_rating_total").setValue(0);
                    rootRef.child(id).child("buyer_rating_count").setValue(0);
                    rootRef.child(id).child("seller_rating_total").setValue(0);
                    BusyWait.showBusyWait(FillAddress.this);
                    startActivity(new Intent(FillAddress.this, buyorsell.class));
                }
            }
        });
    }
}
