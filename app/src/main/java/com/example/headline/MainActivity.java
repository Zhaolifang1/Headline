package com.example.headline;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.headline.fragment.Fragment_01;
import com.example.headline.fragment.Fragment_010;
import com.example.headline.fragment.Fragment_011;
import com.example.headline.fragment.Fragment_012;
import com.example.headline.fragment.Fragment_013;
import com.example.headline.fragment.Fragment_014;
import com.example.headline.fragment.Fragment_015;
import com.example.headline.fragment.Fragment_016;
import com.example.headline.fragment.Fragment_02;
import com.example.headline.fragment.Fragment_03;
import com.example.headline.fragment.Fragment_04;
import com.example.headline.fragment.Fragment_05;
import com.example.headline.fragment.Fragment_06;
import com.example.headline.fragment.Fragment_07;
import com.example.headline.fragment.Fragment_08;
import com.example.headline.fragment.Fragment_09;
import com.example.headline.fragment.Fragment_left;
import com.example.headline.fragment.Fragment_right;
import com.example.headline.view.Titleview;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //全局变量
    private Titleview title;
    private DrawerLayout drawerlayout;
    private TabLayout tab;
    private ViewPager vp;
    List<Fragment> list = new ArrayList<>();
    String[] tabname = new String[]{"推荐", "热点", "本地", "视频", "社会", "娱乐", "科技", "汽车",
            "体育", "财经", "军事", "国际", "段子", "趣图", "健康", "美女"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //设置tablayout以及viewpager
        setTab();
        //点击左侧头像可以显示左侧侧拉fragment
        title.setLeftLister(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.LEFT);
            }
        });
        //点击右侧图片可以显示右侧侧拉fragment
        title.setRightLister(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.RIGHT);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.frag_left, new Fragment_left()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_right,new Fragment_right()).commit();
    }

    private void setTab() {
        for (int i = 0; i < 16; i++) {
            tab.addTab(tab.newTab().setText(tabname[i]));
        }
        for (int i = 0; i < 16; i++) {
            list.add(new Fragment_01());
        }
        //把fragment添加到集合
//        list.add(new Fragment_01());
//        list.add(new Fragment_02());
//        list.add(new Fragment_03());
//        list.add(new Fragment_04());
//        list.add(new Fragment_05());
//        list.add(new Fragment_06());
//        list.add(new Fragment_07());
//        list.add(new Fragment_08());
//        list.add(new Fragment_09());
//        list.add(new Fragment_010());
//        list.add(new Fragment_011());
//        list.add(new Fragment_012());
//        list.add(new Fragment_013());
//        list.add(new Fragment_014());
//        list.add(new Fragment_015());
//        list.add(new Fragment_016());
        //给viewpager添加适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabname[position];
            }
        });
        tab.setupWithViewPager(vp);
    }

    private void initView() {
        title = (Titleview) findViewById(R.id.mytitle);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }
}
