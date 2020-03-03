package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResourceActivity extends AppCompatActivity {
boolean isForMe;
String resource_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        Intent intent = getIntent();
        isForMe = intent.getBooleanExtra("audience_type", true);
        resource_type = intent.getStringExtra("resource_type");

        ArrayAdapter<String> adapter2;

        if (isForMe) {
            String [] resources = getResources().getStringArray(R.array.mental_forMe);
            adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    resources);
        }
        else {
            String[] resources = getResources().getStringArray(R.array.mental_forSomeoneElse);
            adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resources);
        }

        ListView listView = this.findViewById(R.id.listView2);
        listView.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
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
