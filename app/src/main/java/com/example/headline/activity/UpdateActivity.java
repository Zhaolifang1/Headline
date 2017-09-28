package com.example.headline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.headline.MainActivity;
import com.example.headline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        x.Ext.init(getApplication());
        x.http().get(new RequestParams("http://mapp.qzone.qq.com/cgi-bin/mapp/mapp_subcatelist_qq?yyb_cateid=-10&categoryName=%E8%85%BE%E8%AE%AF%E8%BD%AF%E4%BB%B6&pageNo=1&pageSize=20&type=app&platform=touch&network_type=unknown&resolution=412x732"), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("app");
                    JSONObject jo = jsonArray.getJSONObject(0);
                    String url = jo.getString("url");
                    String version = jo.getString("versionName");
                    Log.i("xxx", "url:" + url + ",versionName:" + version);
                    String versionName = getVersionName();
                    System.out.println(versionName);
                    /*if (version.compareTo(versionName) > 0) {
                        showChoiseDialog(url);
                    }*/
                    showChoiseDialog(url);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //显示更新选择对话框
    private void showChoiseDialog(final String url) {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.app.AlertDialog dialog = null;

        builder.setTitle("版本更新");
        builder.setMessage("检测到新版本，是否下载更新？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downLoadApk(url);

            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    //从服务器中下载APK
    private void downLoadApk(String path) {

        RequestParams params = new RequestParams(path);
        //自定义保存路径 Environment.getExternalStorageDirectory() sdcard 根目录

        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/app/");
        //自动为文件命令
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {

            //网络请求成功时回调
            @Override
            public void onSuccess(File result) {
                Toast.makeText(UpdateActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                //apk下载完成后 调用系统的安装方法
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri. fromFile(result), "application/vnd.android.package-archive");
                startActivity(intent);
                Toast.makeText(UpdateActivity.this, "安装成功", Toast.LENGTH_SHORT).show();

            }

            //网络请求错误时回调
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            //网络请求取消的时候回调
            @Override
            public void onCancelled(CancelledException cex) {

            }

            //网络请求完成的时候回调
            @Override
            public void onFinished() {

            }

            //网络请求之前回调
            @Override
            public void onWaiting() {

            }

            //网络请求开始的时候回调
            @Override
            public void onStarted() {

            }

            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //文件总大小和当前进度
                Log.i("xxx", total + "," + current);

            }
        });

    }
    //获取版本名称
    private String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo info = null;
        try {
            info = packageManager.getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = info.versionName;
        return versionName;
    }
    //获取版本号
    public String getVersionCode() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionCode = String.valueOf(packInfo.versionCode);
        return versionCode;
    }
}
