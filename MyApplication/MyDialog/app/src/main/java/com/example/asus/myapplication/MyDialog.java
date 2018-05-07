package com.example.asus.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by asus on 2017-09-13.
 */

public class MyDialog extends Dialog {
    private String dialogName;
    private TextView tvMsg;
    private Button btnOK;
    private Button btnCancel;

    public MyDialog(@NonNull Context context,String dialogName) {
        super(context);
        this.dialogName = dialogName;

    }
     protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
         setContentView(R.layout.dialoglayout);
         tvMsg = (TextView) findViewById(R.id.textView5);
         btnOK = (Button) findViewById(R.id.but_ok);
         btnCancel = (Button) findViewById(R.id.but_cancel);
         tvMsg.setText(dialogName);
//         btnOK.setOnClickListener(this);
//         btnCancel.setOnClickListener(this);
         btnOK.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View v) {

            }


         });
       // 为"取消"按钮设置点击事件

         btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(getContext(), "小姐姐会再次等你回来的！", Toast.LENGTH_LONG).show();
                dismiss();
               new Thread() {
                   public void run(){
                       try {
                           Thread.sleep(2000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       System.exit(0);
                   }

               }.start();

            }
        });
     }

//    @Override
//    public void onClick(View v) {
//     switch (v.getId()){
//         case R.id.but_ok:
//             break;
//     }
//
//    }
}

