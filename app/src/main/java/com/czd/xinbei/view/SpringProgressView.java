package com.czd.xinbei.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.czd.xinbei.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * 自定义渐变进度条
 * Author: YangYi
 * Create: YangYi (2015/9/17)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SpringProgressView extends View {
    private Handler handler=new Handler() {
        @Override
        public void close() {


        }

        @Override
        public void flush() {

        }

        @Override
        public void publish(LogRecord record) {

        }
    };
    /**分段颜色*/
    private int[] section_colors;
    /**背景颜色*/
    private int progressBg;
    /**进度条最大值*/
    private float maxCount;
    /**进度条当前值*/
    private float currentCount;
    /**画笔*/
    private Paint mPaint;
    private int mWidth,mHeight;

    public SpringProgressView(Context context) {
        super(context);
        initView(context);
    }

    public SpringProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SpringProgressView(Context context,AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        section_colors=new int[]{getResources().getColor(R.color.progressStart),getResources().getColor(R.color.progressEnd)};
        progressBg=getResources().getColor(R.color.progressBg);
        this.maxCount=100;
        this.currentCount=80;
    }

    /**
     * 根据目标值和现实值现实进度条进度
     * @param target
     * @param reality
     */
    public void setProgressByTarget(int target,int reality){
        this.currentCount=100/target*reality;

        invalidate();
    }

    /**
     * 根据百分比重新设置进度条进度
     * @param value
     */
    public void setProgressValue(int value){
        this.currentCount=value;

        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        int round=mHeight;
        mPaint.setColor(Color.rgb(71, 76, 80));
        RectF rectBg=new RectF(0,0,mWidth,mHeight);
        canvas.drawRoundRect(rectBg, round, round, mPaint);
        mPaint.setColor(progressBg);
//        RectF rectGrayBg=new RectF(2,2,mWidth-2,mHeight-2);
        canvas.drawRoundRect(rectBg,round,round,mPaint);
        /**当前值和最大值比例*/
        float section=currentCount/maxCount;
        RectF rectProgressBg=new RectF(0,0,mWidth*section,mHeight);
        if(section<=1.0f/2.0f){
            if(section!=0.0f){
                mPaint.setColor(section_colors[0]);
            }else{
                mPaint.setColor(Color.TRANSPARENT);
            }
        }else{
            int count=2;
              int[] colors=new int[count];
              System.arraycopy(section_colors,0,colors,0,count);
            LinearGradient shader=new LinearGradient(0,0,mWidth*section,mHeight,colors,null, Shader.TileMode.MIRROR);
            mPaint.setShader(shader);
        }
        canvas.drawRoundRect(rectProgressBg,round,round,mPaint);
    }

    private int dipTop(int dip){
        float scale=getContext().getResources().getDisplayMetrics().density;
        return (int)(dip*scale+0.5f*(dip>=0?1:-1));
    }

    /**
     * 设置最大的进度值
     * @param maxCount
     */
    public void setMaxCount(float maxCount){
        this.maxCount=maxCount;
    }

    /**
     * 设置当前进度值
     * @param currentCount
     */
    public void setCurrentCount(float currentCount){
        this.currentCount=currentCount>maxCount?maxCount:currentCount;
        invalidate();
    }

    public float getMaxCount(){
        return maxCount;
    }

    public float getCurrentCount(){
        return currentCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode==MeasureSpec.EXACTLY||widthSpecMode==MeasureSpec.AT_MOST){
            mWidth=widthSpecSize;
        }else{
                mWidth=0;
        }

        if(heightSpecMode==MeasureSpec.AT_MOST||heightSpecMode==MeasureSpec.UNSPECIFIED){
            mHeight=dipTopx(getContext(),15);
        }else{
            mHeight=heightSpecSize;
        }
        setMeasuredDimension(mWidth,mHeight);
    }
    public static int dipTopx(final Context ctx, float dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, ctx.getResources().getDisplayMetrics());
    }
}
