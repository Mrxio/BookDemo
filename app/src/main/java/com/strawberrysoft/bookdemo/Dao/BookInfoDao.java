package com.strawberrysoft.bookdemo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class BookInfoDao {
    private MyOpenHelper openHelper;
    private SQLiteDatabase db;
    public BookInfoDao(Context context) {
        this.openHelper = MyOpenHelper.getDBHelper(context,1);
        this.db = openHelper.getWritableDatabase();
    }

    synchronized public boolean insertBook(String type1,String type2 ,DouBanBookBean bean) {
        String isbn = bean.getIbsn13();
        if (TextUtils.isEmpty(isbn)){
            return false;
        }
        if (isExist(isbn)){
            delectByIsbn(isbn);
        }
        ContentValues value = new ContentValues();
        Gson gson = new Gson();
        String author = gson.toJson(bean.getAuthor());
        String tags = gson.toJson(bean.getTags());
        String translator = gson.toJson(bean.getTranslator());
        value.putNull("_id");
        value.put("type1", type1);
        value.put("type2", type2);
        value.put("author", author);
        value.put("author_intro", bean.getAuthor_intro());
        value.put("binding", bean.getBinding());
        value.put("catalog", bean.getCatalog());
        value.put("isbn13", bean.getIbsn13());
        value.put("image", bean.getImage());
        value.put("pages", bean.getPages());
        value.put("price", bean.getPrice());
        value.put("pubdate", bean.getPubdate());
        value.put("publisher", bean.getPublisher());
        value.put("tags", tags);
        value.put("title", bean.getTitle());
        value.put("translator", translator);
        long id = db.insert("bookinfo", null, value);
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    synchronized public boolean delectByIsbn(String isbn13) {
        int deleteIs = db.delete("bookinfo", "isbn13=?", new String[]{isbn13 + ""});
        if (deleteIs <= 0) {
            return false;
        } else {
            return false;

        }
    }

    synchronized public List<DouBanBookBean> queryBook() {
        List<DouBanBookBean> list = new ArrayList<DouBanBookBean>();
        Cursor cursor = db.rawQuery("select * from bookinfo", null);
        Gson gson = new Gson();
        while (cursor.moveToNext()) {
            List<String> author = gson.fromJson(cursor.getString(cursor.getColumnIndex("author")),List.class);
            List<Object> tags = gson.fromJson(cursor.getString(cursor.getColumnIndex("tags")),List.class);
            List<String> translator = gson.fromJson(cursor.getString(cursor.getColumnIndex("translator")),List.class);
            String author_intro = cursor.getString(cursor.getColumnIndex("author_intro"));
            String binding = cursor.getString(cursor.getColumnIndex("binding"));
            String catalog = cursor.getString(cursor.getColumnIndex("catalog"));
            String isbn13 = cursor.getString(cursor.getColumnIndex("isbn13"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            String pages = cursor.getString(cursor.getColumnIndex("pages"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String pubdate = cursor.getString(cursor.getColumnIndex("pubdate"));
            String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            DouBanBookBean bean = new DouBanBookBean(author,author_intro,binding,catalog,isbn13,image,pages,price,pubdate,publisher,tags,title,translator);
            list.add(bean);
        }
        cursor.close();
        return list;
    }
    synchronized public List<DouBanBookBean> queryBookByType(String type1,String type2) {
        List<DouBanBookBean> list = new ArrayList<DouBanBookBean>();
        Cursor cursor = db.rawQuery("select * from bookinfo where type1? and type2?", new String[]{type1+"",type2+""});
        Gson gson = new Gson();
        while (cursor.moveToNext()) {
            List<String> author = gson.fromJson(cursor.getString(cursor.getColumnIndex("author")),List.class);
            List<Object> tags = gson.fromJson(cursor.getString(cursor.getColumnIndex("tags")),List.class);
            List<String> translator = gson.fromJson(cursor.getString(cursor.getColumnIndex("translator")),List.class);
            String author_intro = cursor.getString(cursor.getColumnIndex("author_intro"));
            String binding = cursor.getString(cursor.getColumnIndex("binding"));
            String catalog = cursor.getString(cursor.getColumnIndex("catalog"));
            String isbn13 = cursor.getString(cursor.getColumnIndex("isbn13"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            String pages = cursor.getString(cursor.getColumnIndex("pages"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String pubdate = cursor.getString(cursor.getColumnIndex("str"));
            String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            DouBanBookBean bean = new DouBanBookBean(author,author_intro,binding,catalog,isbn13,image,pages,price,pubdate,publisher,tags,title,translator);
            list.add(bean);
        }
        cursor.close();
        return list;
    }
    synchronized public DouBanBookBean queryBookByISBN(String ISBN) {
        DouBanBookBean bean = null;
        Cursor cursor = db.rawQuery("select * from bookinfo where isbn13=?", new String[]{ISBN+""});
        Gson gson = new Gson();
        while (cursor.moveToNext()) {
            List<String> author = gson.fromJson(cursor.getString(cursor.getColumnIndex("author")),List.class);
            List<Object> tags = gson.fromJson(cursor.getString(cursor.getColumnIndex("tags")),List.class);
            List<String> translator = gson.fromJson(cursor.getString(cursor.getColumnIndex("translator")),List.class);
            String author_intro = cursor.getString(cursor.getColumnIndex("author_intro"));
            String binding = cursor.getString(cursor.getColumnIndex("binding"));
            String catalog = cursor.getString(cursor.getColumnIndex("catalog"));
            String isbn13 = cursor.getString(cursor.getColumnIndex("isbn13"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            String pages = cursor.getString(cursor.getColumnIndex("pages"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String pubdate = cursor.getString(cursor.getColumnIndex("pubdate"));
            String publisher = cursor.getString(cursor.getColumnIndex("publisher"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            bean = new DouBanBookBean(author,author_intro,binding,catalog,isbn13,image,pages,price,pubdate,publisher,tags,title,translator);
        }
        cursor.close();
        return bean;
    }

    synchronized public boolean isExist(String isbn) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from bookinfo where isbn13=?", new String[] { isbn+ "" });
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("isbn13")));
        }
        cursor.close();
        if (list.size()>0){
            return true;
        }else {
            return false;
        }
    }

    public void clearBook(){
        String sql = "DELETE FROM bookinfo;";
        db.execSQL(sql);
    }
}
