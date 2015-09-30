package com.czd.xinbei.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.czd.xinbei.R;
import com.czd.xinbei.adapter.ItemAdapter;
import com.czd.xinbei.avtivity.ProjectActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/21)
 * Description:精品项目的fragment
 * To change this template use File | Settings | File Templates.
 */
public class SyJPOneFragment extends Fragment  {

    private String TAG=SyJPOneFragment.class.getName();
    private ListView lv_jpxm;
    private ItemAdapter Myadapter;
    private Context context;
    private List<Object> data;
    private int layoutId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.syjpfragment,container,false);
        lv_jpxm=(ListView)v.findViewById(R.id.lv_jpxm);
        init();
        Log.i(TAG, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!---------");

        return v;
    }

    public void init() {
        Log.e(TAG, "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!+++++++++++++++");
        context=getActivity();
        data=getData();
        Myadapter=new ItemAdapter(context,data,R.layout.syjpitem);
        lv_jpxm.setAdapter(Myadapter);
        lv_jpxm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"第"+position+"个",Toast.LENGTH_LONG).show();
                Log.e("jp", "position=====" + position);
                Intent intent=new Intent();
                intent.setClass(context, ProjectActivity.class);
                startActivity(intent);

            }
        });
    }

    //获得数据源
    private  List<Object> getData(){
        data=new ArrayList<Object>();
        data.add("0");
        data.add("0");
        data.add("0");
        data.add("0");
        data.add("0");
        Log.e(TAG, " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + data.toString());
        return data;
    }


}
