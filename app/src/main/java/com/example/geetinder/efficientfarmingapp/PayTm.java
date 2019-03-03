package com.example.geetinder.efficientfarmingapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PayTm extends AppCompatActivity {
    EditText x,y;
    Button b;
    ImageView img;
    TextView tv,tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_tm);
        x=findViewById(R.id.paytmev1);
        y=findViewById(R.id.paytmev2);
        img=findViewById(R.id.imageView2);
        tv=findViewById(R.id.paytmtv1);
        tv1=findViewById(R.id.paytmtv2);
        b=findViewById(R.id.paytmbutton);

    }
}
