package com.example.aegisapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AdminSingleton {

    private static HashMap<String, AdminListener> map;
    private static AdminSingleton instance;

    private AdminSingleton(){
        map = new HashMap<String, AdminListener>();
    }

    public static AdminSingleton getInstance() {
        if (instance == null)
            instance = new AdminSingleton();
        return instance;
    }

    //Save the Admin map to Shared Pref
    public static void saveMap(Activity activity){
        //convert to string using gson
        Gson gson = new Gson();
        String adminMapString = gson.toJson(map);

        // save in shared prefs
        SharedPreferences prefs = activity.getSharedPreferences("ADMIN", MODE_PRIVATE);
        prefs.edit().putString("ADMIN_MAP", adminMapString).apply();
    }

    // Retrieve the Admin map from Shared Pref to be used in AdminInfoActivity
    public void readMap(Activity activity){
        Gson gson = new Gson();
        SharedPreferences prefs = activity.getSharedPreferences("ADMIN", MODE_PRIVATE);
        String storedAdminMapString = prefs.getString("ADMIN_MAP", "");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, AdminListener>>(){}.getType();
        map = gson.fromJson(storedAdminMapString, type);
    }

    public void setMap(String string, AdminListener adminListener) {
        if (map.containsKey(string)) {
            map.get(string).addClick();
        }
        else {
            adminListener.addClick();
            map.put(string, adminListener);
        }

    }

    public HashMap<String, AdminListener> getMap() {
        return map;
    }
}
