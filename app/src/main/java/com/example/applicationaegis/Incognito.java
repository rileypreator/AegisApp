package com.example.applicationaegis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;

/*
IncognitoActivity quickly closes the app by way of starting a new program. This can be another
installed app or a web URL. The settings for Incognito can be changed in the Options menu.
 */
public class Incognito {
    private Activity activity;

    public Incognito(Activity activity){
        this.activity = activity;
    }

    // This function decides where to send the user when the Incognito button is pushed.
    // It first checks for a preferred app, then for a user-defined url. If neither of these exist,
    // it selects the default website found in strings.xml, labeled "incognito_web_address".
    public void setIncognitoURL(){
        SharedPreferences sh = activity.getSharedPreferences("MySharedPref", activity.MODE_PRIVATE);
        String url;
        if (sh.contains("APP_PACKAGE")){
            String appPackage = sh.getString("APP_PACKAGE", "");
            PackageManager pm = activity.getPackageManager();
            if(isPackageInstalled(appPackage, pm)){
                leaveAppApp(appPackage);
            } else if (sh.contains("INCOGNITO_URL")) {
                try{
                    url = sh.getString("INCOGNITO_URL", "");
                leaveAppInternet(url);
                } catch (Exception e) {
                    e.printStackTrace();
                    url = activity.getString(R.string.incognito_web_address);
                    leaveAppInternet(url);
                }
            }else {
                url = activity.getString(R.string.incognito_web_address);
                leaveAppInternet(url);
            }
        }
    }
    // Checks to see if the desire app is actually installed on the device. Returns a Bool.
    private boolean isPackageInstalled(String packageName, PackageManager pm) {
        try {
            pm.getPackageInfo(packageName, 0);
            return true;
            } catch (PackageManager.NameNotFoundException e) {
            return false;
            }
        }
    // If an app is selected and it is installed on the device, this function is called to open that app.
    private void leaveAppInternet(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

    // If an app is not selected, this function is called to open the internet to the
    // specified URL.
    private void leaveAppApp(String appPackage){
        Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(appPackage);
        if (launchIntent != null) {
            activity.startActivity(launchIntent);//null pointer check in case package name was not found
        }
    }
}
