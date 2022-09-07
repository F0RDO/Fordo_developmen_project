package com.example.sos_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class member_status_list extends AppCompatActivity {

    Button btn_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_status_list);

        btn_chart = findViewById(R.id.btn_chart);

        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity1.class);
                Log.d("btn","이동");
                startActivity(intent);
            }
        });
    }
}