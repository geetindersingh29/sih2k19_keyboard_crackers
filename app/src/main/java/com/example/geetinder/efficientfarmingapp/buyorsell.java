package com.example.geetinder.efficientfarmingapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.example.geetinder.efficientfarmingapp.AccountActivity.ResetPassword;

import java.util.Locale;

public class buyorsell extends AppCompatActivity {

    private static ImageView imgA;
    private static ImageView imgB;
    private static ImageView imgC;
    private static ImageView imgD;
    private static ImageView imgE;
    private static ImageView imgF;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button sbb,check,changeLang;
    int pos;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //changeLang("en");
        //saveLocale("en");
        loadLocale();
        setContentView(R.layout.activity_buyorsell);

        ViewPager viewPager = findViewById(R.id.pagerid);
        ImageAdaptor adaptor= new ImageAdaptor(this);
        viewPager.setAdapter(adaptor);


        sbb = findViewById(R.id.sbbutton);
        radioGroup = findViewById(R.id.radioGroup);
        changeLang = findViewById(R.id.changeMyLang);





        sbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.radio_Buyer) {
                    startActivity(new Intent(buyorsell.this, buyersecond.class));
                } else {
                    startActivity(new Intent(buyorsell.this, seller.class));
                }

            }
        });
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

        click1();
        click2();
        click3();
//        click4();
        click5();
//        click6();


    }
    public void click1()
    {
        imgA  = (ImageView)findViewById(R.id.img1);
        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.farming_tech.ScrollingActivity");
                startActivity(intent);

            }
        });
    }
    public void click2()
    {
        imgB  = (ImageView)findViewById(R.id.img2);
        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.machine.Machine_tech");
                startActivity(intent);

            }
        });
    }
    public void click3()
    {
        imgC  = (ImageView)findViewById(R.id.img3);
        imgC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.loan.Loan_activity");
                startActivity(intent);

            }
        });
    }
    //    public void click4()
//    {
//        imgC  = (ImageView)findViewById(R.id.img3);
//        imgC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("Loan_activity");
//                startActivity(intent);
//
//            }
//        });
//    }
    public void click5()
    {
        imgE  = (ImageView)findViewById(R.id.img5);
        imgE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.geetinder.efficientfarmingapp.ngo.awa");
                startActivity(intent);

            }
        });
    }
//    public void click6()
//    {
//        imgF  = (ImageView)findViewById(R.id.img6);
//        imgF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("Loan_activity");
//                startActivity(intent);
//
//            }
//        });
//    }

//    public void openBrowser(View view){
//
//        //Get url from tag
//        String url = (String)view.getTag();
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.addCategory(Intent.CATEGORY_BROWSABLE);
//
//        //pass the url to intent data
//        intent.setData(Uri.parse(url));
//
//        startActivity(intent);
//    }

    public void check(View v) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
        //Toast.makeText(this, "Selected Radio Button : " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resetpw, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.reset_pw:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(buyorsell.this);
                builder.setMessage("Are you sure to reset your password ?")
                        .setCancelable(false)

                        .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(buyorsell.this, ResetPassword.class));
                                //Intent intent = new Intent(buyorsell.this, ResetPassword.class);
                            }

                        });

                AlertDialog reset = builder.create();
                reset.setTitle("Reset Password");
                reset.show();
            }
            break;
            case R.id.delete_acc:
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(buyorsell.this);
                builder.setMessage("Are you sure you want to delete your account?")
                        .setCancelable(false)

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(buyorsell.this,"Account has been selected",Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog delete =builder.create();
                delete.setTitle("Delete Account");
                delete.show();
            }

            break;
        }
        return true;
    }
    public void openBrowser(View view){

        //Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

    public void goToUrl (View view) {
        String url = "https://www.google.com/search?q=weather+report";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    private void showChangeLanguageDialog() {
        final String[] listItems={"English","हिंदी"} ;
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(buyorsell.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems,-1,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                pos=i;
                if(i==0){
                    changeLang("en");
                    saveLocale("en");
                    //changeLang(Resources.getSystem().getConfiguration().locale.getLanguage());
                    //saveLocale(Resources.getSystem().getConfiguration().locale.getLanguage());
                    loadLocale();
                    position=i;
                   recreate();

                }
                else if(i==1){
                    changeLang("hi");
                    saveLocale("hi");
                    loadLocale();
                    position=i;
                    recreate();

                }

                dialogInterface.dismiss();
                startActivity(new Intent(buyorsell.this,MainActivity.class));
            }
        });

        AlertDialog mDialog =mBuilder.create();
        mDialog.show();

    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }
}
