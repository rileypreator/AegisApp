package com.team3.aegisapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class AdminInfoActivity extends AppCompatActivity {

    //Arrays to store the information for each of the Adapters
    private String[] imagesArray = new String[50];
    private String[] linksArray = new String[50];
    private String[] lastClickedArray = new String[50];
    private Integer[] numberofTimesArray = new Integer[50];
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_results);

        //Creates an adapter with the information passed in
        AdminAdapter adapter = new AdminAdapter(this, linksArray, lastClickedArray, numberofTimesArray, imagesArray);

        //calls the function to load the information into the Adapter
        setStrings();

//        if (linksArray[0] == null) {
//
//        }

        //sets the information in the list view with the adapters as well
        listView = (ListView) findViewById(R.id.adminListView);
        listView.setAdapter(adapter);
    }

    private void setStrings() {

        //gets the information from the Singleton Class
        AdminSingleton stringMap = AdminSingleton.getInstance();

        //creates a map to iterate through and store all of the information
        HashMap<String, AdminListener> mapToIterate = stringMap.getMap();

        //Creates a new listener to store all fo the values
        AdminListener newListener;
        int i = 0;

        //for loop to loop through all of the information in the admin mode
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
