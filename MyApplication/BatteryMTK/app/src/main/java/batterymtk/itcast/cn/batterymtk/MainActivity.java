package batterymtk.itcast.cn.batterymtk;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {
    private static String CHARGER_CURRENT_NOW = "/sys/class/power_supply/battery/current_now";
    private TextView mCurrent;
    //在类里声明一个Handler
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what==0){
            try {
                mCurrent = (TextView) findViewById(R.id.a);
                mCurrent.setText(readCurrentFile(new File(CHARGER_CURRENT_NOW))+"uA");
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendEmptyMessageDelayed(0,1000);
          }
        }
    };

//    public void onResume() {
//        mCurrent = (TextView) findViewById(R.id.a);
//        try {
//            mCurrent.setText(readCurrentFile(new File(CHARGER_CURRENT_NOW))+"uA");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        super.onResume();
//    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在你的onCreate的类似的方法里面启动这个Handler就可以了
        mHandler.sendEmptyMessageDelayed(0,1000);
    }

}