package batterymanager.itcast.cn.batterymanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView2;
    private TextView textView;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册广播接受者java代码
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //创建广播接受者对象
        BatteryReceiver batteryReceiver = new BatteryReceiver();
        //注册receiver
        registerReceiver(batteryReceiver,intentFilter);

    }
    class BatteryReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //电量 BatteryManager.EXTRA_LEVEL
            int level = intent.getIntExtra("level",0);
            int scale = intent.getIntExtra("scale",100);
            textView = (TextView) findViewById(R.id.textView);
            textView.setText("电量："+((level*100)/scale)+"%");
            //电压 "voltage"
            double voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
            textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText("电压:"+voltage/1000+"V");
            //温度
            double temperature = intent.getIntExtra("temperature",0);
            textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText("温度："+temperature/10+"℃");
            //健康 充电状态
            String BatterryTemp = null;
            switch (intent.getIntExtra("status",BatteryManager.BATTERY_STATUS_UNKNOWN)){
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    BatterryTemp = "正在充电";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    BatterryTemp = "正在放电";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    BatterryTemp = "充满电";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    BatterryTemp ="未充电";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    BatterryTemp = "未知状态";
            }

            String BatterryTemp1 = null;
            switch (intent.getIntExtra("health",BatteryManager.BATTERY_HEALTH_UNKNOWN)){
                case BatteryManager.BATTERY_HEALTH_COLD:
                    BatterryTemp1 = "温度过低";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    BatterryTemp1 = "温度过热";
                    break;
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    BatterryTemp1 = "良好";
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    BatterryTemp1 = "No Power";
                    break;
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    BatterryTemp1 = "未知错误";
                    break;
            }
            textView7 = (TextView) findViewById(R.id.textView7);
            textView7.setText("电池状态:"+BatterryTemp1);

            textView4 = (TextView) findViewById(R.id.textView4);
            textView4.setText("电池:"+BatterryTemp);
            //充电方式
            textView5 = (TextView) findViewById(R.id.textView5);
            String BatterryTemp2 = null;
            int chargePlug = intent.getIntExtra("plugged",0);
            switch (chargePlug){
                case BatteryManager.BATTERY_PLUGGED_USB:
                    BatterryTemp2="USB充电";
                    break;
                case  BatteryManager.BATTERY_PLUGGED_AC:
                    BatterryTemp2="AC充电";
                    break;
                case 0:
                    BatterryTemp2 = "无";
            }
            textView5.setText("充电方式："+BatterryTemp2);
            textView6 = (TextView) findViewById(R.id.textView6);
            textView6.setText("使用的技术:"+intent.getStringExtra("technology"));
        }
    }
}
