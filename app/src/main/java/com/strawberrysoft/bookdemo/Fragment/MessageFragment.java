package com.strawberrysoft.bookdemo.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.strawberrysoft.bookdemo.Activity.ContactActivity;
import com.strawberrysoft.bookdemo.Adapter.MessageListAdapter;
import com.strawberrysoft.bookdemo.Bean.ConversationBean;
import com.strawberrysoft.bookdemo.Bean.ConversationInfo;
import com.strawberrysoft.bookdemo.R;
import com.strawberrysoft.bookdemo.View.DividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private RecyclerView rv_list;
    private Context context;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        initView(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {
        context = getContext();
        rv_list = (RecyclerView) view.findViewById(R.id.rv_message_list);
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        view.findViewById(R.id.iv_message_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContactActivity.class);
                startActivity(intent);
            }
        });
        ConversationInfo conversationInfo = new ConversationInfo();
        MessageListAdapter adapter = new MessageListAdapter(context,conversationInfo.getCoversationList());
        rv_list.setAdapter(adapter);
        rv_list.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
    }

}
