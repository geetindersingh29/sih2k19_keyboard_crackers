package com.example.geetinder.efficientfarmingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.example.geetinder.efficientfarmingapp.AccountActivity.ResetPassword;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class order_adapter extends ArrayAdapter<orders> {

    private DatabaseReference mItemDatabase;

    public order_adapter(Context context, List<orders> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if there is an existing list item view that we can reuse
        //Otherwise, if covertView is null then inflate a new list item layout
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.order_layout, parent, false);
        }

        //Find the item at the given position in the list of items
        final orders currentorder = (orders) getItem(position);


        ((TextView) listItemView.findViewById(R.id.order_oid)).setText(currentorder.getOid() + "");
        ((TextView) listItemView.findViewById(R.id.order_pid)).setText(currentorder.getPid() + "");
        ((TextView) listItemView.findViewById(R.id.order_bid)).setText(currentorder.getBid() + "");
        ((TextView) listItemView.findViewById(R.id.order_sid)).setText(currentorder.getSid() + "");
        ((TextView) listItemView.findViewById(R.id.order_startdate)).setText(currentorder.getDatestart() + "");
        ((TextView) listItemView.findViewById(R.id.order_enddate)).setText(currentorder.getDateend() + "");
        ((TextView) listItemView.findViewById(R.id.order_amount)).setText(currentorder.getAmount() + "");
        ((TextView) listItemView.findViewById(R.id.order_sellerrating)).setText(currentorder.getSellerrating() + "");
        ((TextView) listItemView.findViewById(R.id.order_buyerrating)).setText(currentorder.getBuyerrating() + "");
        ((TextView) listItemView.findViewById(R.id.order_productrating)).setText(currentorder.getProductrating() + "");
        ((TextView) listItemView.findViewById(R.id.order_status)).setText(currentorder.getOrderstatus() + "");

        final TextView bidtv = ((TextView) listItemView.findViewById(R.id.order_bid));
        final TextView sidtv = ((TextView) listItemView.findViewById(R.id.order_sid));
        final TextView pidtv = ((TextView) listItemView.findViewById(R.id.order_pid));


        mItemDatabase = FirebaseDatabase.getInstance().getReference("users");
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final user itemref = looping.getValue(user.class);
                    if (itemref.getUid().equalsIgnoreCase(currentorder.getBid())) {
                        bidtv.setText(itemref.getName() + "");
                    }
                    if (itemref.getUid().equalsIgnoreCase(currentorder.getSid())) {
                        sidtv.setText(itemref.getName() + "");
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mItemDatabase = FirebaseDatabase.getInstance().getReference("items");
        mItemDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot looping : dataSnapshot.getChildren()) {
                    final Item itemref = looping.getValue(Item.class);
                    if (itemref.getProductid().equalsIgnoreCase(currentorder.getPid())) {
                        pidtv.setText(itemref.getType()+"");
                        break;
                    }
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listItemView;

    }
}
