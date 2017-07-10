package com.strawberrysoft.bookdemo.Utils;

import com.google.gson.Gson;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/21.
 */
public class DoubanJsonParser {
    public static DouBanBookBean getSingleBookInfo(String result){
        Gson gson = new Gson();
        Map<Object,Object> map = new HashMap<>();
        map = gson.fromJson(result,Map.class);
        return getInfoByMap(map);
    }
    public static List<DouBanBookBean> getSearchBooksInfo(String result){
        List<DouBanBookBean> infolist = new ArrayList<>();
        Gson gson = new Gson();
        Map<Object,Object> map = new HashMap<>();
        map = gson.fromJson(result,Map.class);
        List<Map<Object,Object>> list = (List<Map<Object, Object>>) map.get("books");
        for (Map<Object,Object> item : list){
            infolist.add(getInfoByMap(item));
        }
        return infolist;
    }
    public static DouBanBookBean getInfoByMap(Map<Object,Object> map){
        List<String> author = (ArrayList<String>) map.get("author");
        String pubdate = (String) map.get("pubdate");
        List<Object> tags = (List<Object>) map.get("tags");
        String image = (String) map.get("image");
        String binding = (String) map.get("binding");
        List<String> translator = (List<String>) map.get("translator");
        String catalog = (String) map.get("catalog");
        String pages = (String) map.get("pages");
        String publisher = (String) map.get("publisher");
        String isbn13 = (String) map.get("isbn13");
        String title = (String) map.get("title");
        String author_intro = (String) map.get("author_intro");
        String price = (String) map.get("price");
        DouBanBookBean bookBean = new DouBanBookBean(author,author_intro,binding,catalog,isbn13 ,image,pages,price,pubdate,publisher,tags,title,translator);
        return bookBean;
    }
}
