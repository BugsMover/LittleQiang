package com.example.asus.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends  AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
    }
}
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private Button button3;
//    private Button button2;
//    private Button button4;
//    private Button button5;
//    private Button button6;

//   @Override
//   protected void onCreate(Bundle savedInstanceState) {
//       super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout);
//       button2 = (Button) findViewById(R.id.button2);
//       button3 = (Button) findViewById(R.id.button3);
//       button4 = (Button) findViewById(R.id.button4);
//       button5 = (Button) findViewById(R.id.button5);
//        button6 = (Button) findViewById(R.id.button6);
//       button6.setOnClickListener(this);
//        button5.setOnClickListener(this);
//        button4.setOnClickListener(this);
//        button3.setOnClickListener(this);
//       button2.setOnClickListener(this);
//
//    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button2:
//                button2.setText("!");
//                Toast.makeText(this,"!!!!!!",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.button3:
//                button3.setText("!");
//                break;
//            case R.id.button4:
//                button4.setText("!");
//                break;
//            case R.id.button5:
//                button5.setText("!");
//                break;
//            case R.id.button6:
//                button6.setText("!");
//                break;
//        }
//    }
//}
