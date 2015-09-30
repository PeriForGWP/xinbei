package com.czd.xinbei.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.czd.xinbei.R;

public class ProblemActivity extends Activity implements View.OnClickListener{

    private ImageView iv_faz,iv_zcz,iv_zcdl,iv_qt,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_problem);
        iv_faz=(ImageView)findViewById(R.id.iv_fqz);
        iv_zcz=(ImageView)findViewById(R.id.iv_zcz);
        iv_zcdl=(ImageView)findViewById(R.id.iv_zcdl);
        iv_qt=(ImageView)findViewById(R.id.iv_qt);
        back=(ImageView)findViewById(R.id.back);
        iv_faz.setOnClickListener(this);
        iv_zcz.setOnClickListener(this);
        iv_zcdl.setOnClickListener(this);
        iv_qt.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.iv_fqz:
                intent.setClass(this,AnswerActivity.class);
                startActivity(intent);
                break;
            case  R.id.iv_zcz:
                intent.setClass(this,AnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_zcdl:
                intent.setClass(this,AnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_qt:
                intent.setClass(this,AnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.back:
                Toast.makeText(this,"蹦回上个页面",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
