package com.example.aegisapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

public class AdminInfoActivity extends AppCompatActivity {

    private String[] imagesArray = new String[50];
    private String[] linksArray = new String[50];
    private String[] lastClickedArray = new String[50];
    private Integer[] numberofTimesArray = new Integer[50];
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_results);

        AdminAdapter adapter = new AdminAdapter(this, linksArray, lastClickedArray, numberofTimesArray, imagesArray);
        setStrings();

        if (linksArray[0] == null) {

        }

        listView = (ListView) findViewById(R.id.adminListView);
        listView.setAdapter(adapter);
    }

    private void setStrings() {

        AdminSingleton stringMap = AdminSingleton.getInstance();

        HashMap<String, AdminListener> mapToIterate = stringMap.getMap();
        AdminListener newListener;
        int i = 0;
        for (HashMap.Entry<String, AdminListener> entry : mapToIterate.entrySet()) {
            newListener = entry.getValue();

            imagesArray[i] = newListener.getType();
            linksArray[i] = newListener.getLink();
            lastClickedArray[i] = newListener.getLastTimeClicked();
            numberofTimesArray[i] = newListener.getTotalClicks();
            i++;
        }
    }
    // Save the Singleton Map when the activity is ended.
    @Override
    protected void onStop() {
        super.onStop();
        AdminSingleton.saveMap(this);
    }
}
