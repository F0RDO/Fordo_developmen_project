package com.example.sos_project;

import androidx.appcompat.app.AppCompatActivity;

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

public class MemberJacketQuestionInfoActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest request;

    memberJacketQuestionInfoAdapter adapter;
    ArrayList<memberJacketQuestionVO> dataset;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_jacket_question_info);

        dataset = new ArrayList<>();
        listview = findViewById(R.id.listView3);
        requestQueue = Volley.newRequestQueue(MemberJacketQuestionInfoActivity.this);
        adapter = new memberJacketQuestionInfoAdapter(getApplicationContext(), R.layout.member_jacket_question_list, dataset);

        LoadJacketQuestionList();
    }

    public void LoadJacketQuestionList(){
        request = new StringRequest(
                Request.Method.GET,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/getjacketquestioninfo.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("checkres", response.toString());
                            JSONObject json =new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("jacket_question");
                            for(int i=0; i<list.length(); i++) {
                                JSONObject question = (JSONObject) list.get(i);
                                String user_id = question.getString("user_id");
                                String jacket_count = question.getString("jacket_count");
                                String question_count = question.getString("question_count");
                                dataset.add(new memberJacketQuestionVO(user_id, jacket_count, question_count));
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