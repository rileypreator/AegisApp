package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
The ContactActivity displays contact options for various abuse help resources. It can initiate
several resources, including phone call and text message.
 */
public class ContactActivity extends AppCompatActivity {
    Button seven;
    Button eight;
    Button ten;
    Button eleven;
    String btnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        seven = findViewById(R.id.button7);
        eight = findViewById(R.id.button8);
        ten = findViewById(R.id.button10);
        eleven = findViewById(R.id.button11);

        seven.setOnClickListener(listener);
        eight.setOnClickListener(listener);
        ten.setOnClickListener(listener);
        eleven.setOnClickListener(listener);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button7:
                    btnName = getString(R.string.call_tollfree);
                    saveAdminListener();
                    onCallClick();
                    break;
                case R.id.button8:
                    btnName = getString(R.string.chat_247);
                    saveAdminListener();
                    onChatClick();
                    break;
                case R.id.button10:
                    btnName = getString(R.string.call_tollfree_tty);
                    saveAdminListener();
                    onTTYClick();
                    break;
                case R.id.button11:
                    btnName = getString(R.string.start_text);
                    saveAdminListener();
                    onTextClick();
            }
        }
    };

    //Gets the phone number from strings.xml.
    public void onCallClick(){
        String number = getString(R.string.hotline_call);
        startPhoneCall(number);
    }

    public void onTTYClick(){
        String number = getString(R.string.hotline_TTY_call);
        startPhoneCall(number);
    }

    public void onChatClick(){
        String url = getString(R.string.chat_url);
        openInternet(url);
    }

    //Starts the ResourceActivity
    public void onResourceClick(View view){
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }

    // Gets phone number from appropriate button function and opens phone's dial function with
    //  ACTION_DIAL. Loads the phone number automatically.
    public void startPhoneCall(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    // Starts the WebView internet browser. Passes the desired URL.
    public void openInternet(String url){
        InformationPresenter presenter = new InformationPresenter(this);
        presenter.goToInternet(url);
    }

    // Opens the phone's text message and populates the To: address.
    public void onTextClick(){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:741741"));
        smsIntent.putExtra("sms_body", "Help!");
        startActivity(smsIntent);
    }

    // Starts incognito mode, which immediately exits the app to the desired location.
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    // Starts OptionsActivity where the user can make settings changes.
    public void goToOptions(View view){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

    // Saves to the Admin Singelton map whenever a button is pressed.
    public void saveAdminListener(){
        AdminListener smallListener = new AdminListener(btnName, System.currentTimeMillis());
        AdminSingleton.getInstance().setMap(btnName, smallListener);
    }

    // Save the Singleton Map when the activity is ended.
    @Override
    protected void onStop() {
        super.onStop();
        AdminSingleton.saveMap(this);
    }


}
