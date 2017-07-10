package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.DBUtils;
import com.strawberrysoft.bookdemo.Utils.FileUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FileUtils.initFolders();
        DBUtils dbUtils = new DBUtils();
        dbUtils.openDatabase(this);
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
