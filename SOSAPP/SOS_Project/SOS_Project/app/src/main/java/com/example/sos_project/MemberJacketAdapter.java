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



public class MemberJacketAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<JacketStatusVO> dataset;
    private ArrayList<tbl_user> user_info;
    private LayoutInflater inflater;

    public MemberJacketAdapter(Context context, int layout, ArrayList<JacketStatusVO> dataset, ArrayList<tbl_user> user_info) {
        this.context = context;
        this.layout = layout;
        this.dataset = dataset;
        this.user_info = user_info;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MemberJacketAdapter(Context context, int layout, ArrayList<JacketStatusVO> dataset) {
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

        Button btn_chart;

        TextView product_id = view.findViewById(R.id.product_id);
        TextView batteryStatus = view.findViewById(R.id.batteryStatus);
        TextView connectStatus = view.findViewById(R.id.connectStatus);
        TextView currentStatus = view.findViewById(R.id.currentStatus);

        product_id.setText(dataset.get(i).getProduct_id());
        batteryStatus.setText(dataset.get(i).getBatteryStatus());
        connectStatus.setText(dataset.get(i).getConnectStatus());
        currentStatus.setText("수압"+dataset.get(i).getWaterPressure()+" 온도"+dataset.get(i).getWaterTemperature()+" 물정도"+dataset.get(i).getWaterDetect());
        btn_chart = view.findViewById(R.id.btn_chart);
        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), MainActivity1.class);
                intent1.putExtra("dataset", dataset);
                intent1.putExtra("user_info", user_info);
                //intent1.putExtra("수압", dataset.get(i).getWaterPressure()+"");
                //intent1.putExtra("온도", dataset.get(i).getWaterTemperature()+"");
                //intent1.putExtra("수위", dataset.get(i).getWaterDetect()+"");
                //Log.d("btn","이동");
                context.startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                //context.startActivity(intent1);
            }
        });
        return view;
    }
}