package com.darkside.cab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(7000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent WelcomeIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(WelcomeIntent);
                }
            }
        };
        thread.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
