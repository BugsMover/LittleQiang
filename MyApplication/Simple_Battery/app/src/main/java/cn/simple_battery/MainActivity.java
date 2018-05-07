package cn.simple_battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private SimpleDraweeView drawview;
    private TextView tv_level;
    private TextView tv_voltage;
    private TextView tv_temperature;
    private TextView tv_temp;
    private TextView tv_chargePlug;
    private TextView tv_type;
    private TextView tv_health;
    private TextView tv_rate;
    private TextView tv_charge_or_discharge;

   private Handler handler=new Handler(){
       public void handleMessage(android.os.Message msg){
           if (msg.what==0){
          tv_rate.setText(getCurrent());
               sendEmptyMessageDelayed(0,1000);
           }else if (msg.what==1){
               try {
                   tv_voltage.setText(Double.parseDouble((readCurrentFile(new File("/sys/class/power_supply/battery/batt_vol"))))/1000+"V");
                   sendEmptyMessageDelayed(1,10000);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }else if (msg.what==2){
               try {
                   tv_voltage.setText(Double.parseDouble((readCurrentFile(new File("/sys/class/power_supply/battery/voltage_now/"))))/1000+"V");
                   sendEmptyMessageDelayed(2,10000);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);//放在加载布局之前
        setContentView(R.layout.activity_mian);
        init();
        //电流
        handler.sendEmptyMessageDelayed(0,1000);

        //创建广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        BatteryReceiver batteryReceiver = new BatteryReceiver();
        registerReceiver(batteryReceiver,intentFilter);

    }
    private void init() {
        //gif图初始化
         drawview = (SimpleDraweeView) findViewById(R.id.main_drawview);
         tv_level = (TextView)findViewById(R.id.tv_level);
         tv_voltage=(TextView)findViewById(R.id.tv_voltage);
         tv_temperature=(TextView)findViewById(R.id.tv_temperature);
         tv_temp=(TextView)findViewById(R.id.tv_Temp);
         tv_type=(TextView)findViewById(R.id.tv_type);
         tv_rate=(TextView)findViewById(R.id.tv_rate);
         tv_health=(TextView)findViewById(R.id.tv_health);
         tv_chargePlug=(TextView)findViewById(R.id.tv_chargePlug);
         tv_charge_or_discharge=(TextView)findViewById(R.id.tv_charge_or_discharge);
    }
    /**
     * 获取当前电流
     */
    private String getCurrent(){
        String result ="无";
        try {
            Class systemProperties = Class.forName("android.os.SystemProperties");
            Method get = systemProperties.getDeclaredMethod("get",String.class);
            String platName = (String) get.invoke(null,"ro.hardware");
            if(platName.startsWith("mt")||platName.startsWith("MT")){
                String filePath = "/sys/class/power_supply/battery/current_now";
                int current =Integer.parseInt(readCurrentFile(new File(filePath)));
                if (current<0){
                    result = String.valueOf(-current/1000)+"mA";
                }else{
                    result = String.valueOf(current/1000)+"mA";
                }
            }else if (platName.startsWith("qcom")){
                String filePath = "/sys/class/power_supply/battery/current_now";
                int current =Integer.parseInt(readCurrentFile(new File(filePath))) ;
                if (current<0){
                    result = String.valueOf(-current/1000)+"mA";
                }else{
                    result = String.valueOf(current/1000)+"mA";
                }
            }else {
                String filePath = "/sys/class/power_supply/battery/current_now";
                int current =Integer.parseInt(readCurrentFile(new File(filePath))) ;
                if (current<0){
                    result = String.valueOf(-current/1000)+"mA";
                }else{
                    result = String.valueOf(current/1000)+"mA";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String readCurrentFile(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();
        String line =null;
        while ((line =reader.readLine())!=null){
            sb.append(line);
        }
        input.close();
        return sb.toString();

    }

    private class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //电量
            int level = intent.getIntExtra("level",0);
            int scale = intent.getIntExtra("scale",100);
            tv_level.setText(((level*100)/scale)+"%");
            //电压
            double voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
            if (voltage==0){
                try {
                    Class systemProperties = null;
                    systemProperties = Class.forName("android.os.SystemProperties");
                    Method get = systemProperties.getDeclaredMethod("get", String.class);
                    String platName = (String) get.invoke(null, "ro.hardware");
                    if (platName.startsWith("mt") || platName.startsWith("MT")) {
                        handler.sendEmptyMessageDelayed(1, 100);
                    } else if (platName.startsWith("qcom")) {
                        handler.sendEmptyMessageDelayed(2, 100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                tv_voltage.setText(voltage / 1000 + "V");
            }
            //温度
            double temperature = intent.getIntExtra("temperature",0);
            tv_temperature.setText(temperature/10+"℃");
            //状态
            switch (intent.getIntExtra("status",BatteryManager.BATTERY_STATUS_UNKNOWN)){
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    tv_temp.setText("正在充电");
                    tv_charge_or_discharge.setText("充电");
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    tv_temp.setText("正在放电");
                    tv_charge_or_discharge.setText("放电");
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    tv_temp.setText("充满电");
                    tv_charge_or_discharge.setText("用电");
                    tv_temp.setTextColor(Color.GREEN);
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    tv_temp.setText("未充电");
                    tv_charge_or_discharge.setText("放电");
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    tv_temp.setText("未知状态");
                    tv_temp.setTextColor(Color.RED);
                    break;
            }
            //电源
            int chargePlug = intent.getIntExtra("plugged",0);
            switch (chargePlug){
                case BatteryManager.BATTERY_PLUGGED_USB:
                    tv_chargePlug.setText("USB充电");
                    DraweeController mDraweeController_2 = Fresco.newDraweeControllerBuilder()
                            .setAutoPlayAnimations(true)
                            //加载drawable里的一张gif图
                            .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.b))//设置uri
                            .build();
                    //设置Controller
                    drawview.setController(mDraweeController_2);
                    break;
                case  BatteryManager.BATTERY_PLUGGED_AC:
                    tv_chargePlug.setText("AC充电");
                    DraweeController mDraweeController_1 = Fresco.newDraweeControllerBuilder()
                            .setAutoPlayAnimations(true)
                            //加载drawable里的一张gif图
                            .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.b))//设置uri
                            .build();
                    //设置Controller
                    drawview.setController(mDraweeController_1);
                    break;
                case 0:
                    tv_chargePlug.setText("无");
                    //gif图
                    DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                            .setAutoPlayAnimations(true)
                            //加载drawable里的一张gif图
                            .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.a))//设置uri
                            .build();
                    //设置Controller
                    drawview.setController(mDraweeController);
            }
            //电池类型
            tv_type.setText(intent.getStringExtra("technology"));
            //健康
            switch (intent.getIntExtra("health",BatteryManager.BATTERY_HEALTH_UNKNOWN)){
                case BatteryManager.BATTERY_HEALTH_COLD:
                    tv_health.setText("温度过低");
                    tv_health.setTextColor(Color.RED);
                    break;
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    tv_health.setText("温度过热");
                    tv_health.setTextColor(Color.RED);
                    break;
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    tv_health.setText("良好");
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    tv_health.setText("No Power");
                    tv_health.setTextColor(Color.RED);
                    break;
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    tv_health.setText("未知错误");
                    tv_health.setTextColor(Color.RED);
                    break;
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,0,0,"退出");
//        menu.add(0,0,1,"关于");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 0:
                System.exit(0);
                break;
//            case 1:
//                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
//                startActivity(intent);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
