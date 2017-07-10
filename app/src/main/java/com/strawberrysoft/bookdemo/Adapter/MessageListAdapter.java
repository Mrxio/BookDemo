package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.strawberrysoft.bookdemo.Activity.AddChatActivity;
import com.strawberrysoft.bookdemo.Activity.ChatActivity;
import com.strawberrysoft.bookdemo.Activity.SystemChatActivity;
import com.strawberrysoft.bookdemo.Bean.ConversationBean;
import com.strawberrysoft.bookdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<ConversationBean> list;
    public MessageListAdapter(Context context,List<ConversationBean> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;

    }

    @Override
    public MessageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_conversation,parent,false));
    }

    @Override
    public void onBindViewHolder(MessageListAdapter.ViewHolder holder, int position) {
        final ConversationBean bean = list.get(position);
        final String type = bean.getType();
        holder.textView1.setText(bean.getTitle());
        holder.textView2.setText(bean.getContent());
        holder.textView3.setText(bean.getTime());
        if (type.equals("0")){
            holder.imageView.setImageResource(R.mipmap.ic_xitong);
        }else if (type.equals("1")){
            holder.imageView.setImageResource(R.mipmap.ic_yanzheng);
        }else if (type.equals("2")){
            holder.imageView.setImageResource(R.mipmap.ic_library);
        }else {
            holder.imageView.setImageResource(R.mipmap.head_no);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("0")){
                    Intent intent = new Intent(context, SystemChatActivity.class);
                    context.startActivity(intent);
                }else if (type.equals("1")){
                    Intent intent = new Intent(context, AddChatActivity.class);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("title",bean.getTitle());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_coversation_image);
            textView1 = (TextView) itemView.findViewById(R.id.tv_coversation_text1);
            textView2 = (TextView) itemView.findViewById(R.id.tv_coversation_text2);
            textView3 = (TextView) itemView.findViewById(R.id.tv_coversation_text3);
        }
    }
}
