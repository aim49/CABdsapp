package com.darkside.cab;

import android.app.Activity;
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

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private Button mCustomerLoginBtn, mCustomerRegisterBtn;
    private TextView mCustomerRegisterLink;
    private TextView mCustomerstatus;
    private EditText mEmailCustomer, mPasswordCustomer;
    private FirebaseAuth mAuth;
    private ProgressDialog Loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        mCustomerLoginBtn = findViewById(R.id.Customer_login_btn);
        mCustomerRegisterBtn = findViewById(R.id.customer_register_btn);
        mCustomerRegisterLink = findViewById(R.id.register_Customer_link);
        mCustomerstatus = findViewById(R.id.customer_status_link);
        mEmailCustomer = findViewById(R.id.customer_email);
        mPasswordCustomer = findViewById(R.id.customer_password);
        Loadingbar = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        mCustomerRegisterBtn.setVisibility(View.INVISIBLE);
        mCustomerRegisterBtn.setEnabled(false);

        mCustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomerLoginBtn.setVisibility(View.INVISIBLE);
                mCustomerRegisterLink.setVisibility(View.INVISIBLE);

                mCustomerstatus.setText("Register Customer");

                mCustomerRegisterBtn.setVisibility(View.VISIBLE);
                mCustomerRegisterBtn.setEnabled(true);
            }
        });

        mCustomerRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailCustomer.getText().toString();
                String password = mPasswordCustomer.getText().toString();

                CustomerRegister(email, password);
            }
        });
        mCustomerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailCustomer.getText().toString();
                String password = mPasswordCustomer.getText().toString();

                signInCustomer(email, password);

            }
        });

    }

    private void signInCustomer(String email, String password) {

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write email...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Loadingbar.setTitle("Customer Registration");
            Loadingbar.setMessage("Please wait, While we are register your data...");
            Loadingbar.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {    //if the user data is authenticated
                            if(task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer login Successful", Toast.LENGTH_SHORT).show();
                                Loadingbar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Login failed, Please try again...", Toast.LENGTH_SHORT).show();
                                Loadingbar.dismiss();
                            }
                        }
                    });
        }
    }


    private void CustomerRegister(String email, String password)
    {
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write email...", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Loadingbar.setTitle("Customer Registration");
            Loadingbar.setMessage("Please wait, While we are register your data...");
            Loadingbar.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {    //if the user data is authenticated
                            if(task.isSuccessful())
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register Successful", Toast.LENGTH_SHORT).show();
                                Loadingbar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Registration failed, Please try again...", Toast.LENGTH_SHORT).show();
                                Loadingbar.dismiss();
                            }
                        }
                    });
        }
    }
}
