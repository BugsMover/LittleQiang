package battery.itcast.cn.battery;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private boolean mIsstart=true;
    private Toast mToast;
    private Handler mHandler = new Handler(){
    public void handleMessage(android.os.Message msg){
        showToast(getCurrent());
        if (mIsstart){
            // 因为Toast.LENGTH_SHORT的默认值是2000
            mHandler.sendEmptyMessageDelayed(0,1900);
        }
    }
    };
    /**
     * 获取当前电流
     */
    private String getCurrent(){
        String result ="null";
       try {
           Class systemProperties = Class.forName("android.os.SystemProperties");
           Method get = systemProperties.getDeclaredMethod("get",String.class);
           String platName = (String) get.invoke(null,"ro.hardware");
           if(platName.startsWith("mt")||platName.startsWith("MT")){
           String filePath = "/sys/class/power_supply/battery/device/FG_Battery_CurrentConsumtion";
           result = "当前电流为："+Math.round(getMeanCurrentVal(filePath,5,0)/1000.0f)+"mA";
           result+=",电压为："+readFile("/sys/class/power_supply/battery/batt_vol",0)+"mV";
           }else if (platName.startsWith("qcom")){
               String filePath = "/sys/class/power_supply/battery/current_now";
               int current = Math.round(getMeanCurrentVal(filePath,5,0)/1000.0f);
               int voltage = readFile("/sys/class/power_supply/battery/voltage_now",0)/1000;
               if (current<0){
                   result = "充电电流为："+(-current)+"mA,电压为："+voltage+"mV";
               }else{
                   result ="放电电流为："+current+"mA,电压为"+voltage+"mV";
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private int readFile(String path, int defaultValue) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            int i = Integer.parseInt(bufferedReader.readLine(),10);
            bufferedReader.close();
            return i;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
    /**
     * 获取平均电流值
     * 获取 filePath 文件 totalCount 次数的平均值，每次采样间隔 intervalMs 时间
     */
    private float getMeanCurrentVal(String filePath, int totalCount, int intervalMs) {
        float meanVal = 0.0f;
        if (totalCount<=0){
            return 0.0f;
        }
        for (int i = 0;i<totalCount;i++){
            float f = Float.valueOf(readFile(filePath,0));
            meanVal+=f/totalCount;
            if (intervalMs<=0){
                continue;
            }
            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return meanVal;
    }

    private void showToast(String content) {
        if (mToast==null){
            mToast=Toast.makeText(this,content,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(content);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler.sendEmptyMessage(0);
//        Toast.makeText(this,getCurrent(),Toast.LENGTH_SHORT).show();
    }
    protected  void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
        mIsstart = false;
    }
}
