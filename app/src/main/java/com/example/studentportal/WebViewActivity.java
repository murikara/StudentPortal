package com.example.studentportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webViewPortal)
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        //pak de url en gebruik dit voor de webview
        String url = intent.getStringExtra(MainActivity.URL);
        webView.loadUrl(url);
    }
}

