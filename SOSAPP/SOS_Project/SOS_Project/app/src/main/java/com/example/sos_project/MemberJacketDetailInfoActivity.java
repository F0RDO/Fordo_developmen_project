package com.example.sos_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MemberJacketDetailInfoActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest request;

    MemberJacketDetailInfoAdapter adapter;
    ArrayList<JacketStatusVO> dataset;
    ListView listview;

    String user_id = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_jacket_detail_info);

        dataset = new ArrayList<>();
        listview = findViewById(R.id.listView);
        requestQueue = Volley.newRequestQueue(MemberJacketDetailInfoActivity.this);
        adapter = new MemberJacketDetailInfoAdapter(getApplicationContext(), R.layout.member_jacket_detail_info_adapter, dataset);

        LoadJacketDetailInfoList();

    }

    public void LoadJacketDetailInfoList(){
        Intent userIntent = getIntent();
        String user_id = userIntent.getStringExtra("user_id");
        request = new StringRequest(
                Request.Method.GET,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/getjacketdetailinfo.do?user_id="+user_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("checkres", response.toString());
                            JSONObject json =new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("jacketdetailinfo");
                            for(int i=0; i<list.length(); i++) {
                                JSONObject question = (JSONObject) list.get(i);
                                String product_id = question.getString("product_id");
                                String batteryStatus = question.getString("batteryStatus");
                                String connectStatus = question.getString("connectStatus");
                                String waterPressure = question.getString("water_pressure");
                                String waterTemperature = question.getString("water_temperature");
                                String waterDetect = question.getString("water_detect");
                                dataset.add(new JacketStatusVO(product_id, batteryStatus, connectStatus, Double.parseDouble(waterPressure), Double.parseDouble(waterTemperature),Double.parseDouble(waterDetect)));
                            }
                            listview.setAdapter(adapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }
}