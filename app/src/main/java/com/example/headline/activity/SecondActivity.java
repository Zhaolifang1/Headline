package com.example.headline.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.headline.MainActivity;
import com.example.headline.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    //全局变量
    private ViewPager secondvp;
    private TextView second_tv;
    private List<ImageView> list = new ArrayList<>();
    private int[] image = new int[]{R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //初始化数据
        initView();
        //拿到适配器
        MyAdapter adapter = new MyAdapter();
        //绑定
        secondvp.setAdapter(adapter);
        setonvpLister();

    }

    private void setonvpLister() {
        //当滑动到第三张图片的时候显示文字
        secondvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 2) {
                    second_tv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //点击文字跳转到主页面
        second_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        //找到控件
        secondvp = (ViewPager) findViewById(R.id.Secondvp);
        second_tv = (TextView) findViewById(R.id.second_tv);

        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(SecondActivity.this);
            img.setImageResource(image[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(img);
        }
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }
    }
}
