package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ResourceActivity extends AppCompatActivity {
boolean isForMe;
String resource_type;
String [] resources;
Spinner abuseTypeSpinner;
String [] abuseTypes = {"Physical","Sexual","Verbal","Emotional/Mental"};
String whereToGoNext;
Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        Intent intent = getIntent();
        isForMe = intent.getBooleanExtra("audience_type", true);
        resource_type = intent.getStringExtra("resource_type");

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

                if (abuseTypes[sid] == "Physical") {
                    whereToGoNext = "physical";
                    if(isForMe)
                        resources = getResources().getStringArray(R.array.physical_forMe);
                    else
                        resources = getResources().getStringArray(R.array.physical_forSomeoneElse);
                }
                else if (abuseTypes[sid] == "Sexual") {
                    whereToGoNext = "sexual";
                    if(isForMe)
                        resources = getResources().getStringArray(R.array.sexual_forMe);
                    else
                        resources = getResources().getStringArray(R.array.sexual_forSomeoneElse);
                }
                else if (abuseTypes[sid] == "Verbal") {
                    whereToGoNext = "verbal";
                    if(isForMe)
                        resources = getResources().getStringArray(R.array.verbal_forMe);
                    else
                        resources = getResources().getStringArray(R.array.verbal_forSomeoneElse);
                }
                else if (abuseTypes[sid] == "Emotional/Mental") {
                    whereToGoNext = "mental";
                    if(isForMe)
                        resources = getResources().getStringArray(R.array.mental_forMe);
                    else
                        resources = getResources().getStringArray(R.array.mental_forSomeoneElse);
                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, resources);

                ListView listView = findViewById(R.id.listView2);
                listView.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
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
        i.leaveApp();

    }
}
