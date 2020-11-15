package com.example.sosbicicletta.WebActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sosbicicletta.R;

public class CS extends AppCompatActivity {

private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_s);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi Siamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);



        webView = findViewById(R.id.webviewci);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.mobilityevolution.it/chi-siamo/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }


}
