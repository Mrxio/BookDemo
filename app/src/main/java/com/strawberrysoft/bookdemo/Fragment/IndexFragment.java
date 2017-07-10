package com.strawberrysoft.bookdemo.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.strawberrysoft.bookdemo.Activity.QRCodeActivity;
import com.strawberrysoft.bookdemo.Activity.SearchActivity;
import com.strawberrysoft.bookdemo.Adapter.IndexAdAdapter;
import com.strawberrysoft.bookdemo.Adapter.IndexRecyclerAdapter;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.Dao.BookInfoDao;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.View.DividerItemDecoration;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private LinearLayout ll_toolbar;
    private Context context;
    private SwipeRefreshLayout srl_refresh;
    private int scrollY = 0;
    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        initView(view);
        return view;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            srl_refresh.setRefreshing(false);
        }
    };
    //初始化组件
    public void initView(View view){
        context = getContext();
        view.findViewById(R.id.rl_indexfragment_search).setOnClickListener(this);//搜索框
        view.findViewById(R.id.iv_indexfragment_qr).setOnClickListener(this);//二维码
        ll_toolbar = (LinearLayout) view.findViewById(R.id.ll_indexfragment_toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_indexfragment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        BookInfoDao dao = new BookInfoDao(context);
        List<DouBanBookBean> list = dao.queryBook();
        Collections.shuffle(list);
        recyclerView.setAdapter(new IndexRecyclerAdapter(context,list));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,LinearLayoutManager.VERTICAL));
        final int hight = (int)(context.getResources().getDisplayMetrics().density*60+0.5f);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                scrollY = scrollY + dy;
                if (scrollY>hight){
                    ll_toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else{
                    ll_toolbar.setBackgroundColor(Color.parseColor("#003F51B5"));
                }
            }
        });
        srl_refresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_index_refresh);
        srl_refresh.setColorSchemeResources(R.color.refresh_color1,R.color.refresh_color2,R.color.refresh_color3,R.color.refresh_color4,R.color.refresh_color5);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(4*1000);
                            handler.sendEmptyMessage(1);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_indexfragment_search://搜索框
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_indexfragment_qr://二维码
                intent.setClass(context, QRCodeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
