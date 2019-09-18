package com.all.soreyo;

import android.os.Bundle;
import android.webkit.WebView;

public class GifActivity extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView wView = new WebView(this);
        //wView.setBackgroundColor(0x00000000);
        //wView.setBackgroundColor(R.drawable.images);
        //wView.setBackground(#000000);
        wView.loadUrl("file:///android_asset/snow.gif");
        setContentView(wView);
    }
}
