package com.czd.xinbei.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.czd.xinbei.R;
import com.czd.xinbei.view.SpringProgressView;


import java.util.List;
import java.util.zip.Inflater;

/**
 * Created with Android Studio.
 * Author: YangYi
 * Create: YangYi (2015/9/18)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class ItemAdapter extends BaseAdapter{
    private Context context;
    private List<Object> data;
    private int layoutId;

    public ItemAdapter(Context context,List<Object> data,int layoutId){
        this.data=data;
        this.layoutId=layoutId;
        this.context=context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHodler hodler;
        if (convertView==null){
            hodler=new ViewHodler();
            view= LayoutInflater.from(context).inflate(layoutId, null);
            hodler.itemImage= (ImageView) view.findViewById(R.id.list_image);
            hodler.itemTitle= (TextView) view.findViewById(R.id.list_title);
            hodler.itemProgress= (SpringProgressView) view.findViewById(R.id.list_progress);
            hodler.itemState= (TextView) view.findViewById(R.id.list_state);
            hodler.itemTarget= (TextView) view.findViewById(R.id.list_target);
            hodler.itemDay= (TextView) view.findViewById(R.id.list_day);
            hodler.itemProgressValue= (TextView) view.findViewById(R.id.list_progress_value);
            view.setTag(hodler);
        }else {
            view=convertView;
            hodler= (ViewHodler) view.getTag();
        }
        hodler.itemImage.setImageBitmap(BitmapFactory.decodeResource(hodler.itemImage.getResources(),R.drawable.jiu_img1));
        hodler.itemState.setText("预热中");
        hodler.itemTitle.setText("港澳美酒-同城读书交流平台");
        hodler.itemProgress.setProgressValue(70);
        hodler.itemTarget.setText("500万");
        hodler.itemDay.setText("23天");
        hodler.itemProgressValue.setText("70");
        return view;
    }

    public class ViewHodler {
        public ImageView itemImage;
        public TextView itemState;
        public TextView itemTitle;
        public SpringProgressView itemProgress;
        public TextView itemTarget;
        public TextView itemDay;
        public TextView itemProgressValue;

    }
}
