package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Bean.ClassificationInfoBean;
import com.strawberrysoft.bookdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class BookListLeftAdpter extends RecyclerView.Adapter<BookListLeftAdpter.ViewHolder> {
    private Context context;
    private List<ClassificationInfoBean> list;
    private LayoutInflater inflater;
    private int p;

    public BookListLeftAdpter(Context context,List<ClassificationInfoBean> list,int p) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.p = p;
    }

    @Override
    public BookListLeftAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_bookinfo_left,parent,false));
    }

    @Override
    public void onBindViewHolder(final BookListLeftAdpter.ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        if (position == p){
            holder.textView.setEnabled(false);
        }else {
            holder.textView.setEnabled(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_left_text1);
        }
    }
}
