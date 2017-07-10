package com.strawberrysoft.bookdemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strawberrysoft.bookdemo.Adapter.Oder1InfoAdapter;
import com.strawberrysoft.bookdemo.R;

public class Oder4Fragment extends Fragment {

    private Context context;
    private RecyclerView rv_list;

    public Oder4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oder4, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        context = getContext();
        rv_list = (RecyclerView) view.findViewById(R.id.rv_oder4_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        String[] ISBN = {"9787513312998","9787505736443","9787562493495"};
        rv_list.setAdapter(new Oder1InfoAdapter(context,3,ISBN));
    }

}
