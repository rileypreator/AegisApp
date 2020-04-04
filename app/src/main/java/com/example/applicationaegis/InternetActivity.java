package com.example.applicationaegis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

/*
The InternetActivity class creates a web browser, using WebView, that operates internal to the app.
Only the desired URL need be passed, by intent, from another Activity.
 */

public class InternetActivity extends AppCompatActivity {

    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        //Get the URL passed by intent
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.getDisplayZoomControls();
    }


    // When the phone's back button is pressed, the web browser will go to the previous page in its history.
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Initiates Incognito mode to leave app. Can be duplicated for every Activity
    public void startIncognito(View view){
        Incognito i = new Incognito(this);
        i.setIncognitoURL();
    }

    // Save the Singleton Map when the activity is ended.
    @Override
    protected void onStop() {
        super.onStop();
        AdminSingleton.saveMap(this);
    }
}
