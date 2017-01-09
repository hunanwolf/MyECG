package com.compassecg.test720.compassecg.Home.AcitvityW;

import android.os.Bundle;
import android.webkit.WebView;

import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;


public class web extends BarBaseActivity {
    WebView mWebView;
    String url;
    int AFGAHJ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setTitleString("新闻详情");
        url = getIntent().getExtras().getString("url");
        mWebView = getView(R.id.mWebView);

    }



}
