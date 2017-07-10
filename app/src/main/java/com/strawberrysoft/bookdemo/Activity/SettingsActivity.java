package com.strawberrysoft.bookdemo.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.strawberrysoft.bookdemo.R;


public class SettingsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.iv_setting_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
