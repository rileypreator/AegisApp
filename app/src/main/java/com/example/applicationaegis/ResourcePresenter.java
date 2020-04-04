package com.example.applicationaegis;

import android.app.Activity;
import android.content.Intent;

/*
Resource Presenter starts the Internet Activity when called to do so.
 */
public class ResourcePresenter {
    Activity activity;
    String url;

    public ResourcePresenter(Activity activity){
        this.activity = activity;
    }
    // This function is part of the internet package and can be duplicated for any Presenter
    // Get the desired URL from the associated Activity, create an Intent and start the InternetActivity
    public void goToInternet(String url){
        this.url = url;
        Intent intent = new Intent(activity, InternetActivity.class);
        intent.putExtra("URL", url);
        activity.startActivity(intent);
    }
}
