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

public class ManagerMainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    StringRequest request;

    ManagerAsAdapter adapter;
    ArrayList<QuestionVO> dataset;

    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);

        listview = findViewById(R.id.listview);

        requestQueue = Volley.newRequestQueue(ManagerMainActivity.this);
        dataset = new ArrayList<>();
        adapter = new ManagerAsAdapter(getApplicationContext(), R.layout.manager_as_list, dataset);

        LoadAsList();
    }

    public void LoadAsList(){
        request = new StringRequest(
                Request.Method.GET,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/managermainlist.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("checkres", response.toString());
                            JSONObject json =new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("questionlist");
                            for(int i=0; i<list.length(); i++) {
                                JSONObject question = (JSONObject) list.get(i);
                                String q_name = question.getString("q_name");
                                String q_email = question.getString("q_email");
                                String q_phone = question.getString("q_phone");
                                String q_type = question.getString("q_type");
                                String q_content = question.getString("q_content");
                                String q_date = question.getString("q_date");
                                int q_seq = Integer.parseInt(question.getString("q_seq"));
                                String user_id = question.getString("q_user_id");
                                dataset.add(new QuestionVO(q_seq, q_name, q_email, q_content, q_phone, q_date, user_id, q_type));
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