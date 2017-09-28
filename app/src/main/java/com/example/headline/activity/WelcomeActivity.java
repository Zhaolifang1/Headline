package com.example.headline.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.headline.MainActivity;
import com.example.headline.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final int SLEEP_time = 3000;//欢迎界面的显示时间
    private static final int GO_home = 100;
    private static final int GO_Viewpager = 101;
    private boolean isfrist = false;  //记录是否是第一次进入APP
    private SharedPreferences sp;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case GO_Viewpager://第一次进入显示Viewpager的滑动图片
                    Intent intentpager = new Intent(WelcomeActivity.this, SecondActivity.class);
                    startActivity(intentpager);
                    WelcomeActivity.this.finish();
                    break;
                case GO_home://第二次进入显示一张图片3s之后跳转到主页面
                    Intent intenthome = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intenthome);
                    WelcomeActivity.this.finish();
                    break;

            }
        }

    };
    private ImageView welcomeimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //初始化
        initView();
    }

    private void initView() {
        sp = getSharedPreferences("user", MODE_PRIVATE);
        //判断是否是第一次登录默认第一次进入
        isfrist = sp.getBoolean("isfrist", true);
        //找控件
        welcomeimg = (ImageView) findViewById(R.id.welcomeimg);
        //第一次进入
        if (isfrist) {
            handler.sendEmptyMessage(GO_Viewpager);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isfrist", false);
            editor.commit();
        } else {//第二次进入显示图片3s
            welcomeimg.setVisibility(View.VISIBLE);
            handler.sendEmptyMessageDelayed(GO_home, 3000);
        }
    }
}
