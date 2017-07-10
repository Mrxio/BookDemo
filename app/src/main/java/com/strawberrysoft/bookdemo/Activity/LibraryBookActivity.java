package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Adapter.BaseBookInfoAdapter;
import com.strawberrysoft.bookdemo.Bean.ClassificationInfo;
import com.strawberrysoft.bookdemo.Bean.ClassificationInfoBean;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.Dao.BookInfoDao;
import com.strawberrysoft.bookdemo.R;

import java.util.Collections;
import java.util.List;

public class LibraryBookActivity extends AppCompatActivity {
    private TextView tv_title;
    private RecyclerView rv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_book);
        BookInfoDao dao = new BookInfoDao(this);
        List<DouBanBookBean> list = dao.queryBook();
        Collections.shuffle(list);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        initView();
        tv_title.setText(type);
        rv_list.setAdapter(new BaseBookInfoAdapter(this,list,2));
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_librarybook_title);
        findViewById(R.id.iv_librarybook_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rv_list = (RecyclerView) findViewById(R.id.rv_librarybook_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
    }

}
