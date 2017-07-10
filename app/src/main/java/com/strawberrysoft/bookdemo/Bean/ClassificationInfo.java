package com.strawberrysoft.bookdemo.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ClassificationInfo {
    String[] class1_1;
    String[] class1_2;
    String[] class1_3;
    String[] class1_4;
    String[] class1_5;
    String[] class1_6;
    String[] class1_7;
    public List<ClassificationInfoBean> getClassificationInfo(){
        String[] class1 = {"文艺","人文","经管","励志","教育","科技","生活"};
        class1_1 = new String[]{"小说", "文字", "传记", "青春文学", "动漫/幽默", "艺术", "摄影", "偶像明星", "涂色填色"};
        class1_2 = new String[]{"哲学宗教", "历史", "政治军事", "文化", "社会科学", "心理学", "古籍", "法律", "经济"};
        class1_3 = new String[]{"经济", "管理", "投资理财", "股票", "金融", "市场/销售", "会计", "互联网"};
        class1_4 = new String[]{"励志/成功", "心灵修养", "职场"};
        class1_5 = new String[]{"中小学教辅", "考试", "大中专教材", "外语", "工具书", "教师用书", "新概念", "英语四/六级", "考研", "公务员"};
        class1_6 = new String[]{"科普读物", "计算机/网络", "医学", "工业技术", "建筑", "自然科学", "农业/林业"};
        class1_7 = new String[]{"两性", "孕期", "育儿", "亲子家教", "保健", "运动", "休闲", "旅游", "美食", "美妆", "手工DIY", "家庭家居", "风水占卜"};
        List<ClassificationInfoBean> infolist = new ArrayList<>();
        for (int i = 0; i < class1.length ; i ++){
            List<String> list = new ArrayList<>();
            String[] arr = getArr(i);
            for (int j = 0; j < arr.length; j++) {
                list.add(arr[j]);
            }
            ClassificationInfoBean bean = new ClassificationInfoBean(class1[i],list);
            infolist.add(bean);
        }
        return infolist;
    }

    public String[] getArr(int i) {
        if (i==0){
            return class1_1;
        }else if (i==1){
            return class1_2;
        }else if (i==2){
            return class1_3;
        }else if (i==3){
            return class1_4;
        }else if (i==4){
            return class1_5;
        }else if (i==5){
            return class1_6;
        }else{
            return class1_7;
        }
    }
}
