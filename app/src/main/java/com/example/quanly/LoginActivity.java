package com.example.quanly;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanly.dao.UserDAO;
import com.example.quanly.model.User;


public class LoginActivity extends AppCompatActivity {

    private EditText username , password;
    private CheckBox chkremember;
    private Button btnlogin;
    private UserDAO userDAO;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        chkremember = findViewById(R.id.login_chk);
        btnlogin = findViewById(R.id.login_login);
        checkRemember();
        checkLogin();
    }

    private void checkLogin(){
        userDAO = new UserDAO(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String strusname = username.getText().toString().trim();
                String strpw = password.getText().toString().trim();

                userDAO.checkLogin(strusname, strpw, new UserDAO.UserDAOITF() {
                    @Override
                    public void xuli(Object obj) {
                        user = (User) obj;
                        rememberUser(username.getText().toString(), password.getText().toString());
                    }
                });
            }
        });
    }


    private void rememberUser(String username1 , String password1){
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username1 );
        editor.putString("password", password1);
        editor.putBoolean("check", true);
        editor.commit();
    }

    private void checkRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String us = sharedPreferences.getString("username", "");
        String pw = sharedPreferences.getString("password", "");
        boolean check = sharedPreferences.getBoolean("check", false);
        if (check){
            username.setText(us);
            password.setText(pw);
            chkremember.setChecked(true);
        }
    }

    private boolean validate(String str){
        return str == null || str.equals("") || str.length() < 5;
    }
}