package com.czd.xinbei.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/21)
 * Description:用来给精品项目那一块做适配
 * To change this template use File | Settings | File Templates.
 */
public class SyItemAdapter extends BaseAdapter{
    private Context context;
    private List<Object> data;
    private int layoutId;
    public SyItemAdapter(Context context,List<Object> data,int layoutId) {
        this.context=context;
        this.data=data;
        this.layoutId=layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    public class ViewHodler{
        public ImageView itemImage;
        public TextView itemState;
    }
}
