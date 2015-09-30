package com.czd.xinbei.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.czd.xinbei.R;

public class SearchActivity extends Activity implements View.OnClickListener{

    private String text;
    private EditText editText;
    private ImageView iv_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        init();
    }

    //初始化
    private void init(){
        iv_cancel=(ImageView)findViewById(R.id.cancel);
        iv_cancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                Intent intent=new Intent();
                intent.setClass(SearchActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
