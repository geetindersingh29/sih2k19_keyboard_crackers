package com.example.geetinder.efficientfarmingapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.logging.Filter;

public class seller_item_adapter extends ArrayAdapter<Item> {

    public seller_item_adapter(Context context, List<Item> items) {
        super(context, 0, items);
    }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Check if there is an existing list item view that we can reuse
            //Otherwise, if covertView is null then inflate a new list item layout
            View listItemView = convertView;

            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.seller_product_layout, parent, false);
            }

            //Find the item at the given position in the list of items
            Item currentItem = (Item) getItem(position);

            ImageView image = (ImageView) listItemView.findViewById(R.id.image);

            if(image!=null){
                Glide.with(getContext()).load(currentItem.getmImageUrl()).into(image);
            }

            TextView type = (TextView) listItemView.findViewById(R.id.type_seller);
            type.setText(currentItem.getType());

            TextView pricepd = (TextView) listItemView.findViewById(R.id.ppd);
            pricepd.setText(String.valueOf(currentItem.getPayPerDay()));

            TextView deposit = (TextView) listItemView.findViewById(R.id.deposit);
            deposit.setText(String.valueOf(currentItem.getDeposit()));

            TextView description = (TextView) listItemView.findViewById(R.id.description);
            description.setText(String.valueOf(currentItem.getProductinfo()));

            TextView fromdate = (TextView) listItemView.findViewById(R.id.fromdate);
            fromdate.setText(String.valueOf(currentItem.getFromdate()));

            TextView todate = (TextView) listItemView.findViewById(R.id.todate);
            todate.setText(String.valueOf(currentItem.getTodate()));

//            TextView rating = (TextView) listItemView.findViewById(R.id.rating);
//            if(rating!=null){
//                rating.setText(currentItem.getType());
//            }

            //Return the list item view that is now showing the appropriate data
            return listItemView;

        }
    }
