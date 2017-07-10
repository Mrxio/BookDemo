package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.strawberrysoft.bookdemo.Activity.SearchResultActivity;
import com.strawberrysoft.bookdemo.R;

/**
 * Created by Android-J on 2016/8/19.
 */
public class IndexAdAdapter extends PagerAdapter {
    private Context context;
    private int[] imgRes = {R.mipmap.a,R.mipmap.b,R.mipmap.c};
    public IndexAdAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int tag = position%3;
        View view = View.inflate(context,R.layout.item_index_ad,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_indexadadapter_image);
        imageView.setImageResource(imgRes[tag]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchResultActivity.class);
                if (tag == 0){
                    intent.putExtra("str","围城");
                    context.startActivity(intent);
                }else if (tag == 1){
                    intent.putExtra("str","盗墓笔记");
                    context.startActivity(intent);
                }else if (tag == 2){
                    intent.putExtra("str","数学建模");
                    context.startActivity(intent);

                }
            }
        });
        container.addView(view);
        return view;
    }
}
