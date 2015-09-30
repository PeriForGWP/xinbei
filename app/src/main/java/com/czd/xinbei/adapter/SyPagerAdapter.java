package com.czd.xinbei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.czd.xinbei.avtivity.InformationActivity;

import java.util.List;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/17)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SyPagerAdapter extends PagerAdapter {

    //封装view的数组
    private View[] mImagView;


    public SyPagerAdapter(View[] images) {
        this.mImagView=images;

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
            //((ViewPager)viewGroup).addView(mImagView[position], 0);
            ((ViewPager)viewGroup).addView(mImagView[position%mImagView.length],0);
            Log.e("xinbei1", "OOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOOOOOO---mImagView.length===" + mImagView.length);
            Log.e("xinbei1","OOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOOOOOO---position==="+position);
        }catch (Exception e){
            Log.e(" SyPagerAdapter", "指针是---------->" + position);
            Log.e(" SyPagerAdapter", "指针指向的视图是---------->" + mImagView[position].getId());
        }
        mImagView[position%mImagView.length].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent;
                Log.e("xinbei1","*********点击已经相应*****");
            }
        });
        return mImagView[position%mImagView.length];
    }

}
