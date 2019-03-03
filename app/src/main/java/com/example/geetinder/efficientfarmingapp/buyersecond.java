package com.example.geetinder.efficientfarmingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class buyersecond extends AppCompatActivity {
    Button b1, b2;
    SearchView mysearchview;
    FirebaseDatabase database;
    private DatabaseReference mItemDatabase;
    private ItemAdapter mAdapter;
    private ListView itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyersecond);

        mysearchview = findViewById(R.id.search_id);


        mItemDatabase = FirebaseDatabase.getInstance().getReference("items");

        itemlist = (ListView) findViewById(R.id.list);


        final ArrayList<Item> arraylistitems = new ArrayList<>();

        BusyWait.showBusyWait(buyersecond.this);
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final Item itemref = looping.getValue(Item.class);
                    arraylistitems.add(itemref);
                }
                mAdapter = new ItemAdapter(buyersecond.this, arraylistitems);
                itemlist.setAdapter(mAdapter);

                BusyWait.removeBusyWait();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //This is the function to search

        mysearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                boolean found = false;

                ArrayList<Item> searchList = new ArrayList<>();

                for (Item i : arraylistitems) {
                    if (i.getType().toLowerCase().contains(query.toLowerCase()) || String.valueOf(i.getPayPerDay()).contains(query)) {
                        found = true;
                        searchList.add(i);

                    }
                }
                if (found) {

                    mAdapter = new ItemAdapter(buyersecond.this, searchList);
                    itemlist.setAdapter(mAdapter);

                } else {
                    Toast.makeText(buyersecond.this, "Searched Item Not found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //mAdapter.getFilter().filter(newText);
                if (newText.length() == 0) {
                    mAdapter = new ItemAdapter(buyersecond.this, arraylistitems);
                    itemlist.setAdapter(mAdapter);
                }
                return false;
            }


        });


        //Click Listener for each item of list
        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Item currentItem = mAdapter.getItem(i);

//                String cName = currentItem.getCompany();
//                String imageUrl = currentItem.getmImageUrl();
//                String modelNumber = currentItem.getModelNumber();
//                int pricePerDay = currentItem.getPayPerDay();
//                String type = currentItem.getType();
//                String uID = currentItem.getUserId();

                Intent intent = new Intent(buyersecond.this, buyerorderdate.class);
                intent.putExtra("bid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                intent.putExtra("sid", currentItem.getOwnerid());
                intent.putExtra("pid", currentItem.getProductid());
                intent.putExtra("type", currentItem.getType());
                intent.putExtra("deposit", currentItem.getDeposit());
                intent.putExtra("productinfo", currentItem.getProductinfo());
                intent.putExtra("priceperday", currentItem.getPayPerDay());
                intent.putExtra("image", currentItem.getmImageUrl());
                intent.putExtra("fromdate", currentItem.getFromdate());
                intent.putExtra("todate", currentItem.getTodate());
                intent.putExtra("productstartotal", currentItem.getProductstartotal());
                intent.putExtra("productstarcount", currentItem.getProductstarcount());
                DataModel.bid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DataModel.sid = currentItem.getOwnerid();
                DataModel.pid = currentItem.getProductid();
                DataModel.type = currentItem.getType();
                DataModel.productinfo = currentItem.getProductinfo();
                DataModel.image = currentItem.getmImageUrl();
                DataModel.fromdate = currentItem.getFromdate();
                DataModel.todate = currentItem.getTodate();
                DataModel.productstarcount = currentItem.getProductstarcount();
                DataModel.productstartotal = currentItem.getProductstartotal();
                DataModel.priceperday = currentItem.getPayPerDay();
                DataModel.deposit = currentItem.getDeposit();

                if (DataModel.deposit == 0) DataModel.deposit = 1000;
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.buyermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.order:
                Intent intent = new Intent(buyersecond.this, orderList.class);
                intent.putExtra("mode", "buyer");
                startActivity(intent);
                break;
            case R.id.edit_account:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(buyersecond.this, LoginActivity.class));
//                Toast.makeText(this, "Edit Account clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notify:
                startActivity(new Intent(buyersecond.this, tech.class));
                break;
        }
        return true;
    }

}
