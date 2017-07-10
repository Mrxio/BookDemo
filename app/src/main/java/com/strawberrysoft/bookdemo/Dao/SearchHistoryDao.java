package com.strawberrysoft.bookdemo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SearchHistoryDao {
    private MyOpenHelper openHelper;
    private SQLiteDatabase db;
    public SearchHistoryDao(Context context) {
        this.openHelper = MyOpenHelper.getDBHelper(context,1);
        this.db = openHelper.getWritableDatabase();
    }

    synchronized public boolean insertHistory(String str) {
        if (TextUtils.isEmpty(str)){
            return false;
        }
        if (isHistoryExist(str)){
            deleteHistoryByStr(str);
        }
        ContentValues value = new ContentValues();
        value.putNull("_id");
        value.put("str", str);
        long id = db.insert("search_history", null, value);
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    synchronized public boolean deleteHistoryByStr(String str) {
        int deleteIs = db.delete("search_history", "str=?", new String[]{str + ""});
        if (deleteIs <= 0) {
            return false;
        } else {
            return false;

        }
    }

    synchronized public List<String> queryHistory() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from search_history", null);
        while (cursor.moveToNext()) {
            String str = cursor.getString(cursor.getColumnIndex("str"));
            list.add(str);
        }
        cursor.close();
        return list;
    }

    synchronized public boolean isHistoryExist(String str) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from search_history where str=?", new String[] { str + "" });
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("str")));
        }
        cursor.close();
        if (list.size()>0){
            return true;
        }else {
            return false;
        }
    }

    public void clearHistory(){
        String sql = "DELETE FROM search_history;";
        db.execSQL(sql);
    }

}
