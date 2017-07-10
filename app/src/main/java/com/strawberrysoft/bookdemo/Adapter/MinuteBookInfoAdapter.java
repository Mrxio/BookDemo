package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.strawberrysoft.bookdemo.Bean.DouBanBookBean;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.Utils.ImageUtils;
import com.strawberrysoft.bookdemo.Utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.lujun.androidtagview.TagContainerLayout;

/**
 * Created by Android-J on 2016/8/15.
 */
public class MinuteBookInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> list;
    private LayoutInflater inflater;
    private final int ITEM_HEADER = 1;
    private final int ITEM_BODY = 2;
    private final int ITEM_FOOTER = 3;
    private DouBanBookBean bookBean;
    private Context context;
    private List<String> tags;
    public MinuteBookInfoAdapter(Context context, List<String> list,DouBanBookBean bookBean){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.bookBean = bookBean;
        this.context = context;
        this.tags = getTagList(bookBean);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER){
            return new HeaderViewHolder(inflater.inflate(R.layout.item_qrcoderesult_header,parent,false));
        }else if (viewType == ITEM_BODY){
            return new BodyViewHolder(inflater.inflate(R.layout.item_simple_bookinfo,parent,false));
        }else {
            return new FooterViewHolder(inflater.inflate(R.layout.item_qrcoderesult_footer,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).tv_title.setText(bookBean.getTitle());
            ((HeaderViewHolder) holder).tv_publisher.setText(bookBean.getPublisher()+"\\"+bookBean.getPubdate());
            ((HeaderViewHolder) holder).tv_author.setText(StringUtils.listToString(bookBean.getAuthor()));
            ((HeaderViewHolder) holder).tv_price.setText("市场价:" + bookBean.getPrice());

            ((HeaderViewHolder) holder).tcl_tags.setTags(tags);
            ImageUtils.downAndBlurImg(bookBean.getImage(), ((HeaderViewHolder) holder).iv_bookimage, ((HeaderViewHolder) holder).iv_background, context);
        }else if (holder instanceof BodyViewHolder){
            ((BodyViewHolder) holder).textView.setText(list.get(position-1));
        }else if (holder instanceof FooterViewHolder){
            ((FooterViewHolder) holder).tv_catalog.setText(bookBean.getCatalog());
            ((FooterViewHolder) holder).tv_author_intro.setText(bookBean.getAuthor_intro());
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ITEM_HEADER;
        }else if (position ==list.size()+1){
            return ITEM_FOOTER;
        }else {
            return ITEM_BODY;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_background;
        ImageView iv_bookimage;
        TextView tv_title;
        TextView tv_price;
        TextView tv_author;
        TextView tv_publisher;
        TagContainerLayout tcl_tags;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            iv_background = (ImageView) itemView.findViewById(R.id.iv_qrcoderesult_background);
            iv_bookimage = (ImageView) itemView.findViewById(R.id.iv_qrcoderesult_bookimage);
            tv_title = (TextView) itemView.findViewById(R.id.tv_qrcoderesult_title);
            tv_price = (TextView) itemView.findViewById(R.id.tv_qrcoderesult_price);
            tv_author = (TextView) itemView.findViewById(R.id.tv_qrcoderesult_author);
            tv_publisher = (TextView) itemView.findViewById(R.id.tv_qrcoderesult_publisher);
            tcl_tags = (TagContainerLayout) itemView.findViewById(R.id.tl_qrcoderesult_tags);
        }
    }
    public class BodyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public BodyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_simplelist_textview);
        }
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tv_catalog;
        TextView tv_author_intro;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tv_catalog = (TextView) itemView.findViewById(R.id.tv_qrcoderesult_catalog);
            tv_author_intro = (TextView) itemView.findViewById(R.id.tv_qrcoderesult_author_intro);
        }
    }
    public List<String> getTagList(DouBanBookBean bean){
        List<Object> tagsobject = bean.getTags();
        List<String> taglist = new ArrayList<>();
        for (Object tagobject:tagsobject){
            Map<String,String> map = new HashMap<>();
            Gson gson = new Gson();
            map = gson.fromJson(tagobject.toString(),Map.class);
            String tagstr = map.get("title");
            taglist.add(tagstr);
        }
        return taglist;
    }
}
