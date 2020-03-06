package com.example.aegisapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OptionsPresenter {
    Activity activity;
    public OptionsPresenter(Activity activity){
        this.activity = activity;

    }

    public void saveSharedPreferences(String url) {
        // Code borrowed from https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = activity.getSharedPreferences("MySharedPref",
                activity.MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("INCOGNITO_URL", url);
        myEdit.commit();

        // Run a toast on the UI thread
        if (activity != null){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Preference Saved", Toast.LENGTH_SHORT).show();
                }
            });
    }
    }
}
