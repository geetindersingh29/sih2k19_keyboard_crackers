package com.example.geetinder.efficientfarmingapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class specifications extends AppCompatActivity {

    private long k = 0;
    private DatabaseReference rootRef;
    private ProgressBar mProgressBar;
    private EditText companyName;
    private EditText modelNumber;
    private EditText pricePerDay;
    private Button uploadBtn;

    private Uri mImageUri;
    private String mImageUrl;
    private String itemType;
    private String cName;
    private String mNumber;
    private int pPerDay;

    private StorageReference mStorageRef;

    private StorageTask mUploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications);

        companyName = findViewById(R.id.company_name);
        modelNumber = findViewById(R.id.model_number);
        pricePerDay = findViewById(R.id.price_per_day);
        uploadBtn = findViewById(R.id.upload_btn);
        mProgressBar = findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkNotEmpty()) {

                    itemType = getIntent().getStringExtra("type");
                    mImageUri = Uri.parse(getIntent().getStringExtra("mImageUri"));
                    cName = companyName.getText().toString();
                    mNumber = modelNumber.getText().toString();
                    pPerDay = Integer.parseInt(pricePerDay.getText().toString());

                    addDetails();
                }
                else
                    Toast.makeText(specifications.this, "All details are compulsary",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean checkNotEmpty()
    {
        if(companyName.getText().toString().matches("") || modelNumber.getText().toString().matches("") || pricePerDay.getText().toString().matches(""))
            return false;
        return true;
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void addDetails(){

        final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(mImageUri));

        mUploadTask = fileReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //Toast.makeText(getApplicationContext(),fileReference.toString(),Toast.LENGTH_LONG).show();
                                mImageUrl = uri.toString();
                            }
                        });

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(0);
                            }
                        }, 200);



                        System.out.println(mImageUrl);

                        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        if (mUploadTask != null && mUploadTask.isInProgress()) {
                            Toast.makeText(specifications.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        } else {

                            rootRef = FirebaseDatabase.getInstance().getReference("items");

                            String id = rootRef.push().getKey();
                            //Item item = new Item(itemType, cName, mNumber, pPerDay, uID, mImageUrl);
                            //Toast.makeText(getApplicationContext(),mImageUrl,Toast.LENGTH_LONG).show();
                            //rootRef.child(id).setValue(item);
                        }
                        Toast.makeText(specifications.this, "Upload successful", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Toast.makeText(specifications.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        mProgressBar.setProgress((int) progress);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        k++;
        if(k == 1)
        {
            Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        else
        {
            startActivity(new Intent(specifications.this,seller.class));
        }
    }
}
