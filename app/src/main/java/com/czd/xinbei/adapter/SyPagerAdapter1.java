package com.czd.xinbei.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.czd.xinbei.R;
import com.czd.xinbei.avtivity.InformationActivity;
import com.czd.xinbei.avtivity.TVActivity;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/17)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SyPagerAdapter1 extends PagerAdapter {

    //封装view的数组
    private View[] mImagView;
    private Context context;

    public SyPagerAdapter1(View[] images,Context context) {
        this.mImagView=images;
        this.context=context;

    }

    @Override
    public int getCount() {
        return mImagView.length;
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
    @Override
    public Object instantiateItem(ViewGroup viewGroup, final int position) {
        try{
            ((ViewPager)viewGroup).addView(mImagView[position % mImagView.length], 0);
            Log.e(" xinbei1", "指针指向的视图是---------->" + mImagView[position].getId());
        }catch (Exception e){
            Log.e(" xinbei1", "指针是---------->" + position);
            Log.e(" xinbei1", "指针指向的视图是---------->" + mImagView[position].getId());
        }
        mImagView[position%mImagView.length].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("xinbei1", "*********点击已经相应*****");
                Intent intent=new Intent();
                switch (position){
                    case 0:
                        ((Activity)context).getWindow();
                        Toast.makeText(context,"等下做视屏",Toast.LENGTH_LONG).show();
                        //这里要放进去一个视频网络的地址
//                        intent.putExtra(url,"url");
//                        intent.setClass(context, TVActivity.class);
//                        context.startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(context, InformationActivity.class);
                        context.startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(context,"应该是另一个界面",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        intent.setClass(context, InformationActivity.class);
                        context.startActivity(intent);
                        break;
                }
//                Toast.makeText(context,"NIMABI",Toast.LENGTH_LONG).show();
//                Intent intent=new Intent();
//                intent.setClass(context,InformationActivity.class);
//                context.startActivity(intent);

            }
        });
        return mImagView[position%mImagView.length];
    }


}
