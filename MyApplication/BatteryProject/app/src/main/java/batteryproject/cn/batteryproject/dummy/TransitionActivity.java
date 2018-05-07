package batteryproject.cn.batteryproject.dummy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import batteryproject.cn.batteryproject.R;

public class TransitionActivity extends AppCompatActivity {
     Boolean isFirstIn;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        SharedPreferences sharedPreferences =getSharedPreferences("is_first_in_data",MODE_PRIVATE);
        isFirstIn =sharedPreferences.getBoolean("isFirstIn",true);
                if (isFirstIn){
                    intent =new Intent(TransitionActivity.this,FragmentActivity.class);
                    startActivity(intent);
                    TransitionActivity.this.finish();
                }else{
                    intent=new Intent(TransitionActivity.this,adActivity.class);
                    startActivity(intent);
                    TransitionActivity.this.finish();
                }
    }
}
