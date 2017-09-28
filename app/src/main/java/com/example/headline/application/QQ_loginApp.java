package com.example.headline.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;


import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.mob.MobSDK;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class QQ_loginApp extends Application {
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    protected String a() {
        return null;
    }

    protected String b() {
        return null;
    }

    @Override
    public void onCreate() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=59bb3682");
        super.onCreate();
        //qq登录
        UMShareAPI.get(this);
        //手机号注册验证码
        MobSDK.init(this, this.a(), this.b());
        //夜间模式
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }
}
