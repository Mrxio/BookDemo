package com.strawberrysoft.bookdemo.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ConversationInfo {
    public List<ConversationBean> getCoversationList(){
        String[] title = {"验证消息","通知信息","辽宁工程技术大学图书馆","一季花开、一季花落","南故笙烟","半夏半暖半倾城","萌小呆-.-","大连理工大学图书馆","一指流砂","愿时光待我如初","秋水墨凉","被偏爱的都有恃无恐"};
        String[] content = {"小奋斗想申请您为好友","您在辽宁工程技术大学图书馆借阅的图书已通过审核","好的同学，你明天过来吧","你好","同学在吗？我们这边有个读书会","你好，书还了没？","明天你过来吧，我现在还有点事","已经通过审核了哦","你今天还去图书馆不？","你那本书叫什么名字？","你好，在么？","好的你忙吧"};
        String[] time = {"昨天23:30","前天12:39","周三8:30","周日20:01","周日22:47","8月20日","8月16日","8月7日","8月4日","7月31日","7月23日","7月14日"};
        String[] type = {"1","0","2","3","3","3","3","2","3","3","3","3"};

        List<ConversationBean> list = new ArrayList<>();
        for (int i = 0; i < 12 ; i++) {
            ConversationBean bean = new ConversationBean(type[i],"",title[i],"",content[i],time[i]);
            list.add(bean);
        }
        return list;
    }
}
