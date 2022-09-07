package com.example.sos_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity1 extends AppCompatActivity {

    LineChart lineChart;
    ArrayList<JacketStatusVO> dataset1;
    Double waterpressure1 = 0.0;
    int waterpressure2 = 0;
    int watertemperature1 = 0;
    int waterdetect1 = 0;
    MemberJacketAdapter adapter;
    ArrayList<JacketStatusVO> dataset;
    RequestQueue requestQueue;
    StringRequest request;
    ListView listview;
    ArrayList<tbl_user> user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        listview = findViewById(R.id.line_chart_list);
        dataset = new ArrayList<>();
        adapter = new MemberJacketAdapter(getApplicationContext(), R.layout.member_status_list, dataset);
        requestQueue = Volley.newRequestQueue(MainActivity1.this);
        AllJacketList();

        //String pres1 = getIntent().getStringExtra("수압");
        //String temp1 = getIntent().getStringExtra("온도");
        //String dete1 = getIntent().getStringExtra("수위");
        //waterpressure1 = Double.parseDouble(pres1);
        //Log.d(waterpressure1+"" , "waterpressure");
        //watertemperature1 = Integer.parseInt(temp1);
        //waterdetect1 = Integer.parseInt(dete1);
        //= getIntent().getSerializableExtra("dataset");
        Intent receive = getIntent();
        ArrayList<JacketStatusVO> dataset1 = (ArrayList<JacketStatusVO>)receive.getSerializableExtra("dataset");
        ArrayList<JacketStatusVO> dataset2 = (ArrayList<JacketStatusVO>)receive.getSerializableExtra("user_info");
        double[] data3 = new double[830];
        double[] data4 = new double[830];
        double[] data5 = new double[830];
        int[] data33 = new int[830];
        int[] data44 = new int[830];
        int[] data55 = new int[830];
        String[] data333 = new String[830];
        String[] data444 = new String[830];
        String[] data555 = new String[830];
//        for(int i = 0; i<830;i++) {
//            data3[i] = dataset2.get(0).getWaterDetect();
//            data333[i] = String.valueOf(data3[i]);
//            data333[i] = data333[i].replace(".", "");
//            data33[i] = Integer.parseInt(data333[i]);
//        }
//        for(int i = 0; i<830;i++) {
//            data4[i] = dataset2.get(0).getWaterPressure();
//            data444[i] = String.valueOf(data4[i]);
//            data444[i] = data444[i].replace(".", "");
//            data44[i] = Integer.parseInt(data444[i]);
//        }
//        for(int i = 0; i<830;i++) {
//            data5[i] = dataset2.get(0).getWaterTemperature();
//            data555[i] = String.valueOf(data5[i]);
//            data555[i] = data555[i].replace(".", "");
//            data55[i] = Integer.parseInt(data555[i]);
//        }

        Intent user1 = getIntent();
        user_info = (ArrayList<tbl_user>) user1.getSerializableExtra("user_info");
        // 초기화
        lineChart = findViewById(R.id.line_chart);


        // 1. 데이터셋에 데이터 넣기
        LineDataSet lineDataSet1 = new LineDataSet(data1(data33), "Data Set1");
        LineDataSet lineDataSet2 = new LineDataSet(data2(data44), "Data Set2");
        LineDataSet lineDataSet3 = new LineDataSet(data3(data55), "Data Set3");

        // 2. 리스트에 데이터셋 추가
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet3);

        // 차트 커스텀

        //라인 굵기
        lineDataSet1.setLineWidth(4);
        lineDataSet2.setLineWidth(4);
        lineDataSet3.setLineWidth(4);
        // 라인 색상
        // bgr 순으로

        lineDataSet1.setColor(Color.BLUE);
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet3.setColor(Color.RED);

        // 데이터 원 표시 여부
        lineDataSet1.setDrawCircles(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet3.setDrawCircles(true);

        // 데이터 원 안의 홀 비울지 여부 (디폴트 true)
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet3.setDrawCircleHole(false);

        // 데이터 원 색상
        lineDataSet1.setCircleColor(Color.BLUE);
        lineDataSet2.setCircleColor(Color.GREEN);
        lineDataSet3.setCircleColor(Color.RED);

        //데이터 원 홀 색상
        lineDataSet1.setCircleHoleColor(Color.BLUE);
        lineDataSet2.setCircleHoleColor(Color.GREEN);
        lineDataSet3.setCircleHoleColor(Color.RED);

        // 데이터 원 반지름
        lineDataSet1.setCircleRadius(10);
        lineDataSet2.setCircleRadius(10);
        lineDataSet3.setCircleRadius(10);

        // 데이터 원 홀 반지름
        lineDataSet1.setCircleHoleRadius(5);

        // 데이터 숫자 크기
        lineDataSet1.setValueTextSize(10);
        lineDataSet2.setValueTextSize(10);
        lineDataSet3.setValueTextSize(10);

        // 데이터 숫자 색상
        lineDataSet1.setValueTextColor(Color.BLUE);
        lineDataSet2.setValueTextColor(Color.GREEN);
        lineDataSet3.setValueTextColor(Color.RED);

        // 라인 대쉬 형태로(끊김형태) 라인길이, 공간길이, 단계  선 끊겨있는거
        //lineDataSet1.enableDashedLine(5, 10, 0);

        // 라인 별 색상 설정
        //int colorArray1[] = {R.color.color1, R.color.color2, R.color.color3};

        //lineDataSet1.setColors(colorArray1, MainActivity1.this);

        // 차트 스타일
        // 차트 배경 색상
        lineChart.setBackgroundColor(Color.WHITE);

        // 차트 데이터 없음 표시
        lineChart.setNoDataText("No Data");

        // 차트 데이터 없음 텍스트 색상    // 이 새끼 쓸일없음
        // lineChart.setNoDataTextColor(Color.BLUE);

        // 격자 그리드 적용
        //lineChart.setDrawGridBackground(true);

        // 차트 외각선 진하게
        //lineChart.setDrawBorders(false);

        // 차트 외곽선 색상
        //lineChart.setBorderColor(Color.RED);

        // 차트 외곽선 굵기
        // lineChart.setBorderWidth(5);

        // 범례(라벨)
        Legend legend = lineChart.getLegend();

        // 라벨 표시(디폴트 true)
        legend.setEnabled(true);

        // 라벨 텍스트 색상
        legend.setTextColor(Color.RED);

        // 라벨 텍스트 사이즈
        legend.setTextSize(15);

        // 라벨 아이콘 설정
        legend.setForm(Legend.LegendForm.CIRCLE); // line

        //라벨 아이콘 크기 설정
        legend.setFormSize(10);

        // 라벨 간 거리
        legend.setXEntrySpace(15);

        //라벨 아이콘과 라벨 텍스트 거리
        legend.setFormToTextSpace(10);

        // 커스텀 라벨 만들어 보기
        LegendEntry[] legendEntries = new LegendEntry[3];

        // 색상
        int[] colorArray = {Color.BLUE, Color.GREEN, Color.RED};

        // 라벨명
        String[] legendName = {"수위", "수압", "수온"};

        for(int i = 0; i<legendEntries.length;i++) {

            LegendEntry entry = new LegendEntry();

            // 색상
            entry.formColor = colorArray[i];

            // 텍스트
            entry.label = legendName[i];

            // 담기
            legendEntries[i] = entry;
        }

        // 적용
        legend.setCustom(legendEntries);


        // 설명
        Description description = new Description();
        description.setText("설명"); // 설명
        description.setTextSize(20); // 설명 텍스트 크기
        description.setTextColor(Color.BLUE);

        //lineChart.setDescription(description);


        // 3. 라인데이터에 리스트 추가
        LineData data = new LineData(dataSets);

        // 4. 차트에 라인데이터 추가
        lineChart.setData(data);

        // 5. 차트 초기화
        lineChart.invalidate();
    } // onCreate

    private ArrayList<Entry> data1(int[] data33) {

        ArrayList<Entry> dataList = new ArrayList<>();
//        for(int i =0; i<data33.length;i++) {
//            dataList.add(new Entry(i, data33[i]));
//        }
        dataList.add(new Entry(0, 0));
        dataList.add(new Entry(1, 10));
        dataList.add(new Entry(2, 20));
        dataList.add(new Entry(3, 40));
        dataList.add(new Entry(4, 30));
        dataList.add(new Entry(5, 50));
        dataList.add(new Entry(6, 70));
        dataList.add(new Entry(7, 20));
        dataList.add(new Entry(8, 40));
        dataList.add(new Entry(9, 60));
        dataList.add(new Entry(10, 55));
        dataList.add(new Entry(11, 45));
        dataList.add(new Entry(12, 30));
        dataList.add(new Entry(13, 40));
        dataList.add(new Entry(14, 30));
        dataList.add(new Entry(15, 40));
        return dataList;
    }

    private ArrayList<Entry> data2(int[] data44) {

        ArrayList<Entry> dataList = new ArrayList<>();

        dataList.add(new Entry(0, 0));
        dataList.add(new Entry(1, 40));
        dataList.add(new Entry(2, 50));
        dataList.add(new Entry(3, 60));
        dataList.add(new Entry(4, 70));
        dataList.add(new Entry(5, 90));
        dataList.add(new Entry(6, 110));
        dataList.add(new Entry(7, 100));
        dataList.add(new Entry(8, 90));
        dataList.add(new Entry(9, 110));
        dataList.add(new Entry(10, 70));
        dataList.add(new Entry(11, 90));
        dataList.add(new Entry(12, 80));
        dataList.add(new Entry(13, 50));
        dataList.add(new Entry(14, 60));
        dataList.add(new Entry(15, 70));

        return dataList;
    }

    private ArrayList<Entry> data3(int[] data55) {

        ArrayList<Entry> dataList = new ArrayList<>();


        dataList.add(new Entry(0, 0));
        dataList.add(new Entry(1, -1));
        dataList.add(new Entry(2, -8));
        dataList.add(new Entry(3, -5));
        dataList.add(new Entry(4, -4));
        dataList.add(new Entry(5, -3));
        dataList.add(new Entry(6, -7));
        dataList.add(new Entry(7, -9));
        dataList.add(new Entry(8, -5));
        dataList.add(new Entry(9, 0));
        dataList.add(new Entry(10, 5));
        dataList.add(new Entry(11, 3));
        dataList.add(new Entry(12, 2));
        dataList.add(new Entry(13, 4));
        dataList.add(new Entry(14, 5));
        dataList.add(new Entry(15, 7));

        return dataList;
    }
    public void AllJacketList() {
        Intent receive = getIntent();
        ArrayList<JacketStatusVO> jacket = (ArrayList<JacketStatusVO>) receive.getSerializableExtra("jacketdetailinfo");
        //Log.d("jacket", jacket.get(0).getBatteryStatus());
        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/getchartInfo.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("jacketres", response.toString());
                            JSONObject json = new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("jacketdetailinfo");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject jacket = (JSONObject) list.get(i);
                                String batteryStatus = jacket.getString("batteryStatus");
                                String connectStatus = jacket.getString("connectStatus");
                                //String product_id = jacket.getString("product_id");
                                Double waterPressure = Double.parseDouble(jacket.getString("water_pressure"));
                                Double waterTemperature = Double.parseDouble(jacket.getString("water_temperature"));
                                Double waterDetect = Double.parseDouble(jacket.getString("water_detect"));
                                dataset.add(new JacketStatusVO(batteryStatus, connectStatus, waterPressure, waterTemperature, waterDetect));

                            }
                            listview.setAdapter(adapter);
                        } catch (JSONException e) {
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
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_info.get(0).getUser_id());
                return params;
            }
        };
        requestQueue.add(request);
    }
}// mainactivity