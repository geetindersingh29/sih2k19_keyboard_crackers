package com.example.geetinder.efficientfarmingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class solditemsadapter extends ArrayAdapter<SoldItems>{

    public solditemsadapter(Context context, List<SoldItems> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Check if there is an existing list item view that we can reuse
            //Otherwise, if covertView is null then inflate a new list item layout
            View listItemView = convertView;

            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.solditems_layout, parent, false);
            }

            //Find the item at the given position in the list of items
            SoldItems currentItem = (SoldItems) getItem(position);

            TextView name = convertView.findViewById(R.id.name);
            name.setText(currentItem.getName());

            TextView companyname = convertView.findViewById(R.id.company_name);
            companyname.setText(currentItem.getName());

            TextView type = convertView.findViewById(R.id.type);
            type.setText(currentItem.getName());

            TextView amount = convertView.findViewById(R.id.amount);
            amount.setText(currentItem.getName());

            //Return the list item view that is now showing the appropriate data
            return listItemView;
        }
}

