package com.example.quanly.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanly.model.OrderInner;
import com.example.quanly.model.OrderOuter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDAO {
    private Context context;
    private String OrderOuterXacNhanURL = IP.IP + "/quanlygiatsay/QLgetOrderOuterXacNhan.php";
    private String DonhangInnerURL = IP.IP + "/giatsay/getOrderInner.php";

    private String changeStatusToDeliveryURL = IP.IP + "/quanlygiatsay/changeStatusToDelivery.php";
    private String changeStatusToCompleteURL = IP.IP + "/quanlygiatsay/changeStatusToComplete.php";
    private String changeComfirmURL = IP.IP + "/quanlygiatsay/QLchangeComfirm.php";
    private String changeCancelURL = IP.IP + "/quanlygiatsay/QLchangeCancel.php";
    private String getAllWaitComfirmUURL = IP.IP + "/quanlygiatsay/getWaitComfirmOrder.php";
    private String changeCompletedURL = IP.IP + "/quanlygiatsay/QLchangeCompleted.php";
    private String getDeliveryOrderURL = IP.IP + "/quanlygiatsay/QLgetDeliveryOrderURL.php";

    public OrderDAO(Context context) {
        this.context = context;
    }
    public interface OrderDAOITF{void xuli(Object obj);}


    public void changeStatusToDelivery(String iddonhang, OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changeStatusToDeliveryURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xuli.xuli(null);
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
                map.put("iddonhang",iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void changeStatusToComplete(String iddonhang, OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changeStatusToCompleteURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xuli.xuli(null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr order dao 99", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("iddonhang",iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void getDonHangInner(String iddonhang, OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DonhangInnerURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<OrderInner> arrayList = new ArrayList<>();
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        arrayList.add(new OrderInner(
                                jsonObject.getInt("quantity"),
                                jsonObject.getInt("price"),
                                jsonObject.getString("img"),
                                jsonObject.getString("name"),
                                jsonObject.getString("description"),
                                jsonObject.getString("address")
                        ));
                    }
                    Log.e("arr", arrayList.toString());
                    xuli.xuli(arrayList);
                }catch (Exception e){
                    Log.e("error xacnhan " , e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error xacnhan " , error.getMessage());

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iddonhang", iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getOrDerWaitComfirm(OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getAllWaitComfirmUURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<OrderOuter> arrayList = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        arrayList.add(new OrderOuter(
                                jsonObject.getString("username"),
                                jsonObject.getString("date"),
                                jsonObject.getString("iddonhang"),
                                jsonObject.getString("totalprice")
                        ));
                    }
                    xuli.xuli(arrayList);
                } catch (JSONException e) {
                    Log.e("orderdao 153",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }



    public void changComfirm(String iddonhang, OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changeComfirmURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xuli.xuli(null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error xacnhan " , error.getMessage());

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iddonhang", iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void changCancel(String iddonhang, OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changeCancelURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xuli.xuli(null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error xacnhan " , error.getMessage());

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iddonhang", iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void changCompleted(String iddonhang, OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changeCompletedURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xuli.xuli(null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error xacnhan " , error.getMessage());

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("iddonhang", iddonhang);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void getOrDerDelivery(OrderDAOITF xuli){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getDeliveryOrderURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<OrderOuter> arrayList = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        arrayList.add(new OrderOuter(
                                jsonObject.getString("username"),
                                jsonObject.getString("date"),
                                jsonObject.getString("iddonhang"),
                                jsonObject.getString("totalprice")
                        ));
                    }
                    xuli.xuli(arrayList);
                } catch (JSONException e) {
                    Log.e("orderdao 153",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
