package com.example.headline.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpUtil {

    public void getDataFromServer(Context context,RequestBean bean,DataCallBack callBack){
        MyHandler handler = new MyHandler(context,callBack);
        MyTask task = new MyTask(bean,handler);
        int cpunum = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newScheduledThreadPool(cpunum);
        service.execute(task);
    }


    class MyTask extends Thread {
        RequestBean bean;
        MyHandler handler;

        public MyTask(RequestBean bean, MyHandler handler) {
            this.bean = bean;
            this.handler = handler;
        }

        @Override
        public void run() {
            super.run();
            try {
                URL url = new URL(bean.url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (bean.method.equals("POST")){
                    connection.setRequestMethod(bean.method);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(bean.value.getBytes());
                }
                int code = connection.getResponseCode();
                StringBuilder builder = new StringBuilder();
                if(code==200){
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String str = "";
                    while((str = br.readLine())!=null){
                        builder.append(str);
                    }
                }
                Message msg = Message.obtain();
                msg.obj = builder.toString();
                msg.what = code;
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class MyHandler extends Handler {
        Context context;
        DataCallBack callBack;

        public MyHandler(Context context, DataCallBack callBack) {
            this.context = context;
            this.callBack = callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int code = msg.what;
            if(code==200){
                String json = (String) msg.obj;
                callBack.proessData(json);
            }else{
                Toast.makeText(context,"未连接成功",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public abstract interface DataCallBack{
        public abstract void proessData(String json);
    }

}
