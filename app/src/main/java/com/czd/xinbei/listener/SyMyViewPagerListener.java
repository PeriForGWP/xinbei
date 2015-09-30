package com.czd.xinbei.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.czd.xinbei.R;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/18)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SyMyViewPagerListener implements ViewPager.OnPageChangeListener {
    private ImageView[]tips;
    public SyMyViewPagerListener(ImageView[] tips) {
        this.tips=tips;
    }
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {

        setImageBackground(arg0%tips.length,tips);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
    //设置选中的tip背景
    private void setImageBackground(int selectItems,ImageView[] tips){

        Log.e("listener里的tips的长度","==================>"+tips.length);
        for(int i=0;i<tips.length;i++){
            if (i==selectItems){
                tips[i].setBackgroundResource(R.drawable.sy_qhs);
            }else {
                tips[i].setBackgroundResource(R.drawable.sy_qhk);
            }
        }
    }
}
