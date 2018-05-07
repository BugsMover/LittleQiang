package com.example.asus.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
//    private TextView editText2;
//    private TextView editText4;
    private ProgressBar mprogressBar1;
    private  ProgressBar mprogressBar2;
    private  ProgressBar mprogressBar3;
    private TextView mLifeTV;
    private  TextView mAttackTV;
    private  TextView mSpeedTV;
    private  TextView tv_name;
    private  TextView tv_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show);
        Intent intent = getIntent();
        String mane = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
       tv_name = (TextView) findViewById(R.id.editText2) ;
       tv_password = (TextView) findViewById(R.id.editText4) ;
        tv_name.setText(mane);
        tv_password.setText(password);
        mLifeTV =(TextView) findViewById(R.id.textView13);
        mAttackTV = (TextView) findViewById(R.id.textView15);
        mSpeedTV = (TextView) findViewById(R.id.textView16);
        initProgress();
    }

    private void initProgress() {
        mprogressBar1 = (ProgressBar) findViewById(R.id.progressBar4);
        mprogressBar2 = (ProgressBar) findViewById(R.id.progressBar5);
        mprogressBar3 = (ProgressBar) findViewById(R.id.progressBar6);
       mprogressBar1.setMax(1000);
        mprogressBar2.setMax(1000);
        mprogressBar3.setMax(1000);
    }
    public void click(View view){
        Intent intent = new Intent(this,ShopActivity.class);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(data != null){
            if (requestCode == 1){
                if (resultCode==1){
                    ItemInfo info =
                            (ItemInfo) data.getSerializableExtra("equipment");
                    updataProgress(info);
                }
            }
        }
    }

    private void updataProgress(ItemInfo info) {
        int progress1 = mprogressBar1.getProgress();
        int progress2 = mprogressBar2.getProgress();
        int progress3 = mprogressBar3.getProgress();
        mprogressBar1.setProgress(progress1 +info.getLife());
        mprogressBar2.setProgress(progress2 +info.getAcctack());
        mprogressBar3.setProgress(progress3 +info.getSpeed());
        mLifeTV.setText(mprogressBar1.getProgress()+"");
        mAttackTV.setText(mprogressBar2.getProgress()+"");
        mSpeedTV.setText(mprogressBar3.getProgress()+"");
    }
}
