package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class OptionsActivity extends AppCompatActivity {

    Switch one;
    Switch two;
    Switch three;
    Switch four;
    Switch five;
    Switch six;
    String appString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        one = findViewById(R.id.switch1);
        two = findViewById(R.id.switch2);
        three = findViewById(R.id.switch3);
        four = findViewById(R.id.switch4);
        five = findViewById(R.id.switch5);
        six = findViewById(R.id.switch6);

        one.setOnClickListener(listener);
        two.setOnClickListener(listener);
        three.setOnClickListener(listener);
        four.setOnClickListener(listener);
        five.setOnClickListener(listener);
        six.setOnClickListener(listener);

    }

       View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.switch1:
                        //one.setChecked(true);
                        two.setChecked(false);
                        three.setChecked(false);
                        four.setChecked(false);
                        five.setChecked(false);
                        six.setChecked(false);
                        if (one.isChecked()){
                            appString = getString(R.string.spotify_app);
                        }
                        else {
                            appString = "";
                        }
                        saveAppPreference(appString);
                        break;
                    case R.id.switch2:
                        one.setChecked(false);
                        //two.setChecked(true);
                        three.setChecked(false);
                        four.setChecked(false);
                        five.setChecked(false);
                        six.setChecked(false);
                        if (two.isChecked()){
                            appString = getString(R.string.twitter_app);
                        }
                        else {
                            appString = "";
                        }
                        saveAppPreference(appString);
                        break;
                    case R.id.switch3:
                        one.setChecked(false);
                        two.setChecked(false);
                        //three.setChecked(true);
                        four.setChecked(false);
                        five.setChecked(false);
                        six.setChecked(false);
                        if (three.isChecked()){
                            appString = getString(R.string.youtube_app);
                        }
                        else {
                            appString = "";
                        }
                        saveAppPreference(appString);
                        break;
                    case R.id.switch4:
                        one.setChecked(false);
                        two.setChecked(false);
                        three.setChecked(false);
                        //four.setChecked(true);
                        five.setChecked(false);
                        six.setChecked(false);
                        if (four.isChecked()){
                            appString = getString(R.string.instagram_app);
                        }
                        else {
                            appString = "";
                        }
                        saveAppPreference(appString);
                        break;
                    case R.id.switch5:
                        one.setChecked(false);
                        two.setChecked(false);
                        three.setChecked(false);
                        four.setChecked(false);
                        //five.setChecked(true);
                        six.setChecked(false);
                        if (five.isChecked()){
                            appString = getString(R.string.facebook_app);
                        }
                        else {
                            appString = "";
                        }
                        saveAppPreference(appString);
                        break;
                    case R.id.switch6:
                        one.setChecked(false);
                        two.setChecked(false);
                        three.setChecked(false);
                        four.setChecked(false);
                        five.setChecked(false);
                        //six.setChecked(true);
                        if (six.isChecked()){
                            appString = getString(R.string.pinterest_app);
                        }
                        else {
                            appString = "";
                        }
                        saveAppPreference(appString);
                        break;
            }
            }
    };

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    public void saveIncognitoURL(View view){
        String key = "INCOGNITO_URL";
        EditText editText = findViewById(R.id.editText);
        String incognitoURL = editText.getText().toString();
        OptionsPresenter options = new OptionsPresenter(this);
        options.saveSharedPreferences(key, incognitoURL);
    }

    public void saveAppPreference(String app){
        String key = "APP_PACKAGE";
        OptionsPresenter options = new OptionsPresenter(this);
        options.saveSharedPreferences(key, app);
    }
}