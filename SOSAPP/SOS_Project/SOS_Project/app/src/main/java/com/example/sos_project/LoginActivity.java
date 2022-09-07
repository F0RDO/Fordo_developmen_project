package com.example.sos_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {
    private EditText txt_id, txt_pw;      // 로그인 입력필드
    private Button btn_login;

    RequestQueue requestQueue;
    StringRequest request;
    ArrayList<tbl_user> dataset = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_id = findViewById(R.id.user_id);
        txt_pw = findViewById(R.id.user_pw);

        btn_login = findViewById(R.id.btn_login);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 요청
                String id = txt_id.getText().toString();
                String pw = txt_pw.getText().toString();

                request = new StringRequest(
                        Request.Method.POST,
                        "http://172.30.1.60:8083/SOSLIFEBACKUP/applogin.do",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject json =new JSONObject(response);
                                    JSONArray user = (JSONArray) json.getJSONArray("userInfo");
                                    Log.d("checkjson", user.toString());
                                    Intent intent = new Intent();
                                    for(int i=0; i<user.length(); i++){
                                        JSONObject tbl_user =(JSONObject) user.get(i);
                                        String user_id = tbl_user.getString("user_id");
                                        String user_nick = tbl_user.getString("user_nick");
                                        String user_type = tbl_user.getString("user_type");
                                        if(user_type.equals("관리자")){
                                            //intent = new Intent(getApplicationContext(), ManagerMainActivity.class);
                                            intent = new Intent(getApplicationContext(), MemberJacketQuestionInfoActivity.class);
                                        }else if(user_type.equals("일반회원")){
                                            intent = new Intent(getApplicationContext(), MemberMainActivity.class);
                                        } else{
                                            intent = new Intent(getApplicationContext(), RescueMainActivity.class);
                                        }
                                            String user_pw = tbl_user.getString("user_pw");
                                        String user_joindate = tbl_user.getString("user_joindate");
                                        dataset.add(new tbl_user(user_id, user_pw, user_nick, user_type, user_joindate));
                                        intent.putExtra("user", dataset);
                                    }
                                    startActivity(intent);
                                    finish();

                                }catch (JSONException e){
                                    e.printStackTrace();
                                    finish();//인텐트 종료
                                    overridePendingTransition(0, 0);//인텐트 효과 없애기
                                    Intent intent = getIntent(); //인텐트
                                    startActivity(intent); //액티비티 열기
                                    overridePendingTransition(0, 0);//인텐트 효과 없애기
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user_id", id);
                        params.put("user_pw", pw);
                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });


        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}