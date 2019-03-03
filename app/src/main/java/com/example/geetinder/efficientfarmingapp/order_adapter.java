package com.example.geetinder.efficientfarmingapp;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class order_adapter extends ArrayAdapter<orders> {

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
            orders currentorder = (orders) getItem(position);


            ((TextView) listItemView.findViewById(R.id.order_oid)).setText("Order id : "+currentorder.getOid());
            ((TextView) listItemView.findViewById(R.id.order_pid)).setText("Product : "+currentorder.getPid());
            ((TextView) listItemView.findViewById(R.id.order_bid)).setText("Buyer : "+currentorder.getBid());
            ((TextView) listItemView.findViewById(R.id.order_sid)).setText("Seller : "+currentorder.getSid());
            ((TextView) listItemView.findViewById(R.id.order_startdate)).setText("Start Date : "+currentorder.getDatestart());
            ((TextView) listItemView.findViewById(R.id.order_enddate)).setText("End Date : "+currentorder.getDateend());
            ((TextView) listItemView.findViewById(R.id.order_amount)).setText("Amount : "+currentorder.getAmount());
            ((TextView) listItemView.findViewById(R.id.order_sellerrating)).setText("Seller Rating : "+currentorder.getSellerrating());
            ((TextView) listItemView.findViewById(R.id.order_buyerrating)).setText("Buyer Rating : "+currentorder.getBuyerrating());
            ((TextView) listItemView.findViewById(R.id.order_productrating)).setText("Product Rating : "+currentorder.getProductrating());
            ((TextView) listItemView.findViewById(R.id.order_status)).setText("Status : "+currentorder.getOrderstatus());
            return listItemView;

        }
    }
