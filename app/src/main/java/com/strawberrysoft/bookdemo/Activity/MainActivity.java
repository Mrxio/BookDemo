package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Fragment.FindFragment;
import com.strawberrysoft.bookdemo.Fragment.IndexFragment;
import com.strawberrysoft.bookdemo.Fragment.MessageFragment;
import com.strawberrysoft.bookdemo.Fragment.PersionalFragment;
import com.strawberrysoft.bookdemo.R;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;

    private Class fragments[] = { IndexFragment.class,FindFragment.class, MessageFragment.class, PersionalFragment.class};
    private String tabName[] = {"首页","发现","消息","我的"};
    private int tabImgRes[] = {R.mipmap.icon_1_n,R.mipmap.icon_4_n,R.mipmap.icon_2_n,R.mipmap.icon_3_n};
    private int tabImgResS[] = {R.mipmap.icon_1_d,R.mipmap.icon_4_d,R.mipmap.icon_2_d,R.mipmap.icon_3_d};
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        updateTab();
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        tabHost = (FragmentTabHost) findViewById(R.id.ftb_mainactivity_tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.fl_mainactivity_content);
        int count = fragments.length;
        tabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0 ; i < count ; i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabName[i]).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec,fragments[i],null);
        }
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                updateTab();
            }
        });
    }

    private View getTabItemView(int index) {
        View view = inflater.inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tabitem_imageview);
        imageView.setImageResource(tabImgRes[index]);
        TextView textView = (TextView) view.findViewById(R.id.tv_tabitem_textview);
        textView.setText(tabName[index]);
        return view;
    }
    private void updateTab(){
        TabWidget tabw = tabHost.getTabWidget();
        for(int i = 0; i<tabw.getChildCount(); i++){
            View view=tabw.getChildAt(i);
            ImageView iv=(ImageView)view.findViewById(R.id.iv_tabitem_imageview);
            TextView tv = ((TextView)view.findViewById(R.id.tv_tabitem_textview));
            if(i==tabHost.getCurrentTab()){
                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                iv.setImageResource(tabImgResS[i]);
            }else{
                tv.setTextColor(getResources().getColor(R.color.grayb));
                iv.setImageResource(tabImgRes[i]);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
