package com.strawberrysoft.bookdemo.Utils;

import java.util.List;

/**
 * Created by Android-J on 2016/8/15.
 */
public class StringUtils {
    public static String listToString(List<String> list){
        String str = "";
        for (int i = 0 ; i < list.size() ; i ++){
            if (i==0||i==list.size()-1){
                str = str + list.get(i);
            }else {
                str = str + list.get(i)+"、";
            }
        }
        return str;
    }
}
