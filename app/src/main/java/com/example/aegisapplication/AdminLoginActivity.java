package com.example.aegisapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    //information in for the
    Button enterPin;
    Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        appContext = this;

        enterPin = (Button) findViewById(R.id.buttonEnterPin);

        enterPin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent intent = new Intent(appContext, Pin)
            }
        });
    }
}
