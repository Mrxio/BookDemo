package com.strawberrysoft.bookdemo.Bean;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ShuPingBean {
    String title;
    String content;
    String author;
    String book;
    String img;
    int zan;
    int ping;

    public ShuPingBean(String title, String content, String author, String book, String img, int zan, int ping) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.book = book;
        this.img = img;
        this.zan = zan;
        this.ping = ping;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }
}
