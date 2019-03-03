package com.example.geetinder.efficientfarmingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geetinder.efficientfarmingapp.AccountActivity.LoginActivity;
import com.example.geetinder.efficientfarmingapp.AccountActivity.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login_using_phonenumber extends AppCompatActivity {

    private Button send, confirm;
    private EditText phoneNumber, fillotp;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_using_phonenumber);

        send = findViewById(R.id.btn_send_otp);
        confirm = findViewById(R.id.btn_confirm_otp);
        phoneNumber = findViewById(R.id.phone_number);
        fillotp = findViewById(R.id.fill_otp);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_using_phonenumber.this, FillAddress.class));
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                AlertDialog.Builder builder = new AlertDialog.Builder(login_using_phonenumber.this);
                builder.setMessage("you are valid")
                        .setCancelable(false)
                        .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }

                        });

                AlertDialog reset = builder.create();
                reset.setTitle("Verify Email");
                reset.show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                AlertDialog.Builder builder = new AlertDialog.Builder(login_using_phonenumber.this);
                builder.setMessage("please enter a valid phone number")
                        .setCancelable(false)
                        .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }

                        });

                AlertDialog reset = builder.create();
                reset.setTitle("Verify Email");
                reset.show();
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                fillotp.setEnabled(true);
                confirm.setEnabled(true);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PhoneAuthProvider.getInstance().verifyPhoneNumber();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                       "+91"+ phoneNumber.getText().toString(),        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        login_using_phonenumber.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = fillotp.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();

                            Intent intent = new Intent(login_using_phonenumber.this, FillAddress.class);
                            intent.putExtra("Phone Number", phoneNumber.getText().toString());
                            startActivity(intent);

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(login_using_phonenumber.this);
                            builder.setMessage("OTP mismatch")
                                    .setCancelable(false)
                                    .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }

                                    });


                            AlertDialog reset = builder.create();
                            reset.setTitle("Verify Email");
                            reset.show();
                        }
                    }
                });
    }

}
