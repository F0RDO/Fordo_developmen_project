package com.example.sos_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RescueMainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap = null;

    RequestQueue requestQueue;
    StringRequest request;
    ArrayList<JacketLocationVO> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_main);
        dataset = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(RescueMainActivity.this);

        LoadJacketLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


    }
    public void LoadJacketLocation(){
        request = new StringRequest(
                Request.Method.POST,
                "http://172.30.1.60:8083/SOSLIFEBACKUP/rescuejacketinfo.do",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("checkres", response.toString());
                            JSONObject json =new JSONObject(response);
                            JSONArray list = (JSONArray) json.getJSONArray("jacket_location");
                            Log.d("checklist", list.length()+"");
                            for(int i=0; i<list.length(); i++) {
                                JSONObject location = (JSONObject) list.get(i);
                                String jl_seq = location.getString("jl_seq");
                                String jacket_seq = location.getString("jacket_seq");
                                String jl_latitude = location.getString("jl_latitude");
                                String jl_longitude = location.getString("jl_longitude");
                                String jl_date = location.getString("jl_date");
                                dataset.add(new JacketLocationVO(Integer.parseInt(jl_seq), Double.parseDouble(jl_latitude), Double.parseDouble(jl_longitude), jl_date, Integer.parseInt(jacket_seq)));
                            }

                            if(mMap != null){
                                /*LatLng Warning = new LatLng(dataset.get(0).getJl_latitude(), dataset.get(0).getJl_longitude());
                                MarkerOptions markerOptions3 = new MarkerOptions();
                                markerOptions3.position(Warning);
                                markerOptions3.title("위험발생");
                                markerOptions3.snippet("위도"+dataset.get(0).getJl_latitude()+" 경도"+dataset.get(0).getJl_longitude());*/
                                LatLng Warning = new LatLng(34.967509, 126.385326);
                                MarkerOptions markerOptions3 = new MarkerOptions();
                                markerOptions3.position(Warning);
                                markerOptions3.title("위험발생");
                                markerOptions3.snippet("위도 : 34.967509, 경도 : 126.385326");
                                mMap.addMarker(markerOptions3);
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Warning, 15));
                                mMap.setOnInfoWindowClickListener(infoWindowClickListener);
                            }

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
                params.put("jacket_seq", "4");
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LoadJacketLocation();
        mMap = googleMap;

        if(dataset.size() > 0){

            LatLng Warning = new LatLng(dataset.get(0).getJl_latitude(), dataset.get(0).getJl_longitude());
            MarkerOptions markerOptions3 = new MarkerOptions();
            markerOptions3.position(Warning);
            markerOptions3.title("위험발생");
            markerOptions3.snippet("위도"+dataset.get(0).getJl_latitude()+" 경도"+dataset.get(0).getJl_longitude());
            mMap.addMarker(markerOptions3);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Warning, 15));
            mMap.setOnInfoWindowClickListener(infoWindowClickListener);
        }

    }

    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(@NonNull Marker marker) {
            Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
            startActivity(intent);
        }
    };
}