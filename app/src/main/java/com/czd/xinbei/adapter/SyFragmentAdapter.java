package com.czd.xinbei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.czd.xinbei.fragment.SyFirstFragment;
import com.czd.xinbei.fragment.SySecondFragment;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/18)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SyFragmentAdapter extends FragmentPagerAdapter {

   // private ArrayList<Fragment> fragmentList;
    Fragment fragment;
    public SyFragmentAdapter(FragmentManager fm,Fragment fragment) {
        super(fm);
        this.fragment=fragment;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0){
            case 0:
                SyFirstFragment syffm=new SyFirstFragment();
                fragment=syffm;
                break;
            case 1:
                SySecondFragment sysfm=new SySecondFragment();
                fragment=sysfm;
                break;
        }

     return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
