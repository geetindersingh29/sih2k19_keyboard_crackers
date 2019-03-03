package com.example.geetinder.efficientfarmingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdaptor extends PagerAdapter {
    private Context mcontext;
    private int[] imgid= new int[]{R.drawable.picmodiji,R.drawable.drone,R.drawable.farms, R.drawable.picone,R.drawable.picmodijinext,R.drawable.tracttwo};
    ImageAdaptor(Context context){
        mcontext=context;

    }

    @Override
    public int getCount() {
        return imgid.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object );

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageview=new ImageView(mcontext);
        imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageview.setImageResource(imgid[position]);
        container.addView(imageview,0);
        return imageview;
    }
}
