package com.team3.aegisapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencyActivity extends AppCompatActivity {

    public boolean click1;
    public boolean click2;
    public boolean click3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        click1 = false;
        click2 = false;
        click3 = false;
    }

    public void onEmergencyClick(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:911"));
        startActivity(intent);
    }

    public void onNonEmergencyClick(View view)
    {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view) {
        System.out.print("Starting Incognito");
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    public void goToOptions(View view){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

    public void goToLogin(View view) {

        if (click1 == true && click2 == true && click3 == true) {
            click1 = false;
            click2 = false;
            click3 = false;
        }

        if (click1 == false)
            click1 = true;
        else if (click2 == false)
            click2 = true;
        else if (click3 == false) {
            click3 = true;

            Intent intent = new Intent(this, PinView.class);
            startActivity(intent);
        }


    }

    // Save the Singleton Map when the activity is ended.
    @Override
    protected void onStop() {
        super.onStop();
        AdminSingleton.saveMap(this);
    }
}
