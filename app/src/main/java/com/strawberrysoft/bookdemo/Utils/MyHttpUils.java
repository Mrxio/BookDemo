package com.strawberrysoft.bookdemo.Utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * Created by Android-J on 2016/8/14.
 */
public class MyHttpUils {
    public static void getDoubanBookInfo(String ISBN,RequestCallBack callBack){
        HttpUtils httpUtils = init();
        httpUtils.send(HttpRequest.HttpMethod.GET,"https://api.douban.com/v2/book/isbn/:"+ISBN,callBack);
    }
    public static void searchBookByName(String name , RequestCallBack callBack){
        HttpUtils httpUtils = init();
        httpUtils.send(HttpRequest.HttpMethod.GET,"https://api.douban.com/v2/book/search?q="+name,callBack);
    }
    public static HttpUtils init(){
        HttpUtils httpUtils = new HttpUtils(30*1000);
        return httpUtils;
    }
}
