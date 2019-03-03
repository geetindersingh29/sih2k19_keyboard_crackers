package com.example.geetinder.efficientfarmingapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class buyerorderdate extends AppCompatActivity {
    int y1, y2, currentItem = 0;
    int array1[], array2[], array3[];
    int days, diff;
    Date d1, d2;
    long spinnerItemid;
    long one = 1;
    long two = 2;
    String sID,bID,pID,id;

    private static final String TAG = "selectdate";
    private DatabaseReference rootref;

    private TextView mDisplayDate;
    private TextView mDisplayDatereturn;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    private DatePickerDialog.OnDateSetListener mDateSetListnerreturn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView e3;
    private TextView requireddate;
    private TextView returndate;
    private TextView depositShow;
    Spinner s;
    Button submit;
    private TextView mTotalPrice;
    private Date mStartDate;
    private Date mReturnDate;
    private String senderTokenID;
    private long totalPrice;
    private int pricePerDay;
    private int deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_buyerorderdate);
        radioGroup = findViewById(R.id.radioGroup);
        mDisplayDate = findViewById(R.id.requireddate);
        mDisplayDatereturn = findViewById(R.id.returndate);
        requireddate = findViewById(R.id.requireddate);
        returndate = findViewById(R.id.returndate);
        mTotalPrice = findViewById(R.id.price_id);
        s = findViewById(R.id.paymode);
        depositShow = findViewById(R.id.deposit);
        //depositShow.setText(DataModel.deposit);




        submit = findViewById(R.id.submit);

//        Bundle b = getIntent().getExtras();
        String ppd = getIntent().getExtras().getString("priceperday");
//        pricePerDay = Integer.parseInt(ppd);
//        deposit = Integer.parseInt(getIntent().getStringExtra("deposit"));
        pricePerDay = DataModel.priceperday;
        deposit = DataModel.deposit;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(buyerorderdate.this, R.array.payarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        Toast.makeText(parent.getContext(), "OnItemSelectedListner :" + parent.getItemAtPosition(position).toString() + "id" + parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        spinnerItemid = parent.getItemIdAtPosition(position);
                        //startActivity(new Intent(buyerorderdate.this,PayTm.class));
                        Toast.makeText(parent.getContext(), "OnItemSelectedListner :" + parent.getItemAtPosition(position).toString() + "id" + parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        spinnerItemid = parent.getItemIdAtPosition(position);
                        //startActivity(new Intent(buyerorderdate.this,Cardpay.class));
                        Toast.makeText(parent.getContext(), "OnItemSelectedListner :" + parent.getItemAtPosition(position).toString() + "id" + parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        buyerorderdate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListner,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDisplayDatereturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        buyerorderdate.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListnerreturn,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                //Log.d(TAG,"onDateSet: date"+year+"/"+month+"/"+dayOfMonth);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);

                try {
                    mStartDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
                } catch (Exception e) {
                    return;
                }

            }
        };


        mDateSetListnerreturn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                //Log.d(TAG,"onDateSet: date"+year+"/"+month+"/"+dayOfMonth);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDatereturn.setText(date);

                try {
                    mReturnDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);


                    if (mStartDate != null && mReturnDate != null) {
                        long diff = mReturnDate.getTime() - mStartDate.getTime();

                        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        days += 1;

                        if (days <= 0) {
                            Toast.makeText(buyerorderdate.this, "Invalid Date chosen.", Toast.LENGTH_SHORT).show();
                            mTotalPrice.setText("");
                        } else {

                            totalPrice = (long) pricePerDay * days;
                            totalPrice += deposit;
                            mTotalPrice.setText(String.valueOf(totalPrice));
                        }
                    }


                } catch (Exception e) {
                    return;
                }

            }
        };


        // String uID = getIntent().getStringExtra("uid");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validaterequiredate() || !validatereturndate()) {
                    return;
                } else {

                    orders obj = new orders();
                    rootref = FirebaseDatabase.getInstance().getReference("order");
                     id = rootref.push().getKey();

                     bID = getIntent().getExtras().getString("bid");
                     sID = getIntent().getExtras().getString("sid");
                     pID = getIntent().getExtras().getString("pid");

                    obj.setOid(id);
                    obj.setBid(bID);
                    obj.setSid(sID);
                    obj.setPid(pID);
                    obj.setDatestart(mStartDate.toString());
                    obj.setDateend(mReturnDate.toString());
                    obj.setAmount(Integer.parseInt(mTotalPrice.getText().toString()));
                    obj.setBuyerrating(0);
                    obj.setSellerrating(0);
                    obj.setProductrating(0);
                    obj.setOrderstatus(0);

                    rootref.child(obj.getOid()).setValue(obj);
                  /*  Intent intent1 = new Intent(buyerorderdate.this, buyerfinaldetails.class);
                    intent1.putExtra("sid",sID);
                    intent1.putExtra("bid",bID);
                    intent1.putExtra("pid",pID);
                    intent1.putExtra("oid",id);
                    intent1.putExtra("rtdate",returndate.getText().toString());
                    intent1.putExtra("reqdate",requireddate.getText().toString());
                    intent1.putExtra("amount",Integer.parseInt(mTotalPrice.getText().toString()));
                    startActivity(intent1);*/

                    AlertDialog.Builder builder = new AlertDialog.Builder(buyerorderdate.this);
                    builder.setMessage("product uploaded successfully.")
                            .setCancelable(false)

                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent1 = new Intent(buyerorderdate.this, buyerfinaldetails.class);
                                    intent1.putExtra("sid",sID);
                                    intent1.putExtra("bid",bID);
                                    intent1.putExtra("pid",pID);
                                    intent1.putExtra("oid",id);
                                    intent1.putExtra("rtdate",returndate.getText().toString());
                                    intent1.putExtra("reqdate",requireddate.getText().toString());
                                    intent1.putExtra("amount",mTotalPrice.getText().toString());
                                    startActivity(intent1);
                                }

                            });

                    AlertDialog reset = builder.create();
                    reset.setTitle("sucess");
                    reset.show();


