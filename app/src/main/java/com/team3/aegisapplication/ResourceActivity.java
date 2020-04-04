package com.team3.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class ResourceActivity extends AppCompatActivity {
boolean isForMe;
String resource_type;
String [] resources;
String [] resources_names;
Spinner abuseTypeSpinner;
String [] abuseTypes;
String whereToGoNext;
Context context = this;
Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        Intent intent = getIntent();
        isForMe = intent.getBooleanExtra("audience_type", true);
        resource_type = intent.getStringExtra("resource_type");
        abuseTypes = getApplicationContext().getResources().getStringArray(R.array.abuse_types);

        ArrayAdapter<String> adapter2;

        // Get reference of SpinnerView from layout/main_activity.xml
        abuseTypeSpinner =(Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item, abuseTypes);

        abuseTypeSpinner.setAdapter(adapter);

        int selectionID;
        switch (resource_type)
        {
            case "physical":{
                selectionID = 0;
                break;
            }
            case "sexual":{
                selectionID = 1;
                break;
            }
            case "verbal":{
                selectionID = 2;
                break;
            }
            case "mental":{
                selectionID = 3;
                break;
            }
            default:
            {
                selectionID = 0;
                break;
            }
        }
        abuseTypeSpinner.setSelection(selectionID);

        abuseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid=abuseTypeSpinner.getSelectedItemPosition();

                if (abuseTypes[sid] == abuseTypes[0]) {
                    whereToGoNext = "physical";
                    if(isForMe) {
                        resources_names = getResources().getStringArray(R.array.physical_forMe_names);
                        resources = getResources().getStringArray(R.array.physical_forMe);
                    }
                    else {
                        resources_names = getResources().getStringArray(R.array.physical_forSomeoneElse_names);
                        resources = getResources().getStringArray(R.array.physical_forSomeoneElse);
                    }
                }
                else if (abuseTypes[sid] == abuseTypes[1]) {
                    whereToGoNext = "sexual";
                    if(isForMe) {
                        resources = getResources().getStringArray(R.array.sexual_forMe);
                        resources_names = getResources().getStringArray(R.array.sexual_forMe_names);
                    }
                    else {
                        resources = getResources().getStringArray(R.array.sexual_forSomeoneElse);
                        resources_names = getResources().getStringArray(R.array.sexual_forSomeoneElse_names);
                    }
                }
                else if (abuseTypes[sid] == abuseTypes[2]) {
                    whereToGoNext = "verbal";
                    if(isForMe) {
                        resources = getResources().getStringArray(R.array.verbal_forMe);
                        resources_names = getResources().getStringArray(R.array.verbal_forMe_names);
                    }
                    else {
                        resources = getResources().getStringArray(R.array.verbal_forSomeoneElse);
                        resources_names = getResources().getStringArray(R.array.verbal_forSomeoneElse_names);
                    }
                }
                else if (abuseTypes[sid] == abuseTypes[3]) {
                    whereToGoNext = "mental";
                    if(isForMe) {
                        resources = getResources().getStringArray(R.array.mental_forMe);
                        resources_names = getResources().getStringArray(R.array.mental_forMe_names);
                    }
                    else {
                        resources = getResources().getStringArray(R.array.mental_forSomeoneElse);
                        resources_names = getResources().getStringArray(R.array.mental_forSomeoneElse_names);
                    }
                }

                //ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, resources);
                ArrayList<String> a = new ArrayList<>(Arrays.asList(resources_names));
                ArrayList<String> b = new ArrayList<>(Arrays.asList(resources));
                ResourceRow mAdapter = new ResourceRow(context, a, b, activity);
                ListView listView = findViewById(R.id.listView2);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                whereToGoNext = "physical";
            }
        });
    }



    //The two functions below are part of the internet code. These can be duplicated in any class
    // to call the InternetActivity
    // This function gets a URL from the XML string list
    public void getWebUrl(View view){
        String url = getString(R.string.web_address1);
        openInternet(url);
    }

    // Gets the URL and passes it to the associated presenter
    public void openInternet(String url){
        InformationPresenter presenter = new InformationPresenter(this);
        presenter.goToInternet(url);
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    public void goToOptions(View view){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

    // Save the Singleton Map when the activity is ended.
    @Override
    protected void onStop() {
        super.onStop();
        AdminSingleton.saveMap(this);
    }
}
