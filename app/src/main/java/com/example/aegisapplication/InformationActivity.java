package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }


    //The two functions below are part of the internet code. These can be duplicated in any class
    // to call the InternetActivity
    // This function gets a URL from the XML string list
    public void getPinterestUrl(View view){
        String url = getString(R.string.XXXXXXX);
        openInternet(url);
    }

    // Gets the URL and passes it to the associated presenter
    public void openInternet(String url){
        InformationPresenter presenter = new InformationPresenter(this);
        presenter.goToInternet(url);
    }
}
