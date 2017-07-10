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
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.ImageUtils;
import com.strawberrysoft.bookdemo.Utils.StringUtils;
import java.util.List;

/**
 * Created by Administrator on 2016/8/21.
 */
public class BaseBookInfoAdapter extends RecyclerView.Adapter<BaseBookInfoAdapter.ViewHolder> {
    private Context context;
    private List<DouBanBookBean> list;
    private LayoutInflater inflater;
    private int type;//为1时，为普通，条数为list的长度，为2时取20条
    public BaseBookInfoAdapter(Context context, List<DouBanBookBean> list,int type) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.type = type;
    }

    @Override
    public BaseBookInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.item_base_bookinfo,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseBookInfoAdapter.ViewHolder holder, final int position) {
        DouBanBookBean bookBean = list.get(position);
        holder.textView1.setText(bookBean.getTitle());
        holder.textView2.setText(StringUtils.listToString(bookBean.getAuthor()));
        holder.textView3.setText(bookBean.getPublisher()+"\\"+bookBean.getPubdate());
        holder.imageView.setTag(position+"");
        ImageUtils.showOnlineImg(list.get(position).getImage(),holder.imageView,position+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QRCodeResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info",list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (type == 1){
            return list.size();
        }else {
            return 20;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.tv_basebookinfo_textview1);
            this.textView2 = (TextView) itemView.findViewById(R.id.tv_basebookinfo_textview2);
            this.textView3 = (TextView) itemView.findViewById(R.id.tv_basebookinfo_textview3);
            this.imageView = (ImageView) itemView.findViewById(R.id.iv_basebookinfo_image);
        }
    }
}
