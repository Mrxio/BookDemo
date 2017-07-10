package com.strawberrysoft.bookdemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strawberrysoft.bookdemo.Adapter.FindAdapter;
import com.strawberrysoft.bookdemo.Bean.ShuPingBean;
import com.strawberrysoft.bookdemo.Bean.ShuPingInfo;
import com.strawberrysoft.bookdemo.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {

    private Context context;
    private RecyclerView rv_list;
    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        context = getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_list = (RecyclerView) view.findViewById(R.id.rv_find_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        ShuPingInfo info = new ShuPingInfo();
        List<ShuPingBean> list = info.getShuPing();
        rv_list.setAdapter(new FindAdapter(context,list));
    }
}
