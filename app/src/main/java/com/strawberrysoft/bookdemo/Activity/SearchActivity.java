package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.strawberrysoft.bookdemo.Dao.SearchHistoryDao;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.JsonParser;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;


public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private Button button;
    private SpeechRecognizer mIat;
    private EditText mResultText;
    private RelativeLayout rl_yuyin;
    private Button bt_close;
    private Animation expandanim;
    private Animation shrinkanim;
    private EditText et_search;
    private TagContainerLayout tcl_history;
    private SearchHistoryDao historyDao;
    private List<String> historylist;
    private TextView tv_delect;
    private RelativeLayout toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        historyDao = new SearchHistoryDao(this);
        historylist = new ArrayList<>();
        historylist = historyDao.queryHistory();
        randomWelcome();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3.开始听写
                mIat.startListening(mRecoListener);
            }
        });
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setVisibility(View.VISIBLE);
                rl_yuyin.setVisibility(View.GONE);
            }
        });
        if (!historylist.isEmpty()) {
            tcl_history.setTags(historylist);
            tv_delect.setVisibility(View.VISIBLE);
        }else {
            tv_delect.setVisibility(View.GONE);
        }
    }
    //初始化
    public void initView(){
        setContentView(R.layout.activity_search);
        //初始化服务
        SpeechUtility.createUtility(SearchActivity.this, SpeechConstant.APPID + "=57107d1b");
        button = (Button) findViewById(R.id.button);
        toolbar = (RelativeLayout) findViewById(R.id.rl_search);
        findViewById(R.id.iv_search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.iv_search_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置语音搜索功能显示
                toolbar.setAnimation(shrinkanim);
                toolbar.setVisibility(View.GONE);
                rl_yuyin.setVisibility(View.VISIBLE);
                button.setAnimation(expandanim);
            }
        });
        rl_yuyin = (RelativeLayout) findViewById(R.id.rl_searchactivity_yuyin);
        bt_close = (Button) findViewById(R.id.bt_searchactivity_close);
        expandanim = AnimationUtils.loadAnimation(SearchActivity.this, R.anim.expandanim);
        shrinkanim = AnimationUtils.loadAnimation(SearchActivity.this, R.anim.shrinkanim);
        et_search = (EditText) findViewById(R.id.et_toorbal_search);
        et_search.setOnEditorActionListener(this);
        mResultText = (EditText) findViewById(R.id.et_result);
        //1.创建SpeechRecognizer对象，第二个参数：本地识别时传InitListener
        mIat= SpeechRecognizer.createRecognizer(SearchActivity.this, null);
        //2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        tcl_history = (TagContainerLayout) findViewById(R.id.tcl_searchactivity_tags);

        tcl_history.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                intent.putExtra("str",text);
                startActivity(intent);
            }

            @Override
            public void onTagLongClick(int position, String text) {
                tcl_history.removeTag(position);
            }
        });
        tv_delect = (TextView) findViewById(R.id.tv_search_delect);
        tv_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyDao.clearHistory();
                historylist.clear();
                tcl_history.removeAllTags();
            }
        });
    }

    public void randomWelcome(){
        String[] welcome = {"你想了解什么书呢？","说出书名","我本身就是一座图书馆","你想要的也许我都能找到哦~","书中自有黄金屋~"};
        int index = (int)(Math.random()*5);
        mResultText.setText(welcome[index]);
    }
    String str = "";
    //听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener(){
        //听写结果回调接口(返回Json格式结果，用户可参见附录13.1)；
    //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
    //isLast等于true时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            System.out.println("返回结果"+results.getResultString());
            String text = JsonParser.parseIatResult(results.getResultString());
            mResultText.append(text);
            TextPaint tp = mResultText.getPaint();
            tp.setFakeBoldText(true);
            mResultText.setSelection(mResultText.length());
            str = str + text;
            if (isLast){
                str = str.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
                if (!TextUtils.isEmpty(str)){
                    Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                    intent.putExtra("str",str);
                    startActivity(intent);
                    historyDao.insertHistory(str);
                    tcl_history.addTag(str);
                }
                str = "";
            }
        }
        //会话发生错误回调接口
        public void onError(SpeechError error) {
        //打印错误码描述
            mResultText.setText(error.getPlainDescription(true));
            Animation anim = AnimationUtils.loadAnimation(SearchActivity.this, R.anim.shakeanim);
            mResultText.startAnimation(anim);
        }
        //开始录音
        public void onBeginOfSpeech() {
            mResultText.getText().clear();
            button.setBackgroundResource(R.mipmap.ic_microphone);
        }
        //volume音量值0~30，data音频数据
        public void onVolumeChanged(int volume, byte[] data){
            int[] sre = {R.mipmap.ic_microphone1,R.mipmap.ic_microphone2,R.mipmap.ic_microphone3,R.mipmap.ic_microphone4,R.mipmap.ic_microphone5,R.mipmap.ic_microphone6,
                    R.mipmap.ic_microphone7,R.mipmap.ic_microphone8,R.mipmap.ic_microphone9,R.mipmap.ic_microphone10};
            int i = volume/3;
            button.setBackgroundResource(sre[i]);
        }
        //结束录音
        public void onEndOfSpeech() {
            button.setBackgroundResource(R.mipmap.ic_microphone_outline);
        }
        //扩展用接口
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {}
    };

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH){
            String str = et_search.getText().toString();
            str = str.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
            if (!TextUtils.isEmpty(str)){
                Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                intent.putExtra("str",str);
                startActivity(intent);
                historyDao.insertHistory(str);
                tcl_history.addTag(str);
            }else {
                Toast.makeText(SearchActivity.this, "请输入要搜索的书名", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}
