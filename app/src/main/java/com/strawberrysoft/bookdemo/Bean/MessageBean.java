package com.strawberrysoft.bookdemo.Bean;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MessageBean {
    private int type;//0为对方，1为自己
    private String sendTime;
    private String content;

    public MessageBean(int type, String sendTime, String content) {
        this.type = type;
        this.sendTime = sendTime;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
