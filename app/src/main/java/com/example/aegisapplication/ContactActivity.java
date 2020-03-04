package com.example.aegisapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void onCallClick(View view){
        String number = getString(R.string.hotline_call);
        startPhoneCall(number);
    }

    public void onTTYClick(View view){
        String number = getString(R.string.hotline_TTY_call);
        startPhoneCall(number);
    }

    public void onChatClick(View view){
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

    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.leaveApp();
    }


}
