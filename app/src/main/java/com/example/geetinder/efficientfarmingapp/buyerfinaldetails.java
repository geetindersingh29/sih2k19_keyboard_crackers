package com.example.geetinder.efficientfarmingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.example.geetinder.efficientfarmingapp.AccountActivity.SignupActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class buyerfinaldetails extends AppCompatActivity {

    TextView oid, bid, sid, pid, startdate, enddate, price, orderstatus;
    private DatabaseReference mItemDatabase;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerfinaldetails);

        oid = findViewById(R.id.order_number_final);
        bid = findViewById(R.id.bid_final);
        sid = findViewById(R.id.sid_final);
        pid = findViewById(R.id.pid_final);
        startdate = findViewById(R.id.startdate_final);
        enddate = findViewById(R.id.enddate_final);
        price = findViewById(R.id.amount_final);
        orderstatus = findViewById(R.id.order_final);

        sid.setText(getIntent().getStringExtra("sid"));
        bid.setText(getIntent().getStringExtra("bid"));
        pid.setText(getIntent().getStringExtra("pid"));
        oid.setText(getIntent().getStringExtra("oid"));
        startdate.setText(getIntent().getStringExtra("rtdate"));
        enddate.setText(getIntent().getStringExtra("reqdate"));
        price.setText(getIntent().getStringExtra("amount"));
        orderstatus.setText("Success");

        mItemDatabase = FirebaseDatabase.getInstance().getReference("users");
        BusyWait.showBusyWait(buyerfinaldetails.this);
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final user itemref = looping.getValue(user.class);
                    if (itemref.getUid().equalsIgnoreCase(getIntent().getStringExtra("sid"))) {
                        sid.setText(itemref.getName());
                    }
                    if (itemref.getUid().equalsIgnoreCase(getIntent().getStringExtra("bid"))) {
                        bid.setText(itemref.getName());
                    }
                }


                BusyWait.removeBusyWait();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        BusyWait.showBusyWait(buyerfinaldetails.this);
        mItemDatabase = FirebaseDatabase.getInstance().getReference("items");
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final Item itemref = looping.getValue(Item.class);
                    if (itemref.getProductid().equalsIgnoreCase(getIntent().getStringExtra("pid"))) {
                        pid.setText(itemref.getType());
                        BusyWait.removeBusyWait();
                        break;
                    }
                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnSignup = (Button) findViewById(R.id.btn_ok);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyerfinaldetails.this, buyorsell.class));
            }
        });

        /*
        order.setText();
        paymode.setText();
        name.setText();
        contact.setText();
        address.setText();
        description.setText();
        amount.setText();
        returndate.setText();
        requireddate.setText();*/


    }
}








