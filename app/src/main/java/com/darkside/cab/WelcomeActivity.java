package com.darkside.cab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button mDriverBtn,mCustomerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mDriverBtn = findViewById(R.id.welcome_driver_btn);
        mCustomerBtn = findViewById(R.id.welcome_customer_btn);

        mCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, CustomerLoginRegisterActivity.class);
                startActivity(i);
            }
        });

        mDriverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, DriverLoginRegisterActivity.class);
                startActivity(i);
            }
        });

    }

}
