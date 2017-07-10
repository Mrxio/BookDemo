package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Adapter.OderViewPagerAdapter;
import com.strawberrysoft.bookdemo.R;

public class AllOderListActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private RadioButton tv_tab1;
    private RadioButton tv_tab2;
    private RadioButton tv_tab3;
    private RadioButton tv_tab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_oder_list);
        initView();
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0  );
        viewPager.setCurrentItem(type);
    }
    public void initView(){
        tv_tab1 = (RadioButton) findViewById(R.id.tv_oder_tab1);
        tv_tab1.setTextColor(Color.RED);
        tv_tab1.setOnClickListener(this);
        tv_tab2 = (RadioButton) findViewById(R.id.tv_oder_tab2);
        tv_tab2.setOnClickListener(this);
        tv_tab3 = (RadioButton) findViewById(R.id.tv_oder_tab3);
        tv_tab3.setOnClickListener(this);
        tv_tab4 = (RadioButton) findViewById(R.id.tv_oder_tab4);
        tv_tab4.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.vp_alloder_viewpager);
        viewPager.setAdapter(new OderViewPagerAdapter(getSupportFragmentManager()));
        findViewById(R.id.iv_oderlist_back).setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tv_tab1.setTextColor(Color.RED);
                        tv_tab2.setTextColor(Color.BLACK);
                        tv_tab3.setTextColor(Color.BLACK);
                        tv_tab4.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        tv_tab2.setTextColor(Color.RED);
                        tv_tab1.setTextColor(Color.BLACK);
                        tv_tab3.setTextColor(Color.BLACK);
                        tv_tab4.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        tv_tab3.setTextColor(Color.RED);
                        tv_tab1.setTextColor(Color.BLACK);
                        tv_tab2.setTextColor(Color.BLACK);
                        tv_tab4.setTextColor(Color.BLACK);
                        break;
                    case 3:
                        tv_tab4.setTextColor(Color.RED);
                        tv_tab1.setTextColor(Color.BLACK);
                        tv_tab2.setTextColor(Color.BLACK);
                        tv_tab3.setTextColor(Color.BLACK);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_oder_tab1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_oder_tab2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_oder_tab3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_oder_tab4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.iv_oderlist_back:
                finish();
                break;
        }
    }
}
