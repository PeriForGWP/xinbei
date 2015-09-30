package com.czd.xinbei.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.czd.xinbei.R;



public class TVActivity extends Activity{

    //用来控制进度条
    private SeekBar seekBar;
    private TextView back;
    private ProgressBar progressBar;
    private Button play;
    //是否在播放
    private Boolean flag=true;
    //用于判断，是否显示出来别的按钮
    private Boolean display;
    private int position;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private  final static  int VIDEO_UPDATE_SEEKBAR=5;
    private final  static  int VIDEO_STATE_BEGIN=2;
    private  final  static  int VIDEO_FILE_ERROR=1;
    //视屏的地址
    private String url;
    private  UpdateSeekBar updateSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //保持屏幕一直亮着
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_tv);
        //他如果联网的话
        url=getIntent().getStringExtra("url");
        //不联网的话
        //url= Environment.getExternalStorageDirectory().getAbsolutePath()+地址
        initVieoPlayer();
        setPlayerListener();
    }

    /**
     * 初始化这个播放器
     */
    private void initVieoPlayer(){
        //创建一个播放器对象
        mediaPlayer=new MediaPlayer();
        //创建更新进度条
        updateSeekBar=new UpdateSeekBar();
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        back=(TextView)findViewById(R.id.back);
        play=(Button)findViewById(R.id.play);
       //刚进来界面，设置不可点击
        play.setEnabled(false);
        surfaceView=(SurfaceView)findViewById(R.id.sv_tv);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.addCallback(new videoSurfaceView());
    }


  private class videoSurfaceView implements SurfaceHolder.Callback{
      @Override
      public void surfaceCreated(SurfaceHolder holder) {
          if (URLUtil.isNetworkUrl(url)){
              mHandler.sendEmptyMessage(VIDEO_STATE_BEGIN);
          }
      }

      @Override
      public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

      }

      @Override
      public void surfaceDestroyed(SurfaceHolder holder) {
          if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
              position=mediaPlayer.getCurrentPosition();
              mediaPlayer.stop();
              flag=false;
              progressBar.setVisibility(View.VISIBLE);
          }
      }
  }

    /**
     * 线程播放视频
     */
    class PlayVedio extends  Thread{
        int post=0;
        public PlayVedio(int post){
            this.post=post;
        }

        @Override
        public void run() {
            try{
                //恢复默认播放器
                mediaPlayer.reset();
                //设置播放源,路径
                mediaPlayer.setDataSource(url);
                mediaPlayer.setDisplay(surfaceHolder);
                //设置监听
                mediaPlayer.setOnPreparedListener(new videoPreparedListener(post));
                //准备播放
                mediaPlayer.prepareAsync();
            }catch (Exception e){
                Log.e("xinbei1","-------------->>"+e);
                mHandler.sendEmptyMessage(VIDEO_FILE_ERROR);
            }
            super.run();
        }
    }

    class videoPreparedListener implements MediaPlayer.OnPreparedListener{

        int position;
        public videoPreparedListener(int position) {
            this.position = position;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            //准备好后，控件就消失
            progressBar.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            display=false;
            if(mediaPlayer!=null){
                //开始播放
                mediaPlayer.start();
            }else {
                return;
            }
            if(position>0){
                mediaPlayer.seekTo(position);
            }
            //启动线程，更新进度条
            new Thread(updateSeekBar).start();
        }
    }
    //设置操作监听
    private  void  setPlayerListener(){
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener(){
            //缓冲更新
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            //视频播放完成
            @Override
            public void onCompletion(MediaPlayer mp) {
                flag = false;
                play.setBackgroundResource(R.drawable.xm_movie);
            }
        });
        //如果视频在播放，点击就调用media.pause()暂停,否则就midia.start()，同时也要更改play按钮的背景
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    play.setBackgroundResource(R.drawable.btn_play);
                    mediaPlayer.pause();
                    position=mediaPlayer.getCurrentPosition();
                }else {
                    if(flag==false){
                        flag=true;
                        new Thread(updateSeekBar).start();
                    }
                    mediaPlayer.start();
                    play.setBackgroundResource(R.drawable.btn_pause);//变成暂停的样子
                }
            }
        });
        //seekBar的监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int value=seekBar.getProgress()*mediaPlayer.getDuration()/seekBar.getMax();
                mediaPlayer.seekTo(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
        });
        //对视频点击监听，控件该显示的显示，不该显示的就隐藏
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display){
                    back.setVisibility(View.GONE);
                    play.setVisibility(View.GONE);
                    display=false;
                }else {
                    back.setVisibility(View.VISIBLE);
                    play.setVisibility(View.VISIBLE);
                    surfaceView.setVisibility(View.VISIBLE);
                    display=true;
                }
            }
        });
        //返回键的点击监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                mediaPlayer=null;
                TVActivity.this.finish();
                //还是跳回去啊
//                Intent intent=new Intent();
//                intent.setClass(TVActivity.this,ProjectDetalActivity.class);
//                startActivity(intent);
            }
        });
    }

    /**
     * 每隔一秒更新一下进度条
     */
    class UpdateSeekBar implements  Runnable{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(VIDEO_UPDATE_SEEKBAR);
            if (flag){
                mHandler.postDelayed(updateSeekBar,1000);
            }
        }
    }
   Handler mHandler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what){
               case VIDEO_FILE_ERROR:
                   Toast.makeText(getApplicationContext(),"错误，不能播放",Toast.LENGTH_LONG).show();
                   Log.e("VIDEO_FILE_ERROR","错误，不能播放");
               case  VIDEO_STATE_BEGIN:
                   playMediaMethod();
                   play.setEnabled(true);
                   play.setBackgroundResource(R.drawable.xm_movie);
                   break;
               default:
                   break;
           }
       }
   };

    /**
     * 播放视屏
     */
    private  void  playMediaMethod(){
        if(position>0&&url!=null){
            //从停止位置开始播放
            new PlayVedio(position).start();
            flag=true;
            int sMax=seekBar.getMax();
            int mMax=mediaPlayer.getDuration();
            seekBar.setProgress(position*sMax/mMax);
            progressBar.setVisibility(View.GONE);
        }else {
            //第一次播放，从头播放
            new PlayVedio(0).start();
        }
    }

    //activity销毁后，释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        System.gc();
    }
}
