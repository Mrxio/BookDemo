package com.strawberrysoft.bookdemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.strawberrysoft.bookdemo.Adapter.AddChatAdapter;
import com.strawberrysoft.bookdemo.R;

public class AddChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);
        findViewById(R.id.iv_addchat_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RecyclerView rv_list = (RecyclerView) findViewById(R.id.rv_addchat_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(new AddChatAdapter(this));
    }
}
