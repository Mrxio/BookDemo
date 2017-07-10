package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.strawberrysoft.bookdemo.Adapter.ChatMsgAdpter;
import com.strawberrysoft.bookdemo.Bean.MessageBean;
import com.strawberrysoft.bookdemo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private TextView tv_title;
    private RecyclerView rv_list;
    private EditText et_edit;
    private Button bt_send;
    private List<MessageBean> list;
    private ChatMsgAdpter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv_title.setText(title);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_chat_toolbar);
        rv_list = (RecyclerView) findViewById(R.id.rv_chat_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        et_edit = (EditText) findViewById(R.id.et_chat_edit);
        bt_send = (Button) findViewById(R.id.bt_chat_send);
        list = new ArrayList<>();
        adapter = new ChatMsgAdpter(this,list);
        rv_list.setAdapter(adapter);
        findViewById(R.id.iv_chat_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et_edit.getText().toString();
                et_edit.setText("");
                if (TextUtils.isEmpty(text)){

                }else {
                    MessageBean bean = new MessageBean(1,getTime(),text);
                    list.add(bean);
                    adapter.notifyDataSetChanged();
                    rv_list.scrollToPosition(list.size()-1);
                    antoReplyMsg();
                }
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MessageBean message = new MessageBean(0,getTime(),"测试");
            list.add(message);
            adapter.notifyDataSetChanged();
            rv_list.scrollToPosition(list.size()-1);
        }
    };
    private void antoReplyMsg() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1*1000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        return time;
    }
}
