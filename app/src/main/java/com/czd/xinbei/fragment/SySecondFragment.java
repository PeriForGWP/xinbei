package com.czd.xinbei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.czd.xinbei.R;

/**
 * Created with Android Studio.
 * Author: Gao.wanpeng
 * Create: Gao.wanpeng (2015/9/18)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public class SySecondFragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sysecondfragment,null);
        TextView btn_gqzc,btn_qyrz,btn_jj,btn_yhlc,btn_smcp,btn_bx,btn_xyk,btn_xt;
        btn_gqzc=(TextView)view.findViewById(R.id.btn_gqzc);
        btn_xt=(TextView)view.findViewById(R.id.btn_xt);
        btn_jj=(TextView)view.findViewById(R.id.btn_jj);
        btn_yhlc=(TextView)view.findViewById(R.id.btn_yhlc);
        btn_smcp=(TextView)view.findViewById(R.id.btn_smcp);
        btn_bx=(TextView)view.findViewById(R.id.btn_bx);
        btn_xyk=(TextView)view.findViewById(R.id.btn_xyk);
        btn_qyrz=(TextView)view.findViewById(R.id.btn_qyrz);
        btn_gqzc.setOnClickListener(this);
        btn_qyrz.setOnClickListener(this);
        btn_jj.setOnClickListener(this);
        btn_smcp.setOnClickListener(this);
        btn_bx.setOnClickListener(this);
        btn_yhlc.setOnClickListener(this);
        btn_xyk.setOnClickListener(this);
        btn_xt.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_gqzc:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_qyrz:
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_jj:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_yhlc:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_smcp:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_bx:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_xyk:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_xt:
                //暂时设置为toast
                Toast.makeText(getActivity(),"第二页toast",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
