package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void onResourceClick(View view){
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }

    public void startPhoneCall(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    public void openInternet(String url){
        InformationPresenter presenter = new InformationPresenter(this);
        presenter.goToInternet(url);
    }

    public void onTextClick(){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:741741"));
        smsIntent.putExtra("sms_body", "Help!");
        startActivity(smsIntent);
    }

    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    public void goToOptions(View view){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

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
