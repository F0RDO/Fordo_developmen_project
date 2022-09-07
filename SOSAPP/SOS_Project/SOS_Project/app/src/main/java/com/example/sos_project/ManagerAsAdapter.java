package com.example.sos_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ManagerAsAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<QuestionVO> dataset;

    private LayoutInflater inflater;

    public ManagerAsAdapter(Context context, int layout, ArrayList<QuestionVO> dataset) {
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

        TextView q_name = view.findViewById(R.id.q_name);
        TextView q_email = view.findViewById(R.id.q_email);
        TextView q_content = view.findViewById(R.id.q_content);
        TextView q_phone = view.findViewById(R.id.q_phone);
        TextView q_date = view.findViewById(R.id.q_date);
        TextView q_type = view.findViewById(R.id.q_type);
        q_name.setText(dataset.get(i).getQ_name());
        q_email.setText(dataset.get(i).getQ_email());
        q_content.setText(dataset.get(i).getQ_content());
        q_phone.setText(dataset.get(i).getQ_phone());
        q_date.setText(dataset.get(i).getQ_date());
        q_type.setText(dataset.get(i).getQ_type());

        return view;
    }
}
