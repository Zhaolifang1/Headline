package com.example.headline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.headline.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

public class PhoneActivity extends AppCompatActivity {
    private String phoneNumber = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

    }
    public void register(View view) {
        // 打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    phoneNumber = phone;

                    Log.e("PhoneNumber", phone);
                    // 提交用户信息（此方法可以不调用）
                    // registerUser(country, phone);

                }
            }
        });
        registerPage.show(this);


    }

    public void friends(View view) {
        // 打开通信录好友列表页面
        ContactsPage contactsPage = new ContactsPage();
        contactsPage.show(this);
    }
}
