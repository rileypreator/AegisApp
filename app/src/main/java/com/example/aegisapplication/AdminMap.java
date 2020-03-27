package com.example.aegisapplication;

import java.util.HashMap;

public class AdminMap {

    private static HashMap<String, AdminListener> map = new HashMap<String, AdminListener>();
    private static AdminMap instance;

    private void AdminMap() {

    }
    public static AdminMap getInstance() {
        if (instance == null)
            instance = new AdminMap();
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
}
