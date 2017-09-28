package com.example.headline.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.headline.R;
import com.example.headline.adapter.MyAdapter;
import com.example.headline.sqlite.Mypindaosqlite;

import java.util.ArrayList;
import java.util.List;

public class Draggrid extends AppCompatActivity implements View.OnClickListener{

    private Button btn_pindao;
    private GridView my_pindao;
    private GridView other_pindao;
    private Mypindaosqlite sqlite;
    private SQLiteDatabase db;
    private List<String> mychannel;
    private List<String> otherchannel;
    private MyAdapter my_adapter;
    private MyAdapter other_adapter;
    private int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draggrid);
        //初始化数据
        initView();
        sqlite = new Mypindaosqlite(Draggrid.this);
        db = sqlite.getWritableDatabase();
        mychannel = new ArrayList<>();
        mychannel.add("热点");
        mychannel.add("财经");
        mychannel.add("科技");
        mychannel.add("段子");
        mychannel.add("汽车");
        mychannel.add("时尚");
        mychannel.add("房产");
        mychannel.add("彩票");
        mychannel.add("独家");
        otherchannel = new ArrayList<>();
        otherchannel.add("头条");
        otherchannel.add("首页");
        otherchannel.add("娱乐");
        otherchannel.add("图片");
        otherchannel.add("游戏");
        otherchannel.add("体育");
        otherchannel.add("北京");
        otherchannel.add("军事");
        otherchannel.add("历史");
        otherchannel.add("教育");
    }

    private void initView() {
        btn_pindao = (Button) findViewById(R.id.btn_pindao);
        btn_pindao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_pindao:
                //拿到PopupWindow用到的布局
                View inflate = LayoutInflater.from(Draggrid.this).inflate(R.layout.pindao_pop, null);
                my_pindao = (GridView) inflate.findViewById(R.id.my_pindao);
                other_pindao = (GridView) inflate.findViewById(R.id.other_pindao);
                my_adapter = new MyAdapter(mychannel, Draggrid.this);
                my_pindao.setAdapter(my_adapter);
                other_adapter = new MyAdapter(otherchannel, Draggrid.this);
                other_pindao.setAdapter(other_adapter);
                //实例化PopupWindow并设置宽高（宽高不设置是出不来的）
                PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                PopupWindow popupWindow = new PopupWindow(inflate,
//                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                //设置popuwindow外部可以点击
                popupWindow.setOutsideTouchable(true);//可以点击
                //设置popuwindow里面的listView有焦点（GridView也可以）
                popupWindow.setFocusable(true);
                //给popupWindow设置动画
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                if (a == 0) {
                    //显示在那个控件下面
                    popupWindow.showAsDropDown(btn_pindao);
                    Log.d("PY", "弹出");
                    a = 1;
                } else if (a == 1) {
                    popupWindow.dismiss();
                    Log.d("PY", "关闭");
                    a = 0;
                }
                //我的频道的条目点击
                my_pindao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("PY", "点击了");
                        //获取点击的条目
                        String item2 = (String) my_adapter.getItem(position);
                        Log.d("PY", item2 + position);
                        //添加到数据库
                        ContentValues values = new ContentValues();
                        values.put("title", item2);
                        db.insert("item", null, values);
                        //把点击的这个条目从集合里删除掉并刷新适配器
                        mychannel.remove(position);
                        Log.d("PY", "删除" + position);
                        my_adapter.notifyDataSetChanged();
                        //从数据库中查询到删除的这个条目
                        Cursor cursor = db.query("item", null, null, null, null, null, null);
                        //遍历查询结果
                        String string = null;
                        while (cursor.moveToNext()) {
                            string = cursor.getString(cursor.getColumnIndex("title"));
                            //把刚才我的频道删除的数据添加到下面其他频道里
                            otherchannel.add(string);
                            other_adapter.notifyDataSetChanged();
                        }

                        //删除这个数据库中的所有数据，（我们需要循环执行点击删除添加，也就是循环查询和添加，不删除的话
                        // ，查询添加的时候就会重复上面已经添加过的）
                        db.delete("item", null, null);
                    }
                });
                //其他频道条目的点击
                other_pindao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //这里的操作差不多和上面是一样的
                        //获取点击的条目
                        String item1 = (String) other_adapter.getItem(position);
                        //添加到数据库
                        ContentValues values1 = new ContentValues();
                        values1.put("title", item1);
                        db.insert("item", null, values1);
                        //把点击的这个条目从集合里删除掉并刷新适配器
                        otherchannel.remove(position);
                        other_adapter.notifyDataSetChanged();
                        //从数据库中查询到刚删除的这个条目
                        Cursor cursor1 = db.query("item", null, null, null, null, null, null);
                        //遍历查询结果
                        String string1 = null;
                        while (cursor1.moveToNext()) {
                            string1 = cursor1.getString(cursor1.getColumnIndex("title"));
                            //把刚才我的频道删除的数据添加到我的频道里
                            mychannel.add(string1);
                            my_adapter.notifyDataSetChanged();
                        }

                        //删除这个数据库中的所有数据，（我们需要循环执行点击删除添加，也就是循环查询和添加，不删除的话
                        // ，查询添加的时候就会重复上面已经添加过的）
                        db.delete("item", null, null);
                    }
                });
                break;
        }
    }
}
