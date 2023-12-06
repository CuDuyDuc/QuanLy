package com.example.quanly.dao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanly.MainActivity;
import com.example.quanly.model.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private Context context;
    private String checkLoginURL = IP.IP + "/quanlygiatsay/QLcheckLogin.php";

    public UserDAO(Context context) {
        this.context = context;
    }
    public interface UserDAOITF{void xuli(Object obj);}
    public void checkLogin(String username, String password, UserDAOITF xuli){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, checkLoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    User user = new User(
                            username,
                            object.getString("name"),
                            object.getString("phone"),
                            object.getString("email")
                    );
                    xuli.xuli(user);
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("user", user);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } catch (JSONException e) {
                    Log.e("errr userdao 54", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errr userdao 61", error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("username",username);
                map.put("password", password);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
