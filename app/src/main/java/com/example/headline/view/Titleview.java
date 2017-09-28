package com.example.headline.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.headline.R;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Titleview extends LinearLayout {

    private ImageView img1;
    private ImageView img2;
    private TextView tv;

    public Titleview(Context context) {
        super(context);
    }

    public Titleview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Titleview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setLeftLister(OnClickListener clickListener) {
        img1.setOnClickListener(clickListener);
    }

    public void setRightLister(OnClickListener clickListener) {
        img2.setOnClickListener(clickListener);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.title_view, this);
        img1 = (ImageView) findViewById(R.id.title_iv1);
        img2 = (ImageView) findViewById(R.id.title_iv2);
        tv = (TextView) findViewById(R.id.title_tv);
    }
}
