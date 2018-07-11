package com.example.lenovo.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb=findViewById(R.id.webView);
        wb.setWebViewClient(new WebViewClient());

        WebSettings webSettings=wb.getSettings();

        webSettings.setJavaScriptEnabled(true);


        wb.loadUrl("http://www.youtube.com");

    }

    @Override
    public void onBackPressed() {
        if(wb.canGoBack())
        {
            wb.goBack();
        }
        else
          super.onBackPressed();

    }
}
