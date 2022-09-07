package com.example.sos_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity
{
    private EditText txt_id, txt_pw, txt_nick;      // 회원가입 입력필드
    private Button btn_register;            //회원가입 버튼
    Spinner spinner_type;
    String[] items = {"구조대", "관리자", "일반회원"};
    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_id = findViewById(R.id.txt_id);
        txt_pw = findViewById(R.id.txt_pw);
        txt_nick = findViewById(R.id.txt_nick);
        btn_register = findViewById(R.id.btn_register);
        spinner_type = findViewById(R.id.spinner_type);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 처리 시작
                String id = txt_id.getText().toString();
                String pw = txt_pw.getText().toString();
                String nick = txt_nick.getText().toString();
                String type = spinner_type.getSelectedItem().toString();
                request = new StringRequest(
                        Request.Method.POST,
                        "http://172.30.1.60:8083/SOSLIFEBACKUP/appregist.do",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("checkres", response.toString());
                                if(response.toString().equals("1")){
                                    Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                            }
                        }

                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user_id", id);
                        params.put("user_pw", pw);
                        params.put("user_nick", nick);
                        params.put("user_type", type);
                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}