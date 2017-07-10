package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Bean.ShuPingBean;
import com.strawberrysoft.bookdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private final int ITEM_HEAD = 1;
    private final int ITEM_BODY = 2;
    private final int ITEM_FOOT = 3;
    private List<ShuPingBean> list;

    public FindAdapter(Context context, List<ShuPingBean> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEAD){
            return new HeadAdapter(inflater.inflate(R.layout.item_find_head,parent,false));
        }else if (viewType == ITEM_BODY){
            return new BodyAdapter(inflater.inflate(R.layout.item_find_body,parent,false));
        }else{
            return new FootAdapter(inflater.inflate(R.layout.item_find_foot,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadAdapter){

        }else if (holder instanceof BodyAdapter){
            ((BodyAdapter) holder).rv_list.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL,false));
            ((BodyAdapter) holder).rv_list.setAdapter(new FindHorizontalAdapter(context));
        }else if (holder instanceof FootAdapter){
            ShuPingBean bean = list.get(position-2);
            ((FootAdapter) holder).text1.setText(bean.getTitle());
            ((FootAdapter) holder).text2.setText(bean.getContent());
            ((FootAdapter) holder).tv_ping.setText(bean.getPing()+"");
            ((FootAdapter) holder).tv_zan.setText(bean.getZan()+"");
            ((FootAdapter) holder).tv_book.setText(bean.getBook()+"");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_HEAD;
        }else if (position == 1){
            return ITEM_BODY;
        }else {
            return ITEM_FOOT;
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+2;
    }

    public class HeadAdapter extends RecyclerView.ViewHolder {
        public HeadAdapter(View itemView) {
            super(itemView);
        }
    }
    public class BodyAdapter extends RecyclerView.ViewHolder {
        RecyclerView rv_list;
        public BodyAdapter(View itemView) {
            super(itemView);
            rv_list = (RecyclerView) itemView.findViewById(R.id.rv_findbody_list);
        }
    }
    public class FootAdapter extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;
        TextView tv_ping;
        TextView tv_zan;
        TextView tv_book;
        public FootAdapter(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.tv_findfoot_text);
            text2 = (TextView) itemView.findViewById(R.id.tv_findfoot_text2);
            tv_ping = (TextView) itemView.findViewById(R.id.tv_findfoot_ping);
            tv_zan = (TextView) itemView.findViewById(R.id.tv_findfoot_zan);
            tv_book = (TextView) itemView.findViewById(R.id.tv_findfoot_book);
        }
    }
}
