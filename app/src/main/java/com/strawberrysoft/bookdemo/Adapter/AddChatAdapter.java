package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strawberrysoft.bookdemo.R;

/**
 * Created by Administrator on 2016/8/27.
 */
public class AddChatAdapter extends RecyclerView.Adapter<AddChatAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    public AddChatAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public AddChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_addchat,parent,false));
    }

    @Override
    public void onBindViewHolder(AddChatAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
