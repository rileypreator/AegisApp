package com.example.aegisapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    //information in for the
    private String passwordFinal;
    private String userInput;
    final private int password_length = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }
}
