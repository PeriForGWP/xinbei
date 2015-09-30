package com.czd.xinbei.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.czd.xinbei.R;

public class AnswerActivity extends Activity implements View.OnClickListener{

    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_answer);
        back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        startActivity((new Intent()).setClass(this,ProblemActivity.class));
    }
}
