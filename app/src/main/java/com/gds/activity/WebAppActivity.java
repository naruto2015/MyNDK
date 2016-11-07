package com.gds.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import demo.ndk.com.myndk.R;

public class WebAppActivity extends AppCompatActivity {



    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_app);

        init();

    }

    private void init() {
        webView= (WebView) findViewById(R.id.webview);
        WebSettings setting=webView.getSettings();
        setting.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/webapp/demo/index.html");

    }


}
