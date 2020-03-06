package com.example.aegisapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.EditText;

public class Incognito {
    Activity activity;
    String url;
    public Incognito(Activity activity){
        this.activity = activity;
    }

    private void setIncognitoURL(){
        SharedPreferences sh = activity.getSharedPreferences("MySharedPref", activity.MODE_PRIVATE);
        if (sh.contains("INCOGNITO_URL")){
            url = sh.getString("INCOGNITO_URL", "");
        } else{
            url = activity.getString(R.string.incognito_web_address);
        }

    }


    public void leaveApp(){
        setIncognitoURL();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }
}