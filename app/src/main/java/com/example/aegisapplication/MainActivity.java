package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBegin(View view){
        Intent intent2 = new Intent(this, EmergencyActivity.class);
        startActivity(intent2);
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.leaveApp();
    }

    public void welcomeMessageClose(View view) {

        boolean visible = true;
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.openingWelcomeConstraint);
        final TextView welcomeMessage = (TextView) transitionsContainer.findViewById(R.id.openingWelcome);
        final Button button = (Button) transitionsContainer.findViewById(R.id.closeWelcome);
        //https://medium.com/@andkulikov/animate-all-the-things-transitions-in-android-914af5477d50

        TransitionManager.beginDelayedTransition(transitionsContainer);

        visible = !visible;
        welcomeMessage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


}
