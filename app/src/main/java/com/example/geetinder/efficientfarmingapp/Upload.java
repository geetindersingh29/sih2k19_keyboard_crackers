package com.example.geetinder.efficientfarmingapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload {
    private String mName;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String imageUrl) {

        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
