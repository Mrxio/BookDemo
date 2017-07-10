package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.ImageUtils;
import com.strawberrysoft.bookdemo.Utils.StringUtils;

public class OderActivity extends AppCompatActivity implements View.OnClickListener{
    private DouBanBookBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        Intent intent = getIntent();
        bean = (DouBanBookBean) intent.getSerializableExtra("info");
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_oder_submit).setOnClickListener(this);
        findViewById(R.id.iv_oder_back).setOnClickListener(this);
        ImageView iv_book = (ImageView) findViewById(R.id.iv_oder_img);
        TextView tv_title = (TextView) findViewById(R.id.tv_oder_title);
        TextView tv_pubinfo = (TextView) findViewById(R.id.tv_oder_pubinfo);
        TextView tv_author = (TextView) findViewById(R.id.tv_oder_author);
        tv_title.setText(bean.getTitle());
        tv_author.setText(StringUtils.listToString(bean.getAuthor()));
        tv_pubinfo.setText(bean.getPublisher()+"\\"+bean.getPubdate());
        iv_book.setTag(1+"");
        ImageUtils.showOnlineImg(bean.getImage(),iv_book,1+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_oder_submit:
                Toast.makeText(OderActivity.this, "提交成功，请等待审核", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.iv_oder_back:
                finish();
                break;
        }
    }
}
