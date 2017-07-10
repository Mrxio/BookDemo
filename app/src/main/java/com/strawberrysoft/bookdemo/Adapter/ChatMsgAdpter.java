package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.strawberrysoft.bookdemo.Bean.MessageBean;
import com.strawberrysoft.bookdemo.R;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class ChatMsgAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int ITEM_LEFT = 1;
    private int ITEM_RIGHT = 2;
    private Context context;
    private List<MessageBean> list;
    private LayoutInflater inflater;
    public ChatMsgAdpter(Context context,List<MessageBean> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_LEFT){
            return new LeftHolder(inflater.inflate(R.layout.item_chat_left,parent,false));
        }else {
            return new RightHolder(inflater.inflate(R.layout.item_chat_right,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageBean msg = list.get(position);
        if (holder instanceof LeftHolder){
            ((LeftHolder) holder).tv_time.setText(msg.getSendTime());
            ((LeftHolder) holder).tv_content.setText(msg.getContent());
        }else if (holder instanceof  RightHolder){
            ((RightHolder) holder).tv_time.setText(msg.getSendTime());
            ((RightHolder) holder).tv_content.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType()==0){
            return ITEM_LEFT;
        }else {
            return ITEM_RIGHT;
        }
    }

    public class LeftHolder extends RecyclerView.ViewHolder{
        ImageView iv_head;
        TextView tv_time;
        TextView tv_content;
        public LeftHolder(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_chat_head);
            tv_time = (TextView) itemView.findViewById(R.id.tv_chat_time);
            tv_content = (TextView) itemView.findViewById(R.id.tv_chat_content);
        }
    }
    public class RightHolder extends RecyclerView.ViewHolder{
        ImageView iv_head;
        TextView tv_time;
        TextView tv_content;
        public RightHolder(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_chat_headr);
            tv_time = (TextView) itemView.findViewById(R.id.tv_chat_timer);
            tv_content = (TextView) itemView.findViewById(R.id.tv_chat_contentr);
        }
    }
}
