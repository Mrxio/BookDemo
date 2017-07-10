package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Activity.LibraryActivity;
import com.strawberrysoft.bookdemo.Activity.LibraryBookActivity;
import com.strawberrysoft.bookdemo.Bean.ClassificationInfoBean;
import com.strawberrysoft.bookdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class BookListRightAdpter extends RecyclerView.Adapter<BookListRightAdpter.ViewHolder> {

    private Context context;
    private List<ClassificationInfoBean> list;
    private int p;
    private LayoutInflater inflater;

    public BookListRightAdpter(Context context,List<ClassificationInfoBean> list,int p) {
        this.context = context;
        this.list = list;
        this.p = p;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BookListRightAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_bookinfo_right,parent,false));
    }

    @Override
    public void onBindViewHolder(BookListRightAdpter.ViewHolder holder, final int position) {
        final String type = list.get(p).getList().get(position);
        holder.textView.setText(type);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LibraryBookActivity.class);
                intent.putExtra("type",type);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.get(p).getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_right_text1);
        }
    }
}
