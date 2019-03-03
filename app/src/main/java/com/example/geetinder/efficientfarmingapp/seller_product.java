package com.example.geetinder.efficientfarmingapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class seller_product extends AppCompatActivity {
    FirebaseDatabase database;
    private DatabaseReference mItemDatabase;
    private seller_item_adapter mAdapter;
    private ListView itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product);

        mItemDatabase = FirebaseDatabase.getInstance().getReference("items");
        itemlist = (ListView) findViewById(R.id.list);
        final ArrayList<Item> arraylistitems = new ArrayList<>();
        BusyWait.showBusyWait(seller_product.this);
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final Item itemref = looping.getValue(Item.class);
                    String uid = FirebaseAuth.getInstance().getUid();
                    //Toast.makeText(getApplicationContext(),itemref.getUserId()+itemref.getCompany()+itemref.getmImageUrl(),Toast.LENGTH_LONG).show();
                    if (itemref.getOwnerid() != null && itemref.getOwnerid().equalsIgnoreCase(uid)) {
                        arraylistitems.add(itemref);
                    }
                }
                mAdapter = new seller_item_adapter(seller_product.this, arraylistitems);
                itemlist.setAdapter(mAdapter);
                BusyWait.removeBusyWait();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
