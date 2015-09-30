package com.czd.xinbei.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/22)
 * Description:精品项目中的viewpager监听
 * To change this template use File | Settings | File Templates.
 */
public class MyJPXMPageChangeListener implements ViewPager.OnPageChangeListener {

    int offset;
    int btmWidth;
    int currIndex;

    ImageView cursor;
    //一个的偏移量

    public MyJPXMPageChangeListener(int offset,int bmpWidth,int currIndex,ImageView cursor) {
        this.offset=offset;
        this.btmWidth=bmpWidth;
        Log.i("xinbei1", "MyJPXMPageChangeListener里的bmpwidth的值 " + bmpWidth);
        this.currIndex=currIndex;
        this.cursor=cursor;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        int one=offset*2+btmWidth;

        //两个的偏移量
        int two=one*2;
        //三个的偏移量
        int three=one*3;
        Animation animation=null;
        switch (i){
            case 0:
                if(currIndex==1){
                    animation=new TranslateAnimation(one,0,0,0);
                    Log.e("xinbei1","case0---------1"+currIndex);
                    Log.i("xinbei1","one============="+one);
                }else if(currIndex==2){
                    animation=new TranslateAnimation(two,0,0,0);
                    Log.i("xinbei1", "onPageSelected里的two的值是--------"+two);
                    Log.i("xinbei1","case0-------2"+currIndex);
                }else if (currIndex== 3) {
                    animation = new TranslateAnimation(two,0,0,0);
                    Log.i("xinbei","case0-----------3"+currIndex);
                }
                break;
            case 1:
                if (currIndex==0){
                    animation=new TranslateAnimation(offset,one,0,0);
                    Log.e("xinbei1","case1------->0"+currIndex);
                }else  if(currIndex==2){
                    animation=new TranslateAnimation(two,one,0,0);
                    Log.e("xinbei1","case1-------2"+currIndex);
                }else  if(currIndex==3){
                    animation=new TranslateAnimation(three,one,0,0);
                    Log.e("xinbei1","case1-------3"+currIndex);
                }
                break;
            case 2:
                if(currIndex==0){
                    animation=new TranslateAnimation(offset,two,0,0);
                    Log.e("xinbei1","case2-------0"+currIndex);
                    Log.i("xinbei1","two++++++++++++++++++++++++++++++"+two);
                }else if (currIndex==1){
                    animation=new TranslateAnimation(one,two,0,0);
                    Log.e("xinbei1","case2-------1"+currIndex);
                }else if (currIndex==3){
                    animation=new TranslateAnimation(three,two,0,0);
                    Log.e("xinbei1","case2-------3"+currIndex);
                }
                break;
            case 3:
                if(currIndex==0){
                    animation=new TranslateAnimation(offset,three,0,0);
                    Log.e("xinbei1","case3-------0"+currIndex);
                    Log.i("xinbei1","three++++++++++++++++++++++++++"+three);
                }else if(currIndex==1){
                    animation=new TranslateAnimation(one,three,0,0);
                    Log.e("xinbei1","case3-------1"+currIndex);
                }else if (currIndex==2){
                    animation=new TranslateAnimation(two,three,0,0);
                    Log.e("xinbei1","case3-------2"+currIndex);
                }
                break;
        }
        currIndex=i;
        animation.setFillAfter(true);
        animation.setDuration(300);
        cursor.startAnimation(animation);
        Log.e("xinbei1","运行到了startAnimotion");
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
