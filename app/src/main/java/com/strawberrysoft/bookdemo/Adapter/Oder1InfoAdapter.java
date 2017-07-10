package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Activity.QRCodeResultActivity;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.Dao.BookInfoDao;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.ImageUtils;
import com.strawberrysoft.bookdemo.Utils.StringUtils;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Oder1InfoAdapter extends RecyclerView.Adapter<Oder1InfoAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private int type;
    private String[] ISBN;
    private BookInfoDao dao;
    public Oder1InfoAdapter(Context context,int type,String[] ISBN) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.type = type;
        this.ISBN = ISBN;
        this.dao = new BookInfoDao(context);
    }

    @Override
    public Oder1InfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_oder,parent,false));
    }

    @Override
    public void onBindViewHolder(Oder1InfoAdapter.ViewHolder holder, int position) {
        final DouBanBookBean bookBean = dao.queryBookByISBN(ISBN[position]);
        if (type==0){
            holder.tv_type.setText("审核中");
        }else if (type == 1){
            holder.tv_type.setText("待自取");
        }else if (type == 2){
            holder.tv_type.setText("剩"+(position+2)+"天");
        }else if (type == 3){
            holder.tv_type.setText("已完成");
        }
        String title = bookBean.getTitle()+"";
        holder.tv_title.setText(title);
        holder.tv_author.setText(StringUtils.listToString(bookBean.getAuthor()));
        holder.tv_pub.setText(bookBean.getPublisher()+"\\"+bookBean.getPubdate());
        holder.iv_book.setTag(position+"");
        holder.tv_num.setText("160827"+bookBean.getIbsn13());
        ImageUtils.showOnlineImg(bookBean.getImage(),holder.iv_book,position+"");
    }

    @Override
    public int getItemCount() {
        return ISBN.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_type;
        ImageView iv_book;
        TextView tv_num;
        TextView tv_title;
        TextView tv_pub;
        TextView tv_author;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_type = (TextView) itemView.findViewById(R.id.tv_oder_type);
            iv_book = (ImageView) itemView.findViewById(R.id.iv_oderitem_img);
            tv_num = (TextView) itemView.findViewById(R.id.tv_oder_num);
            tv_title = (TextView) itemView.findViewById(R.id.tv_oderitem_title);
            tv_pub = (TextView) itemView.findViewById(R.id.tv_oder_pubinfo);
            tv_author = (TextView) itemView.findViewById(R.id.tv_oderitem_author);
        }
    }
}

