package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.strawberrysoft.bookdemo.Adapter.BaseBookInfoAdapter;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.DoubanJsonParser;
import com.strawberrysoft.bookdemo.Utils.MyHttpUils;
import com.strawberrysoft.bookdemo.View.DividerItemDecoration;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView rv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        initView();
        MyHttpUils.searchBookByName(str, new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                String result = responseInfo.result.toString();
                List<DouBanBookBean> list = DoubanJsonParser.getSearchBooksInfo(result);
                rv_list.setAdapter(new BaseBookInfoAdapter(SearchResultActivity.this,list,1));
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void initView() {
        rv_list = (RecyclerView) findViewById(R.id.rv_searchresult_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        findViewById(R.id.iv_searchresult_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
