package com.example.aegisapplication;

import java.util.HashMap;

public class AdminSingleton {

    private static HashMap<String, AdminListener> map = new HashMap<String, AdminListener>();
    private static AdminSingleton instance;

    private void AdminMap() {

    }
    public static AdminSingleton getInstance() {
        if (instance == null)
            instance = new AdminSingleton();
        return instance;
    }

    public void setMap(String string, AdminListener adminListener) {
        if (map.containsKey(string)) {
            map.get(string).addClick();
        }
        else {
            map.put(string, adminListener);
        }

    }

    public HashMap<String, AdminListener> getMap() {
        return map;
    }
}
