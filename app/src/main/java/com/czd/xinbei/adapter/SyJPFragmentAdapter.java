package com.czd.xinbei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/21)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SyJPFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    //private List<String> titleList;
    public SyJPFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
        //this.titleList=titleList;
    }

    //返回页面的总个数
    @Override
    public int getCount() {
        Log.e("xinbei", "精品Fragment的getCount "+fragmentList.size());
        return fragmentList.size();
    }

//    @Override
//        public CharSequence getPageTitle(int position) {
//        Log.e("title", "-------------------"+titleList.get(position));
//        return titleList.get(position);
//    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }
}
