package com.czd.xinbei.avtivity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.czd.xinbei.R;
import com.czd.xinbei.adapter.SyFragmentAdapter;
import com.czd.xinbei.adapter.SyJPFragmentAdapter;
import com.czd.xinbei.adapter.SyPagerAdapter;
import com.czd.xinbei.fragment.SyJPOneFragment;
import com.czd.xinbei.listener.MyJPXMPageChangeListener;
import com.czd.xinbei.listener.SyMyViewPagerListener;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    //头像,搜索的那个放大镜图片
    private ImageView headImage,searchImage,cursor;

    private ViewPager viewPager2,viewPager1,viewPager3;
    //用来装下面的那个点
   private ImageView[] tips1,tips2;
    //用来装imageview数组
    private View[] mImagViews;
    //用来装标题的
    TextView tv_zhtj,tv_zxsx,tv_jezg,tv_gzzd,searchText;
    //图片资源id
    private int[] imgIdArray1,imgIdArray2;
    private Fragment fragment;
    private int offset=0;
    private int  currIndex=0;
    private int btmWidth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sy);
        init();
    }
    /**
     * 初始化
     */
    public void init(){
        headImage=(ImageView)findViewById(R.id.sy_head);
        searchImage=(ImageView)findViewById(R.id.search_image);
        searchText=(TextView)findViewById(R.id.search_words);
        searchText.setOnClickListener(this);
        headImage.setOnClickListener(this);
       //广告条
       initViewPager();
        //八个小圆形
        initFragmentViewPager();
        //精品项目
        initJPXM();
    }
    /**
     * 初始化上面（新三板那块）的viewpager
     */
    public void initViewPager(){
        ViewGroup group=(ViewGroup)findViewById(R.id.viewGroup1);
        viewPager1=(ViewPager)findViewById(R.id.viewPager1);
        //载入图片资源ID
        imgIdArray1=new int[]{R.drawable.sy_banner,R.drawable.sy_banner,
                R.drawable.sy_banner, R.drawable.sy_banner,R.drawable.sy_banner};
        Log.e("imgIdArray", "----------------" + imgIdArray1.toString());
        //将点点加入到viewgroup里
        tips1=new ImageView[imgIdArray1.length];
        Log.e("initViewPagertips1的长度","-------------------------->"+tips1.length);
        putTipsToViewGroup(group, tips1);

        //将图片载入到数组里
        mImagViews=new View[imgIdArray1.length];
        for (int i=0;i<mImagViews.length;i++){
            ImageView imageView=new ImageView(this);
            mImagViews[i]=imageView;
            imageView.setBackgroundResource(imgIdArray1[i]);
        }

        SyPagerAdapter sy=new SyPagerAdapter(mImagViews);
        viewPager1.setAdapter(sy);
        viewPager1.setOnPageChangeListener(new SyMyViewPagerListener(tips1));
        viewPager1.setCurrentItem((mImagViews.length) * 100);
    }
    /**
     * 初始化中间那八个小圆圈部分
     */
    public void initFragmentViewPager(){
        imgIdArray2=new int[]{R.drawable.sy_qhk,R.drawable.sy_qhs};
        ViewGroup group2=(ViewGroup)findViewById(R.id.viewGroup2);
        tips2=new ImageView[imgIdArray2.length];
        putTipsToViewGroup(group2, tips2);
        viewPager2=(ViewPager)findViewById(R.id.viewPager2);
        SyFragmentAdapter syFragmentAdatper=new SyFragmentAdapter(getSupportFragmentManager(),fragment);
        viewPager2.setAdapter(syFragmentAdatper);
        viewPager2.setOnPageChangeListener(new SyMyViewPagerListener(tips2));
    }

    /**
     * 初始化精品项目
     */
    public void initJPXM(){
        //下面四个页面的list
        List<Fragment> fragmentList=new ArrayList<Fragment>();
        //页面标题的list
//        List<String> titleList=new ArrayList<String>();

        //叶卡头标
        //动画图片的宽度
        int bmpWidth;
        tv_zhtj=(TextView)findViewById(R.id.tv_zhtj);
        tv_zxsx=(TextView)findViewById(R.id.tv_zxsx);
        tv_jezg=(TextView)findViewById(R.id.tv_jezg);
        tv_gzzd=(TextView)findViewById(R.id.tv_gzzd);
        tv_zhtj.setOnClickListener(this);
        tv_zxsx.setOnClickListener(this);
        tv_jezg.setOnClickListener(this);
        tv_gzzd.setOnClickListener(this);
        viewPager3=(ViewPager)findViewById(R.id.viewPager3);
        fragmentList.add(new SyJPOneFragment());
        fragmentList.add(new SyJPOneFragment());
        fragmentList.add(new SyJPOneFragment());
        fragmentList.add(new SyJPOneFragment());
        //初始化那个红色标识动画
        cursor=(ImageView)findViewById(R.id.cursor);
        DisplayMetrics dm=new DisplayMetrics();
        //获得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
       //获得屏幕宽度
        int screenW=dm.widthPixels;
         btmWidth=screenW/4;
        //设定这个cursor的宽
        cursor.setLayoutParams(new LinearLayout.LayoutParams(btmWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
        Log.i("xinbei","屏幕的宽度++++++++++++++++"+screenW);

        Log.i("xinbei","bitmap的值++++++++++++++++"+btmWidth);
        //计算偏移量
        offset=(screenW/4-btmWidth)/2;
        Log.e("xinbei","我就看看是不是==0"+offset);
        Matrix matrix=new Matrix();
        matrix.postTranslate(offset,0);
        //设置动画初始位置
        cursor.setImageMatrix(matrix);
        viewPager3.setAdapter(new SyJPFragmentAdapter(getSupportFragmentManager(), fragmentList));
        viewPager3.setCurrentItem(0);
        viewPager3.setOnPageChangeListener(new MyJPXMPageChangeListener(offset,btmWidth,currIndex,cursor));
    }
    /**
     * 把下面的那个点放进viewgroup里
     * @param
     */
    private void putTipsToViewGroup(ViewGroup group,ImageView[] tips){
        Log.e("tips","++++++++++++++++++++++++"+tips.length);
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


    //监听点击事件
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.tv_gzzd:
              viewPager3.setCurrentItem(3);
                break;
            case R.id.tv_jezg:
                viewPager3.setCurrentItem(2);
                break;
            case R.id.tv_zxsx:
                viewPager3.setCurrentItem(1);
                break;
            case R.id.tv_zhtj:
                viewPager3.setCurrentItem(0);
                break;
            case R.id.sy_head:
                Toast.makeText(this,"此时应该出来个menu",Toast.LENGTH_LONG).show();
                break;
            case R.id.search_words:
//                Toast.makeText(this,"尼玛",Toast.LENGTH_LONG).show();
                intent=new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

}
