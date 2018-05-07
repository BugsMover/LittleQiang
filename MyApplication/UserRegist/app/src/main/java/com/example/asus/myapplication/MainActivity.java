package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private EditText password;
    private Button but_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText) ;
        password = (EditText) findViewById(R.id.editText3);
        but_send = (Button) findViewById(R.id.button);
        but_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passDate();
            }




        });
    }
    private void passDate() {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("name", editText.getText().toString().trim());
        intent.putExtra("password", password.getText().toString().trim());
        startActivity(intent);
    }
}
