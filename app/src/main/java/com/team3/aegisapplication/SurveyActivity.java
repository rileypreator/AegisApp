package com.team3.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SurveyActivity extends AppCompatActivity {
    boolean isForMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // CONSTRUCTOR
        // create activity to determine who the audience is
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    public void onMyselfClick(View view)
    {
        // in this case, the resources should be shown for the actual user
        isForMe = true;

        // start an information activity to show definitions of abuse; pass in the audience
        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("isForMe", isForMe);
        startActivity(intent);
    }

    public void onSomeoneElseClick(View view)
    {
        // in this case, the resources should be shown for someone else
        isForMe = false;

        // start an information activity to show definitions of abuse; pass in the audience
        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("isForMe", isForMe);
        startActivity(intent);
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    public void goToOptions(View view){
        // new intent to go to the options activity to change the incognito default app or website
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
