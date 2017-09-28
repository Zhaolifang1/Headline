package com.example.headline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.headline.R;
import com.example.headline.activity.WebViewActivity;
import com.example.headline.adapter.TuijianAdapter;
import com.example.headline.bean.TuijianBean;
import com.example.headline.http.HttpUtil;
import com.example.headline.http.RequestBean;
import com.google.gson.Gson;

import java.util.List;

import me.maxwin.view.XListView;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Fragment_01 extends Fragment implements XListView.IXListViewListener{
    private List<TuijianBean.DataBean> datalist;
    private XListView lv;
    private TuijianAdapter tuijianAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_01, null);
        lv = (XListView) view.findViewById(R.id.tuijian_xl);
        lv.setPullRefreshEnable(true);
        lv.setPullLoadEnable(true);
        lv.setXListViewListener(this);
        tuijianAdapter = new TuijianAdapter(getActivity());
        lv.setAdapter(tuijianAdapter);
        getData(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                TuijianBean.DataBean item= (TuijianBean.DataBean) tuijianAdapter.getItem(position);
                intent.putExtra("url",item.getDisplay_url());
//                TuijianBean.DataBean dataBean = datalist.get(position);
//                intent.putExtra("keyword",dataBean.getKeywords());
//                intent.putExtra("abstr",dataBean.getAbstractX());
//                intent.putExtra("img",dataBean.getLarge_image_list().get(0).getUrl());
//                intent.putExtra( "img",dataBean.getLarge_image_list().get(0).getUrl());
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData(final boolean isAdd) {
        String path="http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82";
        RequestBean bean = new RequestBean(path);
        new HttpUtil().getDataFromServer(getActivity(), bean, new HttpUtil.DataCallBack() {



            @Override
            public void proessData(String json) {
                Gson gson=new Gson();
                TuijianBean tuijianBean = gson.fromJson(json, TuijianBean.class);
                datalist = tuijianBean.getData();
                if (isAdd){
                    tuijianAdapter.addData(datalist);
                }else {
                   tuijianAdapter.updateData(datalist);
                }
                lv.stopLoadMore();
                lv.stopRefresh();

            }
        });
    }

    @Override
    public void onRefresh() {
     getData(false);
    }

    @Override
    public void onLoadMore() {
     getData(true);
    }
}
