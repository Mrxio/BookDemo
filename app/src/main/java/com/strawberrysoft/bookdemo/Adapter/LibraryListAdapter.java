package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.strawberrysoft.bookdemo.Activity.LibraryActivity;
import com.strawberrysoft.bookdemo.R;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.ViewHolder> {

    private Context context;
    private List<PoiItem> list;
    private LayoutInflater inflater;
    private  LatLng latLng;

    public LibraryListAdapter(Context context, List<PoiItem> list, LatLng latLng) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.latLng = latLng;
    }

    @Override
    public LibraryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_labrary_info,parent,false));
    }

    @Override
    public void onBindViewHolder(LibraryListAdapter.ViewHolder holder, final int position) {
        final PoiItem poi = list.get(position);
        LatLonPoint latLonPoint = poi.getLatLonPoint();
        float distance = AMapUtils.calculateLineDistance(latLng,new LatLng(latLonPoint.getLatitude(),latLonPoint.getLongitude()));
        holder.textView1.setText(poi.getTitle()+"["+getStrDistance(distance)+"]");
        holder.textView2.setText(poi.getSnippet()+"");
        holder.textView3.setText(poi.getTel()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LibraryActivity.class);
                intent.putExtra("name",poi.getTitle()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.tv_labraryinfo_text1);
            textView2 = (TextView) itemView.findViewById(R.id.tv_labraryinfo_text2);
            textView3 = (TextView) itemView.findViewById(R.id.tv_labraryinfo_text3);
        }
    }
    public String getStrDistance(float distance){
        int i = (int)distance;
        if (i>1000){;
            return i/1000+"公里";
        }else {
            return i+"米";
        }
    }
}
