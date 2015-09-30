package com.czd.xinbei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/29)
 * Description:云标签
 * To change this template use File | Settings | File Templates.
 */
public class KeywordFlow extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener{

    public  static  final  int IDX_X=0;
    public  static  final  int IDX_Y=1;
    public  static  final int IDX_TXT_LENGTH=2;
    public  static  final  int IDX_DIS_Y=3;
    /**由外向内的动画*/
    public  static  final  int ANIMATION_IN=1;
    /**由内向外的动画*/
    public static  final  int ANIMATION_OUT=2;
    /**外围移动到坐标点的位移动画类型*/
    public  static  final  int OUTSIDE_TO_LOCATION=1;
    /**坐标点到外围的位移动画类型*/
    public  static  final  int LOCATION_TO_OUTSIDE=2;
    /**中心点动到坐标的位移动画类型*/
    public  static  final int CENTER_TO_LOCATION=3;
    /**坐标移动到中心点的位移动画类型*/
    public  static  final  int LOCATION_TO_CENTER=4;
    public  static  final  int ANI_DURATION=8001;
    public  static  final int MAX=12;
    public  static  final  int TEXT_SIZE_MAX=15;
    public  static  final  int TEXT_SIZE_MIN=5;
    private  OnClickListener itemClickListener;
    private  static Interpolator interpolator;
    private  static AlphaAnimation animAlpha2Opaque;
    private  static  AlphaAnimation alphaAnimation2Transparent;
    private  static ScaleAnimation scaleAnimationLarge2Normal,scaleAnimationNormal2Large,scaleAnimationZero2Normal,scaleAnimationNormal2Zero;
    private Vector<String> vectorKeywords;//用来存放关键字
    private  int width,height;
    private  boolean enableShow;
    private Random random;
    private  int texAnimationInType,textAnimationOutType;
    private  long LastStartAnimationTime;
    private  long AnimationDuration;

    /**
     * go2Show()中被赋值为true，标识开发人员触发其开始动画显示。
     * 本标识的作用是防止在填充keywrods未完成的过程中获取到width和height后提前启动动画。
     * 在show()方法中其被赋值为false。
     * 真正能够动画显示的另一必要条件：width 和 height不为0。
     */


    public KeywordFlow(Context context) {
        super(context);
    }
    public  KeywordFlow(Context context,AttributeSet arr){
        super(context,arr);
    }
    public  KeywordFlow(Context context,AttributeSet arr,int defStyle){
        super(context,arr,defStyle);
    }

    @Override
    public void onGlobalLayout() {
        int tmpW=getWidth();
        int tmpH=getHeight();
        if(width!=tmpW||height!=tmpH){
            width=tmpW;
            height=tmpH;
            show();
        }
    }

    /**
     * 初始化
     */
    private void init(){
        LastStartAnimationTime=01;
        AnimationDuration=ANI_DURATION;
        random=new Random();
        vectorKeywords=new Vector<>(MAX);
    }

    private  boolean show(){
        return false;
    }

    /**
     * 根据到中心点的距离，由近到远的进行冒泡排序
     * @param listTxt
     * @param endIdx
     */
    private void sortXYList(LinkedList<TextView> listTxt, int endIdx) {
        for (int i = 0; i < endIdx; i++) {
            for (int k = i + 1; k < endIdx; k++) {
                if (((int[]) listTxt.get(k).getTag())[IDX_DIS_Y] < ((int[]) listTxt
                        .get(i).getTag())[IDX_DIS_Y]) {
                    TextView iTmp = listTxt.get(i);
                    TextView kTmp = listTxt.get(k);
                    listTxt.set(i, kTmp);
                    listTxt.set(k, iTmp);
                }
            }
        }
    }
    /**
     * 判断a b线是不是有交集
     * @param startA
     * @param endA
     * @param startB
     * @param endB
     * @return
     */
    private boolean isMaxed(int startA,int endA,int startB,int endB){

        boolean result=false;
        if(startB>=startA&&startA<=endA){
            result=true;
        }else if(endB>=startA&&endB<=endA){
            result=true;
        }else if(startA >= startB && startA <= endB){
            result=true;
        }else  if (endA >= startB && endA <= endB){
            result=true;
        }
        return  result;
    }
    /**
     * 产生随机的xy坐标
     * @return
     */
    private  int[]randomXY(Random rom,LinkedList<Integer>lastX,LinkedList<Integer>lastY,int xItem){
        int[] arr=new int[4];
        arr[IDX_X]= lastX.remove(random.nextInt(lastX.size()));
        arr[IDX_Y]=lastY.remove(random.nextInt(lastY.size()));
        return arr;
    }
    private Vector<String> getVectorKeywords(){
        return  vectorKeywords;
    }
    public void setOnItemclickListener(OnClickListener listener){
        itemClickListener=listener;
    }

}
