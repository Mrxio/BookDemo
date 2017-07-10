package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.strawberrysoft.bookdemo.Activity.LibraryListActivity;
import com.strawberrysoft.bookdemo.Activity.QRCodeResultActivity;
import com.strawberrysoft.bookdemo.Activity.SearchResultActivity;
import com.strawberrysoft.bookdemo.Activity.SendBookActivity;
import com.strawberrysoft.bookdemo.Activity.AllOderListActivity;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.ImageUtils;
import com.strawberrysoft.bookdemo.Utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/20.
 */
public class IndexRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private final int ITEM_HEADER = 1;
    private final int ITEM_BODY = 2;
    private List<DouBanBookBean> list;
    public IndexRecyclerAdapter(Context context,List<DouBanBookBean> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER){
            return new HeaderViewHolder(inflater.inflate(R.layout.item_index_recyclerview_head,parent,false));
        }else {
            return new BodyViewHolder(inflater.inflate(R.layout.item_base_bookinfo,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).viewPager.setAdapter(new IndexAdAdapter(context));
            //设置滑动item改变监听
            ((HeaderViewHolder) holder).viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    final int tag = position%3;
                    switch (tag){
                        case 0:
                            ((HeaderViewHolder) holder).dot1.setEnabled(false);
                            ((HeaderViewHolder) holder).dot2.setEnabled(true);
                            ((HeaderViewHolder) holder).dot3.setEnabled(true);
                            break;
                        case 1:
                            ((HeaderViewHolder) holder).dot1.setEnabled(true);
                            ((HeaderViewHolder) holder).dot2.setEnabled(false);
                            ((HeaderViewHolder) holder).dot3.setEnabled(true);
                            break;
                        case 2:
                            ((HeaderViewHolder) holder).dot1.setEnabled(true);
                            ((HeaderViewHolder) holder).dot2.setEnabled(true);
                            ((HeaderViewHolder) holder).dot3.setEnabled(false);
                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            ((HeaderViewHolder) holder).viewPager.setCurrentItem(24);
            //自动切换dot
            setDot(holder);
            //借书按钮
            ((HeaderViewHolder) holder).button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, LibraryListActivity.class);
                    context.startActivity(intent);
                }
            });
            //还书按钮
            ((HeaderViewHolder) holder).button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AllOderListActivity.class);
                    intent.putExtra("type",2);
                    context.startActivity(intent);
                }
            });
            //赠书按钮
            ((HeaderViewHolder) holder).button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show();
                }
            });

        }else if (holder instanceof BodyViewHolder){
            DouBanBookBean bookBean = list.get(position);
            ((BodyViewHolder) holder).textView1.setText(bookBean.getTitle());
            ((BodyViewHolder) holder).textView2.setText(StringUtils.listToString(bookBean.getAuthor()));
            ((BodyViewHolder) holder).textView3.setText(bookBean.getPublisher()+"\\"+bookBean.getPubdate());
            ((BodyViewHolder) holder).imageView.setTag(position+"");
            ImageUtils.showOnlineImg(list.get(position).getImage(),((BodyViewHolder) holder).imageView,position+"");
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
    }

    @Override
    public int getItemCount() {
        return 22;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_HEADER;
        }else {
            return ITEM_BODY;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        View dot1;
        View dot2;
        View dot3;
        Button button1;
        Button button2;
        Button button3;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.viewPager = (ViewPager) itemView.findViewById(R.id.vp_indexfragment_viewpager);
            this.dot1 = itemView.findViewById(R.id.v_indexhead_dot1);
            this.dot2 = itemView.findViewById(R.id.v_indexhead_dot2);
            this.dot3 = itemView.findViewById(R.id.v_indexhead_dot3);
            this.button1 = (Button) itemView.findViewById(R.id.bt_indexhead_button1);
            this.button2 = (Button) itemView.findViewById(R.id.bt_indexhead_button2);
            this.button3 = (Button) itemView.findViewById(R.id.bt_indexhead_button3);
        }
    }
    public class BodyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public BodyViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.tv_basebookinfo_textview1);
            this.textView2 = (TextView) itemView.findViewById(R.id.tv_basebookinfo_textview2);
            this.textView3 = (TextView) itemView.findViewById(R.id.tv_basebookinfo_textview3);
            this.imageView = (ImageView) itemView.findViewById(R.id.iv_basebookinfo_image);
        }
    }
    //定时自动切换dot
    public void setDot(final RecyclerView.ViewHolder holder){

        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            int i = 24;
            @Override
            public void run() {
                i = i + 1;
                if (i == 27){
                    i = 24;
                }
                ((HeaderViewHolder) holder).viewPager.setCurrentItem(i);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable,3000);
    }
}
