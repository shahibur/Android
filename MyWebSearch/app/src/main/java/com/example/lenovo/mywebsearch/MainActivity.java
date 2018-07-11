package com.example.lenovo.mywebsearch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText ed;
    Button button;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView=findViewById(R.id.webView);
        ed=findViewById(R.id.editText);
        button=findViewById(R.id.button);



       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String s=ed.getText().toString();

               WebSettings webSetting;

               webView.setWebViewClient(new WebViewClient());

               webSetting=webView.getSettings();

               webSetting.setJavaScriptEnabled(true);

               webView.loadUrl(s);
           }
       });

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
       else if(webView.canGoForward())
        {
            webView.goForward();
        }
        else
            super.onBackPressed();
    }

}
