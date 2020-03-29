package com.example.aegisapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import static android.content.Context.MODE_PRIVATE;

public class AdminSingleton {

    private static HashMap<String, AdminListener> map = new HashMap<String, AdminListener>();
    private static AdminSingleton instance;


    public static AdminSingleton getInstance(Activity activity) {
        instance = readMap(activity);
        if (instance == null)
            instance = new AdminSingleton();
        return instance;
    }

    public void saveMap(Activity activity){
        //convert to string using gson
        Gson gson = new Gson();
        String adminMapString = gson.toJson(instance);

        // save in shared prefs
        SharedPreferences prefs = activity.getSharedPreferences("ADMIN", MODE_PRIVATE);
        prefs.edit().putString("ADMIN_MAP", adminMapString).apply();
    }

    public static AdminSingleton readMap(Activity activity){
        Gson gson = new Gson();
        SharedPreferences prefs = activity.getSharedPreferences("ADMIN", MODE_PRIVATE);
        String storedAdminMapString = prefs.getString("ADMIN_MAP", "");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        AdminSingleton storedAdminMap = gson.fromJson(storedAdminMapString, type);
        return storedAdminMap;
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
