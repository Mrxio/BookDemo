package com.strawberrysoft.bookdemo.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ClassificationInfoBean {
    private String name;
    private List<String> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public ClassificationInfoBean(String name, List<String> list) {

        this.name = name;
        this.list = list;
    }
}
