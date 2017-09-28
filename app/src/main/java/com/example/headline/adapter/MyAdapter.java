package com.example.headline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.headline.R;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class MyAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private ViewHolder holder;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

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
           convertView=View.inflate(context, R.layout.item_pindao,null);
            holder = new ViewHolder();
            holder.pindao_tv= (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.pindao_tv.setText(list.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView pindao_tv;
    }
}
