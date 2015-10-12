package com.czd.xinbei.slidingmenu;

/**
 * Created with Android Studio.
 * Author: GaoWanpeng
 * Create: GaoWanpeng (2015/10/12)
 * Description:
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.czd.xinbei.R;


public class MenuContent  extends RelativeLayout {

    private MenuClickListener menuClickListener;


    public MenuContent(Context context) {
        this(context, null, 0);
    }

    public MenuContent(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public MenuContent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu, this);

    }
    /**
     * 设置指定View是否可见
     * @param vid
     * @param visible
     */
    public void setViewVisible(int vid, boolean visible){
        if(findViewById(vid) == null)
            return;

        if (visible) {
            findViewById(vid).setVisibility(View.VISIBLE);
        } else {
            findViewById(vid).setVisibility(View.GONE);
        }
    }

    public void setMenuClickListener(MenuClickListener menuClickListener){
        this.menuClickListener = menuClickListener;
    }

    public interface MenuClickListener{
        public void onClick(View view);
    }
}
