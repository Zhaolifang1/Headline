package com.example.headline.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.headline.MainActivity;
import com.example.headline.R;
import com.example.headline.bean.TuijianBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class TuijianAdapter extends BaseAdapter{
    //全局变量
    List<TuijianBean.DataBean> tuilist=new ArrayList<>();
    Context context;
    private final ImageLoader loader;
    private final DisplayImageOptions options;
    private final int TYPE_0=0,TYPE_1=1,TYPE_2=2;
    private ViewHolder0 holder0;
    private ViewHolder1 holder1;
    private ViewHolder2 holder2;
    private PopupWindow popupWindow;
    private ImageView btn_close;

    //构造方法
    public TuijianAdapter(Context context) {
        this.context = context;
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
        loader = ImageLoader.getInstance();
        loader.init(configuration);
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        initPopWindow();
    }

    private void initPopWindow() {
        View view = View.inflate(context, R.layout.listview_pop, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        btn_close = (ImageView) view.findViewById(R.id.btn_close);

    }
    private void showpop(View parent,int x,int y,int position){
        //设置popwindow显示位置
        popupWindow.showAtLocation(parent,0,x,y);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        if (popupWindow.isShowing()){

        }
        //当点击关闭按钮的时候，popupWindow消失
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    public class popAction implements View.OnClickListener{
        int position;

        public popAction(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            int[] ints = new int[2];
            //获取点击按钮的坐标
            v.getLocationOnScreen(ints);
            int x=ints[0];
            int y=ints[1];
            showpop(v,x,y,position);
        }
    }

    //添加
        public void addData(List<TuijianBean.DataBean> tuilist){
            this.tuilist.addAll(tuilist);
            notifyDataSetChanged();
        }
        //更新
        public void updateData(List<TuijianBean.DataBean> tuilist){
            this.tuilist.clear();
            addData(tuilist);
        }
    @Override
    public int getCount() {
        return tuilist.size();
    }

    @Override
    public Object getItem(int position) {
        return tuilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else if (tuilist.get(position).getLarge_image_list()!=null){
            return 1;
        }else if (tuilist.get(position).getImage_list()!=null){
            return 2;
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView==null) {
            switch (type) {
                case 0:
                    holder0 = new ViewHolder0();
                    convertView = View.inflate(context, R.layout.item_tui_01, null);
                    holder0.item01_frag_01_title = (TextView) convertView.findViewById(R.id.item_frag01_title);
                    holder0.item01_frag_01_sourse = (TextView) convertView.findViewById(R.id.item_frag01_source);
                    holder0.item01_img_add= (ImageView) convertView.findViewById(R.id.item01_img_add);
                    convertView.setTag(holder0);
                    break;
                case 1:
                    holder1 = new ViewHolder1();
                    convertView = View.inflate(context, R.layout.item_tui_02, null);
                    holder1.item02_frag_01_title = (TextView) convertView.findViewById(R.id.item02_frag01_title);
                    holder1.item02_frag_01_sourse = (TextView) convertView.findViewById(R.id.item02_frag01_source);
                    holder1.item02_frag_01_img = (ImageView) convertView.findViewById(R.id.item02_frag01_img);
                    holder1.item02_img_add= (ImageView) convertView.findViewById(R.id.item02_img_add);
                    convertView.setTag(holder1);
                    break;
                case 2:
                    holder2 = new ViewHolder2();
                    convertView = View.inflate(context, R.layout.item_tui_03, null);
                    holder2.item03_frag_01_sourse = (TextView) convertView.findViewById(R.id.item03_frag01_source);
                    holder2.item03_frag01_img1= (ImageView) convertView.findViewById(R.id.item03_frag01_img1);
                    holder2.item03_frag01_img2= (ImageView) convertView.findViewById(R.id.item03_frag01_img2);
                    holder2.item03_frag01_img3= (ImageView) convertView.findViewById(R.id.item03_frag01_img3);
                    holder2.item03_img_add= (ImageView) convertView.findViewById(R.id.item03_img_add);
                    convertView.setTag(holder2);
                    break;
                default:
                    break;
            }
        }
        else {
            switch (type){
                case 0:
                    holder0= (ViewHolder0) convertView.getTag();
                    break;
                case 1:
                    holder1= (ViewHolder1) convertView.getTag();
                    break;
                case 2:
                    holder2= (ViewHolder2) convertView.getTag();
                    break;
                default:
                    break;
            }
        }
        String title = tuilist.get(position).getTitle();
        String source = tuilist.get(position).getSource();
        switch (type){
            case 0:
                holder0.item01_frag_01_title.setText(title);
                holder0.item01_frag_01_sourse.setText(source);
                holder0.item01_img_add.setOnClickListener(new popAction(position));
                break;
            case 1:
                holder1.item02_frag_01_title.setText(title);
                holder1.item02_frag_01_sourse.setText(source);
                loader.displayImage(tuilist.get(position).getLarge_image_list().get(0).getUrl(),holder1.item02_frag_01_img,options);
               holder1.item02_img_add.setOnClickListener(new popAction(position));
                break;
            case 2:
                holder2.item03_frag_01_sourse.setText(source);
                loader.displayImage(tuilist.get(position).getImage_list().get(0).getUrl(),holder2.item03_frag01_img1,options);
                loader.displayImage(tuilist.get(position).getImage_list().get(1).getUrl(),holder2.item03_frag01_img2,options);
                loader.displayImage(tuilist.get(position).getImage_list().get(2).getUrl(),holder2.item03_frag01_img3,options);
                holder2.item03_img_add.setOnClickListener(new popAction(position));
                break;
            default:
                break;
        }
        return convertView;
    }
    class ViewHolder0{
       TextView item01_frag_01_title,item01_frag_01_sourse;
        ImageView item01_img_add;
    }
    class ViewHolder1{
        TextView item02_frag_01_title,item02_frag_01_sourse;
        ImageView item02_frag_01_img,item02_img_add;
    }
    class ViewHolder2{
       TextView item03_frag_01_sourse;
        ImageView item03_frag01_img1,item03_frag01_img2,item03_frag01_img3,item03_img_add;
    }
}
