package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
boolean isForMe;
Spinner abuseTypeSpinner;
String [] abuseTypes = {"Physical","Sexual","Verbal","Emotional/Mental"};
String whereToGoNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent = getIntent();
        isForMe = intent.getBooleanExtra("isForMe", true);


        // Get reference of SpinnerView from layout/main_activity.xml
        abuseTypeSpinner =(Spinner)findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item ,abuseTypes);

        abuseTypeSpinner.setAdapter(adapter);

        abuseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid=abuseTypeSpinner.getSelectedItemPosition();

                if (abuseTypes[sid] == "Physical") {
                    whereToGoNext = "physical";
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText(R.string.physical_definition);
                }
                else if (abuseTypes[sid] == "Sexual") {
                    whereToGoNext = "sexual";
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText(R.string.sexual_definition);
                }
                else if (abuseTypes[sid] == "Verbal") {
                    whereToGoNext = "verbal";
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText(R.string.verbal_definition);
                }
                else if (abuseTypes[sid] == "Emotional/Mental") {
                    whereToGoNext = "mental";
                    TextView textView = findViewById(R.id.textView4);
                    textView.setText(R.string.mental_definition);
                }
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

    public void onClickResources(View view)
    {
        Intent intent = new Intent(this, ResourceActivity.class);
        intent.putExtra("resource_type", whereToGoNext);
        intent.putExtra("audience_type", isForMe);
        startActivity(intent);
    }
}
