package com.example.asus.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.myapplication.R;

class MainActivity extends AppCompatActivity {
         private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final MyDialog myDialog = new MyDialog(this,"警告小姐姐，按确定进入！");
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener((new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                       myDialog.show();
//                        new Thread(){
//                            public void run(){
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                ok = (Button) findViewById(R.id.but_ok);
//                                ok.setOnClickListener(new View.OnClickListener(){
//
//                                    @Override
//                                    public void onClick(View v) {
//                                        Intent intent = new Intent();
//                                        intent.setAction("android.intent.action.VIEW");
//                                        intent.setData(Uri.parse("www.baidu.com"));
//                                        startActivity(intent);
//                                    }
//                                });
//                            }
//                        }.start();


                    }
                })
        );

    }

}
