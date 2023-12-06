package com.example.quanly.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanly.model.OrderOuter;
import com.example.quanly.model.ThongKe;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatisticDAO {
    private Context context;
    private String OrderOuterNhieuNgayURLURL = IP.IP + "/quanlygiatsay/getOrderOuterNhieuNgay.php";
    private String OrderOuterForStatisticURL = IP.IP + "/quanlygiatsay/QLgetOrderDate.php";
    private String getThongKeURL = IP.IP + "/quanlygiatsay/getThongKeTheoNgay.php";
    private String getThongKeNhieuNgayURL = IP.IP + "/quanlygiatsay/QLThongKeNhieuNgay.php";

    private String OrderInnerURL = IP.IP + "/quanlygiatsay/QLcheckLogin.php";
    private String changeStatusToDeliveryURL = IP.IP + "/quanlygiatsay/QLcheckLogin.php";
    public StatisticDAO(Context context) {
        this.context = context;
    }
    public interface StatisticDAOITF{void xuli(Object obj);}
    public void getForDate(String date, StatisticDAOITF xuli){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, OrderOuterForStatisticURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                $username , $iddonhang, $date, $totalprice
                ArrayList<OrderOuter> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arrayList.add(new OrderOuter(
                                jsonObject.getString("username"),
                                date,
                                jsonObject.getString("iddonhang"),
                                jsonObject.getString("totalprice")
                        ));
                    }
                    xuli.xuli(arrayList);
                } catch (JSONException e) {
                    Log.e("static 58", e.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr order dao 66", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("date",date);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getDaytoDay(String fromDate, String toDate, StatisticDAOITF xuli){
        Log.e("errr order dao 82", fromDate+toDate);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, OrderOuterNhieuNgayURLURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<OrderOuter> arrayList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arrayList.add(new OrderOuter(
                                jsonObject.getString("username"),
                                jsonObject.getString("date"),
                                jsonObject.getString("iddonhang"),
                                jsonObject.getString("totalprice")
                        ));
                    }
                    xuli.xuli(arrayList);
                } catch (JSONException e) {
                    Log.e("static 99", e.getMessage());

                }

                xuli.xuli(arrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr order dao 73", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("fromDate",fromDate);
                map.put("toDate",toDate);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getThongKe(String date, StatisticDAOITF xuli){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getThongKeURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    ThongKe thongKe = new ThongKe(
                            jsonObject.getString("doanhthu"),
                            jsonObject.getString("hoadon"),
                            jsonObject.getString("dichvu")
                    );
                    xuli.xuli(thongKe);
                } catch (JSONException e) {
                    Log.e("errr order dao 123", e.getMessage());

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr order dao 132", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("day",date);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void getThongKe(String fromdate, String todate, StatisticDAOITF xuli){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getThongKeNhieuNgayURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    ThongKe thongKe = new ThongKe(
                            jsonObject.getString("doanhthu"),
                            jsonObject.getString("hoadon"),
                            jsonObject.getString("dichvu")
                    );
                    xuli.xuli(thongKe);
                } catch (JSONException e) {
                    Log.e("errr order dao 123", e.getMessage());

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr order dao 132", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("fromdate",fromdate);
                map.put("todate",todate);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
