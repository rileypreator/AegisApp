package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.leaveApp();
    }

    public void saveIncognitoURL(View view){
        EditText editText = findViewById(R.id.editText);
        String incognitoURL = editText.getText().toString();
        OptionsPresenter options = new OptionsPresenter(this);
        options.saveSharedPreferences(incognitoURL);
    }
}
