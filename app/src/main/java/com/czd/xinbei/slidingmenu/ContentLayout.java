package com.czd.xinbei.slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created with Android Studio.
 * Author: GaoWanpeng
 * Create: GaoWanpeng (2015/10/12)
 * Description:
 */
public class ContentLayout extends RelativeLayout {
    private SlidingMenu menu;

    public ContentLayout(Context context) {
        super(context);
    }

    public ContentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSlidingMenu(SlidingMenu menu) {
        this.menu = menu;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (menu.getMenuState()) {
            return true;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (menu.getMenuState()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                menu.closeMenu();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

}