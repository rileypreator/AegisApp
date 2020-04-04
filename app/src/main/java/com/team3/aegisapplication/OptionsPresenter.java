package com.team3.aegisapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
/*
The Options Presenter is the mediator between OptionsActivity and the Shared Preferences.
This will save new settings to Shared Preferences as well as read teh data already stored in Shared
Preference to provide feedback to the user on what settings are already selected.
 */
public class OptionsPresenter implements Runnable {
    Activity activity;

    public OptionsPresenter(Activity activity){
        this.activity = activity;

    }

    // Saves the desired app data or user-entered URL
    public void saveSharedPreferences(String key, String address) {
        // Code borrowed from https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = activity.getSharedPreferences("MySharedPref",
                activity.MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(key, address);
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
    //The run() function below gets settings info from Shared Preference and fills in the Options
    // menu with the currently saved information. It does this by selecting the appropriate
    // switch for the apps and displaying the user-defined URL. This will allow the user to easily see what
    // preferences are currently saved.
    @Override
    public void run() {
        SharedPreferences sh = activity.getSharedPreferences("MySharedPref", activity.MODE_PRIVATE);
        if (sh.contains("APP_PACKAGE")) {
            final String appPackage = sh.getString("APP_PACKAGE", "");
        if (sh.contains("INCOGNITO_URL")){
            final String url = sh.getString("INCOGNITO_URL", "");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch(appPackage){
                    case "com.spotify.music":
                        Switch one = activity.findViewById(R.id.switch1);
                        one.setChecked(true);
                        break;
                    case "com.twitter.android":
                        Switch two = activity.findViewById(R.id.switch2);
                        two.setChecked(true);
                        break;
                    case "com.google.android.youtube":
                        Switch three = activity.findViewById(R.id.switch3);
                        three.setChecked(true);
                        break;
                    case "com.instagram.android":
                        Switch four = activity.findViewById(R.id.switch4);
                        four.setChecked(true);
                        break;
                    case "com.facebook.katana":
                        Switch five = activity.findViewById(R.id.switch5);
                        five.setChecked(true);
                        break;
                    case "com.pinterest":
                        Switch six = activity.findViewById(R.id.switch6);
                        six.setChecked(true);
                        break;
                }
                    if (url != ""){
                        TextView text = activity.findViewById(R.id.URLinput);
                        text.setText(url);
                    }
            }
            });
        }
    }
}}


