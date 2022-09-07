package com.example.sos_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MemberJacketDetailInfoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<JacketStatusVO> dataset;

    private LayoutInflater inflater;

    public MemberJacketDetailInfoAdapter() {
    }

    public MemberJacketDetailInfoAdapter(Context context, int layout, ArrayList<JacketStatusVO> dataset) {
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

        TextView jacketNum = view.findViewById(R.id.txt_jacket_seq);
        TextView jacket_battery = view.findViewById(R.id.txt_jacket_battery);
        TextView jacket_connect = view.findViewById(R.id.txt_jacket_connect);
        TextView jacket_detect = view.findViewById(R.id.txt_jacket_detect);
        TextView jacket_pressure = view.findViewById(R.id.txt_jacket_pressure);
        TextView jacket_temperature = view.findViewById(R.id.txt_jacket_temperature);

        jacketNum.setText(dataset.get(i).getProduct_id());
        jacket_battery.setText(dataset.get(i).getBatteryStatus());
        jacket_connect.setText(dataset.get(i).getConnectStatus());
        jacket_detect.setText(dataset.get(i).getWaterDetect()+"");
        jacket_pressure.setText(dataset.get(i).getWaterPressure()+"");
        jacket_temperature.setText(dataset.get(i).getWaterTemperature()+"");

        return view;
    }
}