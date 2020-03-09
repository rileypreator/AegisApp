package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    private Button moveAlert;
    ConstraintLayout ccl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveAlert = findViewById(R.id.closeWelcome);
        ccl = findViewById(R.id.mainLayout);

        moveAlert.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideAlert();
            }
        }));


    }

    private void slideAlert() {

        //https://www.journaldev.com/23344/android-constraint-layout-animation
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_after_alert);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateInterpolator(1.0f));
        transition.setDuration(750);

        TransitionManager.beginDelayedTransition(ccl, transition);
        constraintSet.applyTo(ccl);
    }

    public void sendBegin(View view){
        Intent intent2 = new Intent(this, EmergencyActivity.class);
        startActivity(intent2);
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
}
