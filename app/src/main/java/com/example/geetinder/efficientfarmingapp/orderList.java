package com.example.geetinder.efficientfarmingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class orderList extends AppCompatActivity {

    FirebaseDatabase database;
    private DatabaseReference mItemDatabase;
    private order_adapter mAdapter;
    private ListView itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        BusyWait.showBusyWait(orderList.this);
        mItemDatabase = FirebaseDatabase.getInstance().getReference("order");
        itemlist = (ListView) findViewById(R.id.order_list);
        final ArrayList<orders> arraylistitems = new ArrayList<>();
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final orders orderref = looping.getValue(orders.class);
                    String uid = FirebaseAuth.getInstance().getUid();
                    String mode = getIntent().getExtras().getString("mode");
                    DataModel.ordermode = mode;

                    if (mode.equalsIgnoreCase("buyer")) {
                        if (orderref.getBid().equalsIgnoreCase(uid)) {
                            arraylistitems.add(orderref);
                        }
                    } else {
                        if (orderref.getSid().equalsIgnoreCase(uid)) {
                            arraylistitems.add(orderref);
                        }
                    }

                }
                mAdapter = new order_adapter(orderList.this, arraylistitems);
                itemlist.setAdapter(mAdapter);
                BusyWait.removeBusyWait();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                orders currentItem = mAdapter.getItem(i);

                if (DataModel.ordermode.equalsIgnoreCase("buyer")) {
                    startActivity(new Intent(orderList.this, ratingbuyer.class));
                } else {
                    startActivity(new Intent(orderList.this, rating.class));
                }

//                Intent intent = new Intent(orderList.this, chat.class);
//                intent.putExtra("oid", ((orders) currentItem).getOid());
                DataModel.order_chat_id = ((orders) currentItem).getOid();
                UserDetails.orderid = DataModel.order_chat_id;
//                startActivity(intent);
            }
        });
    }
}
