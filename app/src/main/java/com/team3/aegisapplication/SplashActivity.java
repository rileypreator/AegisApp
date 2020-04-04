package com.team3.aegisapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

//    Got some help from this site
//    https://www.bignerdranch.com/blog/splash-screens-the-right-way/

    //Creates a Splash Screen for the application and as soon as it is done loading the app
    //It will go to the mian activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
