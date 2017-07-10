package com.strawberrysoft.bookdemo.Fragment;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.strawberrysoft.bookdemo.Activity.AllOderListActivity;
import com.strawberrysoft.bookdemo.Activity.SettingsActivity;
import com.strawberrysoft.bookdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersionalFragment extends Fragment implements View.OnClickListener{

    private Context context;

    public PersionalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persional, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        view.findViewById(R.id.tv_persional_shenhezhong).setOnClickListener(this);
        view.findViewById(R.id.tv_persional_daiziqu).setOnClickListener(this);
        view.findViewById(R.id.tv_persional_daihuan).setOnClickListener(this);
        view.findViewById(R.id.tv_persional_yiwancheng).setOnClickListener(this);
        view.findViewById(R.id.tv_persional_shoucang).setOnClickListener(this);
        view.findViewById(R.id.tv_persional_renzheng).setOnClickListener(this);
        view.findViewById(R.id.tv_persional_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_persional_shenhezhong:
                intent.setClass(context, AllOderListActivity.class);
                intent.putExtra("type",0);
                context.startActivity(intent);
                break;
            case R.id.tv_persional_daiziqu:
                intent.setClass(context, AllOderListActivity.class);
                intent.putExtra("type",1);
                context.startActivity(intent);
                break;
            case R.id.tv_persional_daihuan:
                intent.setClass(context, AllOderListActivity.class);
                intent.putExtra("type",2);
                context.startActivity(intent);
                break;
            case R.id.tv_persional_yiwancheng:
                intent.setClass(context, AllOderListActivity.class);
                intent.putExtra("type",3);
                context.startActivity(intent);
                break;
            case R.id.tv_persional_shoucang:
                Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_persional_renzheng:
                Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_persional_setting:
                intent.setClass(context, SettingsActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
