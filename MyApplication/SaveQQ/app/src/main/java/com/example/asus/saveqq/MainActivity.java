package com.example.asus.saveqq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

import cn.itcast.saveqq.FileSaveQQ;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText etNumber;
    private  EditText etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        initView();
        Map<String,String> userInfo = SPSaveQQ.getUserInfo(this);
        if (userInfo != null){
            etNumber.setText(userInfo.get("number"));
            etPassword.setText(userInfo.get("password"));
    }
    }
    private void initView() {
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        String numeber  = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        boolean isSaveSuccess = SPSaveQQ.saveUserInfo(this,numeber,password);
        if (isSaveSuccess){
        Toast.makeText(this,"!!!!",Toast.LENGTH_SHORT).show();}
    }

    }
