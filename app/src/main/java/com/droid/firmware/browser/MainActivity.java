package com.droid.firmware.browser;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    // deklarasi
    private WebView webv;
    private EditText txturl;
    private Button btncari1;
    ProgressBar pg;
    AdView adView; //Ads
    AdRequest adRequest; //Ads

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inisialisasi
        webv = findViewById(R.id.webv);
        txturl = findViewById(R.id.txturl);
        btncari1 = findViewById(R.id.btncari);
//        //ads
//        Ads();

        // siapkan urlnya
        String url = "https://www.google.co.id/";
        // inisialisasi kebutuhan browser

        webv.getSettings().setJavaScriptEnabled(true); // untuk mendukung java script
        webv.getSettings().setDisplayZoomControls(true); // mengontrol zoom pada browser
        webv.getSettings().setLoadWithOverviewMode(true); //otomatis load zoom
        webv.getSettings().setUseWideViewPort(true); // memberi tahu browser untuk mengaktifkan Wide Viewport
        webv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //otomatis menampilkan java script window
        webv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webv.getSettings().setAllowFileAccessFromFileURLs(true);
        webv.getSettings().setAllowUniversalAccessFromFileURLs(true);

       pg = (ProgressBar) findViewById(R.id.progressBar4) ;
        webv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pg.setVisibility(View.VISIBLE);
                pg.setProgress(newProgress);
                if (newProgress == 100){
                    pg.setVisibility(View.GONE);
                }
            }
        });
        webv.loadUrl(url);
        webv.setWebViewClient(new MyWebLaunch());

        btncari1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = txturl.getText().toString();
                // aktifkan java script
                webv.getSettings().setJavaScriptEnabled(true);
                webv.getSettings().setDisplayZoomControls(true);
                pg = (ProgressBar) findViewById(R.id.progressBar4);
                webv.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        pg.setVisibility(View.VISIBLE);
                        pg.setProgress(newProgress);
                        if (newProgress == 100){
                            pg.setVisibility(View.GONE);
                        }
                    }
                });
                webv.loadUrl(url);
                webv.setWebViewClient(new MyWebLaunch());
            }
        });
    }

    private class MyWebLaunch extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK :
                    if (webv.canGoBack()){
                        webv.goBack();
                    }else {
                        finish();
                    }
                    return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

//    //ads
//    private void Ads (){
//        adView = (AdView) findViewById(R.id.adview);
//        adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//    }

}
