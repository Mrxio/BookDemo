package com.strawberrysoft.bookdemo.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android-J on 2016/8/14.
 */
public class DouBanBookBean implements Serializable {
    private List<String> author;//作者名
    private String pubdate;//出版日期
    private List<Object> tags;//标签
    private String image;//图片地址
    private String binding;//装订
    private List<String> translator;//翻译人员
    private String catalog;//目录
    private String pages;//页数
    private String publisher;//出版社
    private String isbn13;//IBSN13
    private String title;//标题
    private String author_intro;//作者介绍
    private String price;//价格

    public DouBanBookBean(List<String> author, String author_intro, String binding, String catalog, String isbn13, String image, String pages, String price, String pubdate, String publisher, List<Object> tags, String title, List<String> translator) {
        this.author = author;
        this.author_intro = author_intro;
        this.binding = binding;
        this.catalog = catalog;
        this.isbn13 = isbn13;
        this.image = image;
        this.pages = pages;
        this.price = price;
        this.pubdate = pubdate;
        this.publisher = publisher;
        this.tags = tags;
        this.title = title;
        this.translator = translator;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getIbsn13() {
        return isbn13;
    }

    public void setIbsn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }
}
