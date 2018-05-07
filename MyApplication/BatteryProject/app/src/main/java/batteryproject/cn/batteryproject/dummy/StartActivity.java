package batteryproject.cn.batteryproject.dummy;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import batteryproject.cn.batteryproject.R;

public class StartActivity extends AppCompatActivity {
  private MyCountDownTimer myCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        //取消标题栏继承AppCompatctivity添加下列代码
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        myCountDownTimer= new MyCountDownTimer(1500,1500);
        myCountDownTimer.start();
    }
    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent=new Intent(StartActivity.this,TransitionActivity.class);
            startActivity(intent);
            StartActivity.this.finish();
        }
    }
    protected void onDestroy() {
        if (myCountDownTimer !=null){
            myCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
