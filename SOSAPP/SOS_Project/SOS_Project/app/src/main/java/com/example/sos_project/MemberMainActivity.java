package com.example.sos_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class MemberMainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest request;

    MemberJacketAdapter adapter;
    ArrayList<JacketStatusVO> dataset;
    ArrayList<tbl_user> user;

    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_main);

        listview = findViewById(R.id.listview2);

        Intent user1 = getIntent();
        user = (ArrayList<tbl_user>)user1.getSerializableExtra("user");
        requestQueue = Volley.newRequestQueue(MemberMainActivity.this);
        dataset = new ArrayList<>();
        adapter = new MemberJacketAdapter(getApplicationContext(), R.layout.member_status_list, dataset, user);

        LoadJacketList();
    }


    public void LoadJacketList(){
        Intent receive = getIntent();
        ArrayList<tbl_user> user = (ArrayList<tbl_user>)receive.getSerializableExtra("user");
        Log.d("checkuser",user.get(0).getUser_id().toString());
        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/memberjacketlist.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("checkres", response.toString());
                            JSONObject json =new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("androidjacketlist");
                            for(int i=0; i<list.length(); i++) {
                                JSONObject jacket = (JSONObject) list.get(i);
                                String batteryStatus = jacket.getString("batterystatus");
                                String connectStatus = jacket.getString("connectstatus");
                                String product_id = jacket.getString("product_id");
                                Double waterPressure = Double.parseDouble(jacket.getString("waterpressure"));
                                Double waterTemperature = Double.parseDouble(jacket.getString("watertemperature"));
                                Double waterDetect = Double.parseDouble(jacket.getString("waterdetect"));
                                dataset.add(new JacketStatusVO(product_id, batteryStatus, connectStatus, waterPressure, waterTemperature, waterDetect));
                            }
                            listview.setAdapter(adapter);
                            // ?
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
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", user.get(0).getUser_id());
                return params;
            }
        };
        requestQueue.add(request);
    }
}