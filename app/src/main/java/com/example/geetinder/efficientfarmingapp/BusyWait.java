package com.example.geetinder.efficientfarmingapp;

import android.app.ProgressDialog;
import android.content.Context;

public class BusyWait {

    private static ProgressDialog progress;

    public static void showBusyWait(Context context) {
        if (progress != null) {
            progress.dismiss();
        }
        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    public static void removeBusyWait() {
        if (progress != null) {
            progress.dismiss();
        }
        progress = null;
    }

}
