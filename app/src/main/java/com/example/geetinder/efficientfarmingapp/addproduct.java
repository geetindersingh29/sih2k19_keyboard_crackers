package com.example.geetinder.efficientfarmingapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class addproduct extends AppCompatActivity {
    int y1,y2,currentItem=0;

    int days,diff;
    Date d1,d2;

    long one=1;
    long two=2;


    private static  final String TAG="selectdate";
    private TextView fromdate;
    private TextView todate;
    EditText deposit,ppd,description;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    private DatePickerDialog.OnDateSetListener mDateSetListnerreturn;
    Button submit_btn,choose_btn;
    ImageView image;
    Spinner type;

    private Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        fromdate=findViewById(R.id.fromdate);
        todate=findViewById(R.id.todate);
        submit_btn=findViewById(R.id.submit_btn);
        choose_btn=findViewById(R.id.choose_btn);
        deposit=findViewById(R.id.deposit);
        ppd=findViewById(R.id.ppd);
        description=findViewById(R.id.description);
        image=findViewById(R.id.image);
        type=findViewById(R.id.type);



        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatefromdate() | !validatetodate() | !validatecheckdate() | !validateppd() | !validatedeposit() ){

                    return;
                }
                else
                {
                    Item newProduct = new Item();
                    newProduct.setDeposit(Integer.parseInt(deposit.getText().toString()));
                    newProduct.setPayPerDay(Integer.parseInt(ppd.getText().toString()));
                    newProduct.setFromdate(fromdate.getText().toString());
                    newProduct.setTodate(todate.getText().toString());
                    newProduct.setProductinfo(description.getText().toString());
                    newProduct.setmImageUrl("");
                    newProduct.setType(type.getSelectedItem().toString());
                    newProduct.setBookdate(new ArrayList<String>());
                    newProduct.setProductstarcount(0);
                    newProduct.setProductstartotal(0);
                    newProduct.setOwnerid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("items");
                    newProduct.setProductid(rootRef.push().getKey());
                    rootRef.child(newProduct.getProductid()).setValue(newProduct);

                    AlertDialog.Builder builder = new AlertDialog.Builder(addproduct.this);
                    builder.setMessage("product uploaded successfully.")
                            .setCancelable(false)

                            .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(addproduct.this,seller.class));
                                }

                            });

                    AlertDialog reset = builder.create();
                    reset.setTitle("sucess");
                    reset.show();
                }
            }
        });

        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog dialog = new DatePickerDialog(
                        addproduct.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListner,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                //y1=year;


            }
        });
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Calendar cal1 = Calendar.getInstance();
                int year =cal1.get(Calendar.YEAR);
                int month =cal1.get(Calendar.MONTH);
                int day =cal1.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog dialog = new DatePickerDialog(
                        addproduct.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListnerreturn,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"onDateSet: date"+year+"/"+month+"/"+dayOfMonth);
                String date =month+"/"+dayOfMonth+"/"+year;
                fromdate.setText(date);
            }
        };
        mDateSetListnerreturn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"onDateSet: date"+year+"/"+month+"/"+dayOfMonth);
                String date =month+"/"+dayOfMonth+"/"+year;
                todate.setText(date);
            }
        };

        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

    }
    private boolean validatefromdate(){
        String  username = fromdate.getText().toString().trim();
        if(username.isEmpty()){
            fromdate.setError("Field can't be empty");
            Toast.makeText(this,"From Date Field cannot be empty ",Toast.LENGTH_SHORT).show();
            return false;
        } else{
            fromdate.setError(null);
            return true;
        }
    }
    private boolean validatetodate(){
        String  username = todate.getText().toString().trim();
        if(username.isEmpty()){
            todate.setError("Field can't be empty");
            Toast.makeText(this,"To Date Field cannot be empty ",Toast.LENGTH_SHORT).show();
            return false;
        } else{
            todate.setError(null);
            return true;
        }
    }
    private boolean validateppd(){
        String  username = ppd.getText().toString().trim();
        if(username.isEmpty()){
            ppd.setError("Field can't be empty");
            Toast.makeText(this,"Price per Date Field cannot be empty ",Toast.LENGTH_SHORT).show();
            return false;
        } else{
            ppd.setError(null);
            return true;
        }
    }
    private boolean validatedeposit(){
        String  username = deposit.getText().toString().trim();
        if(username.isEmpty()){
            deposit.setError("Field can't be empty");
            Toast.makeText(this,"Deposit Field cannot be empty ",Toast.LENGTH_SHORT).show();
            return false;
        } else{
            deposit.setError(null);
            return true;
        }
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
    private boolean validatecheckdate(){
        String rtn = todate.getText().toString();
        String rt = fromdate.getText().toString();
        d1=dateConvert(rtn);
        d2=dateConvert(rt);
        diff=(int)( (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));


        if(diff<=0){
            todate.setError("Return date should not be less than required date");
            Toast.makeText(this, "Return date should not be less than required date", Toast.LENGTH_SHORT).show();
            return false;
        } else{
            todate.setError(null);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(image);
        }
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }
}

