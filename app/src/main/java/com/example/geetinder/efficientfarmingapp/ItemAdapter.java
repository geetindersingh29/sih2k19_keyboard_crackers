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

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if there is an existing list item view that we can reuse
        //Otherwise, if covertView is null then inflate a new list item layout
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_layout, parent, false);
        }

        //Find the item at the given position in the list of items
        Item currentItem = (Item) getItem(position);

        TextView typeView = (TextView) listItemView.findViewById(R.id.item_type);
        typeView.setText(currentItem.getType());

        TextView priceView = (TextView) listItemView.findViewById(R.id.item_price_per_day);
        priceView.setText(String.valueOf(currentItem.getPayPerDay()));

        ImageView image = (ImageView) listItemView.findViewById(R.id.item_image);
        Glide.with(getContext()).load(currentItem.getmImageUrl()).into(image);

        //Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
