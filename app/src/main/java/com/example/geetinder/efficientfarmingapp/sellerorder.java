package com.example.geetinder.efficientfarmingapp;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sellerorder extends AppCompatActivity {
    private DatabaseReference mItemDatabase;
    private DatabaseReference mUserDatabase;
    private solditemsadapter mAdapter;
    private ListView itemlist;
    private SoldItems soldItems;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerorder);

        mItemDatabase = FirebaseDatabase.getInstance().getReference("items");
        mUserDatabase = FirebaseDatabase.getInstance().getReference("users");



        itemlist = (ListView) findViewById(R.id.list);

        final ArrayList<SoldItems> arraylistitems = new ArrayList<>();

        soldItems = new SoldItems();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot looping:dataSnapshot.getChildren())
                {
                    final Item itemref = looping.getValue(Item.class);

                    if(itemref.getOwnerid().equals(userId))
                    {
                        soldItems.setCompanyname(itemref.getProductinfo());
                        soldItems.setType(itemref.getType());
                        soldItems.setPriceperday(String.valueOf(itemref.getPayPerDay()));



                        mUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot looping:dataSnapshot.getChildren()) {
                                    String uid = looping.child("uid").getValue(String.class);
                                    String name = looping.child("name").getValue(String.class);

                                    if (uid.equals(userId)) {
                                        soldItems.setName(name);

                                        arraylistitems.add(soldItems);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        SoldItems obj = new SoldItems("Geet", "20", "ABC", "DEF");
        arraylistitems.add(obj);
        mAdapter = new solditemsadapter(this, arraylistitems);
        itemlist.setAdapter(mAdapter);




    }
}