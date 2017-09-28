package com.example.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.headline.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class WebViewActivity extends AppCompatActivity {

    private WebView web;
    private TextView keywords;
    private TextView abstr;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //找到控件
        web = (WebView) findViewById(R.id.web);
        Intent intent = getIntent();
        //获取传过来的url
        final String url = intent.getStringExtra("url");
        //后退网页
        web.canGoBack();
        web.goBack();
        WebSettings settings = web.getSettings();
        //支持js的交互
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        //设置可以支持缩放
        settings.setSupportZoom(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //设置是否出现缩放工具
        settings.setBuiltInZoomControls(true);
        settings.setDefaultTextEncodingName("utf-8");
        //加载
        web.loadUrl(url);
        //把网页显示到当前Activity
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
//        keywords = (TextView) findViewById(R.id.keywords);
//        abstr = (TextView) findViewById(R.id.abstr);
//        img = (ImageView) findViewById(R.id.img);
//        Intent intent = getIntent();
//        String keyword = intent.getStringExtra("keyword");
//        String abstrb = intent.getStringExtra("abstr");
//        String imgage = intent.getStringExtra("img");
//        keywords.setText(keyword);
//        abstr.setText(abstrb);
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(WebViewActivity.this);
//        ImageLoader loader = ImageLoader.getInstance();
//        loader.init(configuration);
//        loader.displayImage(imgage,img);
    }
}
