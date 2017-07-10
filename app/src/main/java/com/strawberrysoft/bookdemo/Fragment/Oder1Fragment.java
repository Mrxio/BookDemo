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

/**
 * A simple {@link Fragment} subclass.
 */
public class Oder1Fragment extends Fragment {

    private RecyclerView rv_list;
    private Context context;
    public Oder1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oder1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        rv_list = (RecyclerView) view.findViewById(R.id.rv_oder1_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        String[] ISBN = {"9787214068828","9787510423666","9787511310934"};
        rv_list.setAdapter(new Oder1InfoAdapter(context,0,ISBN));
    }

}
