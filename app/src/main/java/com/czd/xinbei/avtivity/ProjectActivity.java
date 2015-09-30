package com.czd.xinbei.avtivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.czd.xinbei.adapter.SyPagerAdapter1;
import com.czd.xinbei.listener.SyMyViewPagerListener;
import com.czd.xinbei.R;

public class ProjectActivity extends Activity {
    private View[] views,mView;
    private ViewGroup group;
    private ViewPager viewPager;
    private int[] imgIdArray;
    private ImageView[] tips;
    private Context context;
    //播放的按钮
    private ImageView play;
    private View page1;

    private LinearLayout project_zambia;
    private LinearLayout project_careful;
    private LinearLayout project_comment;
    private LinearLayout project_share;
    private Window window=this.getWindow();
    private PopupWindow popComment;
    private PopupWindow popShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.project_details);
        initView();
        context=this;
        initViewPager();
    }
    public void initView(){
        project_zambia= (LinearLayout) findViewById(R.id.project_zambia);
        project_careful= (LinearLayout) findViewById(R.id.project_careful);
        project_comment= (LinearLayout) findViewById(R.id.project_comment);
        project_share= (LinearLayout) findViewById(R.id.project_share);


        View popCommentView = getLayoutInflater().inflate(R.layout.comment, null);
        View popSharetView = getLayoutInflater().inflate(R.layout.share, null);
        popComment=new PopupWindow(popCommentView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        popShare=new PopupWindow(popSharetView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        popComment.setBackgroundDrawable(new ColorDrawable(0));
        popShare.setBackgroundDrawable(new ColorDrawable(0));

        project_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showPop(popComment,0,0);
            }
        });
        project_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showPop(popShare,0,0);
            }
        });
    }

    public void showPop(PopupWindow pop,int x,int y){
        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        pop.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM, x, y);
        //popupwindow获取焦点
        pop.setFocusable(true);
        //popupwindow点击外面就关闭
        pop.setOutsideTouchable(true);
        //弹窗出来的动画
      //  pop.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f; //0.0-1.0
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                getWindow().setAttributes(lp);
            }
        });
        pop.update();
    }
    //初始化viewpager
    private void initViewPager(){

        group=(ViewGroup)findViewById(R.id.viewGroup1);
        viewPager=(ViewPager)findViewById(R.id.viewPager1);

        page1=getLayoutInflater().inflate(R.layout.surfaceview,null);
        imgIdArray=new int[]{R.drawable.xmxq_mi_img,R.drawable.xmxq_mi_img,R.drawable.xmxq_mi_img};
        mView=new View[imgIdArray.length+1];
        tips=new ImageView[imgIdArray.length+1];
        putTipsToViewGroup(group,tips);


        mView[0]=page1;
        for (int i=1;i<mView.length;i++){
            View imageView=new View(this);
            mView[i]=imageView;
            imageView.setBackgroundResource(imgIdArray[i-1]);

        }
        Log.e("xinbei1", "initViewPager的mView的长度=== " + mView.length);
        SyPagerAdapter1 MyAdapter=new SyPagerAdapter1(mView,context);
        viewPager.setAdapter(MyAdapter);
        viewPager.setOnPageChangeListener(new SyMyViewPagerListener(tips));

    }
    //把点儿放进去
    private void putTipsToViewGroup(ViewGroup group,ImageView[] tips){
        Log.e("xinbei1", "++++++++++++++++++++++++" + tips.length);
        for (int i=0;i<tips.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10,10));
            tips[i]=imageView;
            if(i==0){
                tips[i].setBackgroundResource(R.drawable.sy_qhs);
            }else {
                tips[i].setBackgroundResource(R.drawable.sy_qhk);
            }
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
            layoutParams.leftMargin=5;
            layoutParams.rightMargin=5;
            group.addView(imageView,layoutParams);
        }
    }
}
