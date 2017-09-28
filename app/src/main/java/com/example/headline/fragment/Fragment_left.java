package com.example.headline.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.headline.R;
import com.example.headline.activity.PhoneActivity;
import com.example.headline.activity.SetActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Fragment_left extends Fragment {
    //全局变量
    private ListView listview;
    String[] name=new String[]{"好友动态","与我相关","我的头条","我的话题","收藏","活动","商城"};
    private List<String> list;
    private Button btn;
    private ImageView qq_login;
    private TextView tv;
    private ImageView night_iv;
    private View view;
    private ImageView phone_login;
    private ImageView set;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_left, null);

        //初始化数据
        initView();
        //点击实现夜间模式
        night_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayNight();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayNight();
            }

        });
        //qq的登录
            QQ_login();
            Phone_login();
//        Set();
        return view;
    }

//    private void Set() {
//        Intent intent = new Intent(getActivity(), SetActivity.class);
//        startActivity(intent);
//    }

    private void Phone_login() {
        phone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }
    private void QQ_login() {
        qq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(getActivity()).doOauthVerify(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
    }

    private void DayNight() {
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (mode) {
            case Configuration.UI_MODE_NIGHT_NO:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                break;
            case Configuration.UI_MODE_NIGHT_YES:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                break;
        }
        getActivity().recreate();
    }
    private void initView() {
        //找控件
        listview = (ListView) view.findViewById(R.id.listview);
        btn = (Button) view.findViewById(R.id.frag_btnlogin);
        tv = (TextView) view.findViewById(R.id.frag_tv);
        night_iv = (ImageView) view.findViewById(R.id.night_iv);
        qq_login = (ImageView) view.findViewById(R.id.qq_login);
        phone_login = (ImageView) view.findViewById(R.id.phone_login);
        set = (ImageView) view.findViewById(R.id.set);
        //集合
        list = Arrays.asList(name);
        //拿到适配器
        MyAdapter adapter = new MyAdapter();
        listview.setAdapter(adapter);

    }
    //对listview进行适配
    class MyAdapter extends BaseAdapter{

        private ViewHolder holder;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=View.inflate(getActivity(),R.layout.item_frag_left,null);
                holder = new ViewHolder();
                holder.tv= (TextView) convertView.findViewById(R.id.item_frag_left_tv);
               convertView.setTag(holder);
            }else {
               holder= (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(list.get(position));
            return convertView;
        }
        class ViewHolder{
            TextView tv;
        }
    }

}
