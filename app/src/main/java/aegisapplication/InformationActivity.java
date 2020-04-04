package aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
boolean isForMe;
Spinner abuseTypeSpinner;
String [] abuseTypes;
String whereToGoNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Intent intent = getIntent();
        isForMe = intent.getBooleanExtra("isForMe", true);
        abuseTypes = getResources().getStringArray(R.array.abuse_types);


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
                TextView textView = findViewById(R.id.textView4);
                textView.setMovementMethod(new ScrollingMovementMethod());
                if (abuseTypes[sid] == abuseTypes[0]) {
                    whereToGoNext = "physical";

                    textView.setText(R.string.physical_definition);
                }
                else if (abuseTypes[sid] == abuseTypes[1]) {
                    whereToGoNext = "sexual";
                    textView.setText(R.string.sexual_definition);
                }
                else if (abuseTypes[sid] == abuseTypes[2]) {
                    whereToGoNext = "verbal";
                    textView.setText(R.string.verbal_definition);
                }
                else if (abuseTypes[sid] == abuseTypes[3]) {
                    whereToGoNext = "mental";
                    textView.setText(R.string.mental_definition);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                whereToGoNext = "physical";
                TextView textView = findViewById(R.id.textView4);
                textView.setMovementMethod(new ScrollingMovementMethod());
                textView.setText(R.string.physical_definition);
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

    // Starts the Resources Activity when Resource button is selected.
    public void onClickResources(View view)
    {
        Intent intent = new Intent(this, ResourceActivity.class);
        intent.putExtra("resource_type", whereToGoNext);
        intent.putExtra("audience_type", isForMe);
        startActivity(intent);
    }

    // Starts the Options Activity when the options button is selected.
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
