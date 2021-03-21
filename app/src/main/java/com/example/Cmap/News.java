package com.example.Cmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.android.volley.toolbox.Volley;

public class News extends AppCompatActivity {
    private WebView webView2;
    private String url = "http://115.85.180.33:3000/news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webView2 = (WebView) findViewById(R.id.webView2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.loadUrl(url);
        webView2.setWebChromeClient(new WebChromeClient());
        webView2.setWebViewClient(new WebViewClientClass());
        //









    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView2.canGoBack()){
            webView2.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    }
}