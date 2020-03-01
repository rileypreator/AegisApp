package com.example.aegisapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import static androidx.core.content.ContextCompat.startActivity;

public class Incognito {
    Activity activity;

    public Incognito(Activity activity){
        this.activity = activity;
    }

    public void leaveApp(){
        String url = activity.getString(R.string.incognito_web_address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }
}
