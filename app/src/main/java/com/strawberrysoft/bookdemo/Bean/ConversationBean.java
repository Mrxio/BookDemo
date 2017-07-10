package com.strawberrysoft.bookdemo.Bean;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ConversationBean {
    private String type;
    private String conversationId;
    private String title;
    private String senderId;
    private String content;
    private String time;

    public ConversationBean(String type, String conversationId, String title, String senderId, String content, String time) {
        this.type = type;
        this.conversationId = conversationId;
        this.title = title;
        this.senderId = senderId;
        this.content = content;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