//                            if (spinnerItemid == one) {
//
//                                startActivity(new Intent(buyerorderdate.this, PayTm.class));
//                            } else if (spinnerItemid == two) {
//                                startActivity(new Intent(buyerorderdate.this, Cardpay.class));
//                            } else {
//                                Toast.makeText(buyerorderdate.this, "ans" + spinnerItemid, Toast.LENGTH_SHORT).show();
//                            }

                }

                                /*senderTokenID="ekrq6QrfsNE:APA91bHiG7jfzafRQiS5MjlKRT1t-lBXXWgnQhNKKV3Qwv46Hphki6cFCUxpwhTDTo1smtF0v3oNbqdJupyp_NlppmFtRhyq1lN4n5ciuc0_DvOIW7NEjp7T8Dfb7lFH9iU9onwURrKg";
                                String msg = "Hello sent";

                                send(senderTokenID, msg);


                                /**
                                 *
                                 *
                                 * Start Activity according to Spinner here
                                 *
                                 */


            }
        });
    }

    public void check(View v) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
        Toast.makeText(this, "Selected Radio Button : " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }


    public String send(String to, String body) {
        try {

            final String apiKey = "AIzaSyDZAlQOIbZIbHgsDwxF3n8RIQjvFMbl0jU";
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + apiKey);
            conn.setDoOutput(true);
            JSONObject message = new JSONObject();
            message.put("to", to);
            message.put("priority", "high");
            JSONObject notification = new JSONObject();

            // notification.put("title", title);
            notification.put("body", body);
            message.put("data", notification);
            OutputStream os = conn.getOutputStream();
            os.write(message.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + message.toString());
            System.out.println("Response Code : " + responseCode);
            System.out.println("Response Code : " + conn.getResponseMessage());

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public Date dateConvert(String s) {

        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return convertedDate;
    }

    private boolean validaterequiredate() {
        String username = requireddate.getText().toString().trim();
        if (username.isEmpty()) {
            requireddate.setError("Field can't be empty");
            Toast.makeText(this, " Required Date Field cannot be empty ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            requireddate.setError(null);
            return true;
        }
    }

    private boolean validatereturndate() {
        String username = returndate.getText().toString().trim();
        if (username.isEmpty()) {
            returndate.setError("Field can't be empty");
            Toast.makeText(this, "Return Date Field cannot be empty ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            returndate.setError(null);
            return true;
        }
    }

    private boolean validatecheckdate() {
        String rtn = returndate.getText().toString();
        String rt = requireddate.getText().toString();
        d1 = dateConvert(rtn);
        d2 = dateConvert(rt);
        diff = (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));


        if (diff <= 0) {
            returndate.setError("Return date should not be less than required date");
            Toast.makeText(this, "Return date should not be less than required date", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            returndate.setError(null);
            return true;
        }
    }

    public void confirmInput(View view) {
        Intent intent = new Intent(buyerorderdate.this, buyerfinaldetails.class);
        startActivity(intent);
    }


}

