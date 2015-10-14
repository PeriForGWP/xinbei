package com.czd.xinbei.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.czd.xinbei.R;

/**
 * Created with Android Studio.
 * Author: GaoWanpeng
 * Create: GaoWanpeng (2015/10/14)
 * Description:
 */
public class MenuFragment extends Fragment implements View.OnClickListener{
    private Context context;
    private ImageView head;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.menu,null);
        context=getActivity();
        head=(ImageView)view.findViewById(R.id.personaldata);
        head.setOnClickListener(this);
        return  view;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,"这是头像",Toast.LENGTH_LONG).show();
    }
}
