package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.R;

/**
 * Created by Administrator on 2016/8/30.
 */
public class FindHorizontalAdapter extends RecyclerView.Adapter<FindHorizontalAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private String[] name = {"小奋斗","深海鱼","贝加尔的蓝","南风未起","时光带不走的信念一如既往","青栞","若琳","失念","无名","小丸子"};
    public FindHorizontalAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_find_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_finditem_name);
        }
    }
}
