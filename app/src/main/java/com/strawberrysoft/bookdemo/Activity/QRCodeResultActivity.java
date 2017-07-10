package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.strawberrysoft.bookdemo.Adapter.MinuteBookInfoAdapter;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.DoubanJsonParser;
import com.strawberrysoft.bookdemo.Utils.ImageUtils;
import com.strawberrysoft.bookdemo.Utils.MyHttpUils;
import com.strawberrysoft.bookdemo.Utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import co.lujun.androidtagview.TagContainerLayout;

public class QRCodeResultActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView lv_info;
    private List<String> infolist = new ArrayList<>();
    private TextView tv_shoucang;
    private DouBanBookBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_result);
        initView();
        Intent intent = getIntent();
        String ISBN = intent.getStringExtra("ISBN");
        bean = (DouBanBookBean) intent.getSerializableExtra("info");
        if (bean == null){
            MyHttpUils.getDoubanBookInfo(ISBN, new RequestCallBack() {
                @Override
                public void onSuccess(ResponseInfo responseInfo) {
                    String result = responseInfo.result.toString();
                    bean = DoubanJsonParser.getSingleBookInfo(result);
                    showBookInfo(bean);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    System.out.println(s);
                }
            });
        }else {
            showBookInfo(bean);
        }
    }

    private void initView() {
        tv_shoucang = (TextView) findViewById(R.id.tv_qrcoderesult_shoucang);
        tv_shoucang.setOnClickListener(this);
        findViewById(R.id.tv_qrcoderesult_jieyue).setOnClickListener(this);
        lv_info = (RecyclerView) findViewById(R.id.rv_arcoderesultactivity_info);
        lv_info.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showBookInfo(DouBanBookBean bookBean){
        infolist.clear();
        infolist.add("书名："+bookBean.getTitle());
        infolist.add("作者："+ StringUtils.listToString(bookBean.getAuthor()));
        infolist.add("出版社："+ bookBean.getPublisher());
        infolist.add("出版时间："+ bookBean.getPubdate());
        infolist.add("翻译："+ StringUtils.listToString(bookBean.getTranslator()));
        infolist.add("装订："+ bookBean.getBinding());
        infolist.add("价格："+ bookBean.getPrice());
        infolist.add("ISBN："+ bookBean.getIbsn13());
        lv_info.setAdapter(new MinuteBookInfoAdapter(this,infolist,bookBean));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_qrcoderesult_shoucang:
                if (tv_shoucang.getText().equals("收藏")){
                    tv_shoucang.setText("已收藏");
                }else {
                    tv_shoucang.setText("收藏");
                }
                break;
            case R.id.tv_qrcoderesult_jieyue:
                Intent intent = new Intent(this,OderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info",bean);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
