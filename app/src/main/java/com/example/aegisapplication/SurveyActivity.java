package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SurveyActivity extends AppCompatActivity {
    boolean isForMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    public void onMyselfClick(View view)
    {
        isForMe = true;
        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("isForMe", isForMe);
        startActivity(intent);
    }

    public void onSomeoneElseClick(View view)
    {
        isForMe = false;
        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("isForMe", isForMe);
        startActivity(intent);
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    public void goToOptions(View view){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

    // Save the Singleton Map when the activity is ended.
    @Override
    protected void onStop() {
        super.onStop();
        AdminSingleton.saveMap(this);
    }
}
