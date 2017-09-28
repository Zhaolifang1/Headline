package com.example.headline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.headline.R;
import com.example.headline.activity.Draggrid;
import com.example.headline.activity.UpdateActivity;
import com.example.headline.activity.XunfeiActivity;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Fragment_right extends Fragment {

    private TextView drag;
    private TextView gengxin;
    private TextView xunfei;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null);
        drag = (TextView) view.findViewById(R.id.drag);
        gengxin = (TextView) view.findViewById(R.id.gengxin);
        xunfei = (TextView) view.findViewById(R.id.xunfei);
        drag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Draggrid.class);
                startActivity(intent);
              }
        });
        gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                startActivity(intent);
            }
        });

       xunfei.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), XunfeiActivity.class);
               startActivity(intent);
           }
       });
        return view;
    }
}
