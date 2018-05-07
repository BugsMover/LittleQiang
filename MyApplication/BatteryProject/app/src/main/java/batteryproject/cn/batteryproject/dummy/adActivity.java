package batteryproject.cn.batteryproject.dummy;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import batteryproject.cn.batteryproject.R;


public class adActivity extends AppCompatActivity {
   private Button button;
   private MyCountDownTimer myCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ad);
        //取消标题栏继承AppCompatctivity添加下列代码
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
            button=(Button)findViewById(R.id.button2);
        myCountDownTimer= new MyCountDownTimer(3500,1000);
       myCountDownTimer.start();
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myCountDownTimer.cancel();
               Intent intent=new Intent(adActivity.this,MainActivity.class);
               startActivity(intent);
               adActivity.this.finish();
           }
       });
    }
   class MyCountDownTimer extends CountDownTimer{

       /**
        * @param millisInFuture    The number of millis in the future from the call
        *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
        *                          is called.
        *                          表示以毫秒为单位倒计时的总数
        *                          例如 millisInFuture = 1000 表示1秒
        * @param countDownInterval The interval along the way to receive
        *                          {@link #onTick(long)} callbacks.
        *                          表示 间隔 多少微秒 调用一次 onTick()
        *      例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
        */
       public MyCountDownTimer(long millisInFuture, long countDownInterval) {
           super(millisInFuture, countDownInterval);
       }

       @Override
       public void onTick(long millisUntilFinished) {
           button.setText(millisUntilFinished/1000+"s秒 跳过");
       }

       @Override
       public void onFinish() {
           Intent intent=new Intent(adActivity.this,MainActivity.class);
           startActivity(intent);
           adActivity.this.finish();
       }
   }
    @Override
    protected void onDestroy(){
        if (myCountDownTimer !=null){
            myCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
