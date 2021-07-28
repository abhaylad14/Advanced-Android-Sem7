package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class webview extends AppCompatActivity {

    WebView wv;
    Button btnshow;
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ControlInitialization();
        events();
    }
    private void ControlInitialization(){
        btnshow = findViewById(R.id.btnshow);
        wv = findViewById(R.id.wv);
        wv.setWebViewClient(new MyBrowser());
    }
    private void events(){
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = "https://www.google.com/";
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setLoadsImagesAutomatically(true);
                wv.loadUrl(URL);
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}