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
public class Oder2Fragment extends Fragment {

    private Context context;
    private RecyclerView rv_list;

    public Oder2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oder2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        rv_list = (RecyclerView) view.findViewById(R.id.rv_oder2_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        String[] ISBN = {"9787535479860","9787512702646","9787802444331","9787564067526","9787540454302"};
        rv_list.setAdapter(new Oder1InfoAdapter(context,1,ISBN));
    }

}
