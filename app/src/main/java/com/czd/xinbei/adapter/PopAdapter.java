package com.czd.xinbei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.czd.xinbei.R;
import com.czd.xinbei.entity.PopEntity;

import java.util.List;

/**
 * Created with Android Studio.
 * Author: YangYi
 * Create: YangYi (2015/9/21)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class PopAdapter extends BaseAdapter{


    private List<PopEntity> data;
    private int layoutId;
    private Context context;

    public PopAdapter(List<PopEntity> data, int layoutId, Context context) {
        this.data = data;
        this.layoutId = layoutId;
        this.context = context;
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
        PopEntity entity= (PopEntity) data.get(position);
        PopViewHoder hoder;
        if(convertView==null){
            hoder=new PopViewHoder();
            convertView= LayoutInflater.from(context).inflate(layoutId,null);
//            hoder.popItemText= (TextView) convertView.findViewById(R.id.pop_content);
//            hoder.popItemState= (ImageView) convertView.findViewById(R.id.pop_state);
            convertView.setTag(hoder);
        }else{
            hoder= (PopViewHoder) convertView.getTag();
        }
        hoder.popItemText.setText(entity.getContent());
        if(entity.getShow()==View.VISIBLE){
            hoder.popItemText.setTextColor(context.getResources().getColor(R.color.popText));
            hoder.popItemState.setVisibility(View.VISIBLE);
        }else{
            hoder.popItemText.setTextColor(context.getResources().getColor(R.color.popNoText));
            hoder.popItemState.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }



    public class PopViewHoder{
        private TextView popItemText;
        private ImageView popItemState;
        private int popSlect;
    }
}
