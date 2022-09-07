package com.example.sos_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class memberJacketQuestionInfoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<memberJacketQuestionVO> dataset;

    private LayoutInflater inflater;


    public memberJacketQuestionInfoAdapter(Context context, int layout, ArrayList<memberJacketQuestionVO> dataset) {
        this.context = context;
        this.layout = layout;
        this.dataset = dataset;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int i) {
        return dataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(layout, null);

        TextView id = view.findViewById(R.id.txt_userInfo);


        Button btn_vest = view.findViewById(R.id.btn_vest);
        Button btn_quest = view.findViewById(R.id.btn_quest);


        id.setText(dataset.get(i).getUser_id());
        btn_vest.setText(dataset.get(i).getJacket_count());
        btn_quest.setText(dataset.get(i).getQuestion_count());


        btn_vest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MemberJacketDetailInfoActivity.class);
                intent.putExtra("user_id",dataset.get(i).getUser_id());
                context.startActivity(intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        btn_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ManagerMainActivity.class);
                context.startActivity(intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        return view;
    }
}