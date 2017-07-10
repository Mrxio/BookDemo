package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strawberrysoft.bookdemo.Adapter.BookListLeftAdpter;
import com.strawberrysoft.bookdemo.Adapter.BookListRightAdpter;
import com.strawberrysoft.bookdemo.Bean.ClassificationInfo;
import com.strawberrysoft.bookdemo.Bean.ClassificationInfoBean;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.View.RecyclerItemClickListener;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {
    private TextView tv_name;
    private RecyclerView rv_left;
    private RecyclerView rv_right;
    private int p = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        tv_name.setText(name);
    }

    private void initView() {
        ClassificationInfo info = new ClassificationInfo();
        final List<ClassificationInfoBean> list =  info.getClassificationInfo();
        tv_name = (TextView) findViewById(R.id.tv_libraryinfo_name);
        rv_left = (RecyclerView) findViewById(R.id.rv_library_left);
        rv_left.setLayoutManager(new LinearLayoutManager(this));
        rv_left.setAdapter(new BookListLeftAdpter(this,list,p));
        rv_right = (RecyclerView) findViewById(R.id.rv_library_right);
        rv_right.setLayoutManager(new LinearLayoutManager(this));
        rv_right.setAdapter(new BookListRightAdpter(LibraryActivity.this,list,p));
        findViewById(R.id.iv_library_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rv_left.addOnItemTouchListener(new RecyclerItemClickListener(this, rv_left, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                p = position;
                rv_right.setAdapter(new BookListRightAdpter(LibraryActivity.this,list,p));
                rv_left.setAdapter(new BookListLeftAdpter(LibraryActivity.this,list,p));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
