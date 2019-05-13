package com.darkside.cab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {
    private Button mDriverLoginBtn, mDriverRegisterBtn;
    private TextView mDriverRegisterLink;
    private TextView mDriverstatus;
    private EditText mEmailDriver, mPasswordDriver;
    private FirebaseAuth mAuth;
    private ProgressDialog LoadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        mAuth = FirebaseAuth.getInstance();


        mDriverLoginBtn = findViewById(R.id.driver_login_btn);
        mDriverRegisterBtn = findViewById(R.id.driver_register_btn);
        mDriverRegisterLink = findViewById(R.id.driver_register_link);
        mDriverstatus = findViewById(R.id.driver_status);
        mEmailDriver = findViewById(R.id.driver_email);
        mPasswordDriver = findViewById(R.id.customer_password);
        LoadingBar = new ProgressDialog(this);

        mDriverRegisterBtn.setVisibility(View.INVISIBLE);
        mDriverRegisterBtn.setEnabled(false);



        mDriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDriverLoginBtn.setVisibility(View.INVISIBLE);
                mDriverRegisterLink.setVisibility(View.INVISIBLE);

                mDriverstatus.setText("Register Driver");

                mDriverRegisterBtn.setVisibility(View.VISIBLE);
                mDriverRegisterBtn.setEnabled(true);
            }
        });


        mDriverRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailDriver.getText().toString();
                String password = mPasswordDriver.getText().toString();

                RegisterDriver(email, password);
            }
        });

        mDriverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailDriver.getText().toString();
                String password = mPasswordDriver.getText().toString();

                LoginDriver(email, password);
            }
        });


    }

    private void LoginDriver(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write email...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LoadingBar.setTitle("Driver Login");
            LoadingBar.setMessage("Please wait, While we are Loading your account...");
            LoadingBar.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {    //if the user data is authenticated
                            if(task.isSuccessful())
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver SignIn Successful", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                            }
                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver login failed, Please try again...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }
                        }
                    });
        }


    }

    private void RegisterDriver(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write email...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please write password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LoadingBar.setTitle("Driver Registration");
            LoadingBar.setMessage("Please wait, While we are register your data...");
            LoadingBar.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {    //if the user data is authenticated
                            if(task.isSuccessful())
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Successful", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Registration failed, Please try again...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}
