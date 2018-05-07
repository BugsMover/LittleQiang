package bindsevice.itcast.cn.bindsevice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
   private MyService.MyBinder myBinder;
    private MyConn myconn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnBind(View view){
        if (myconn ==null){
            myconn = new MyConn();
        }
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,myconn,BIND_AUTO_CREATE);
    }
    public void btnUnbind(View view){
        if(myconn != null){
            unbindService(myconn);
            myconn =null;
        }
    }
    public void btnCall(View view){
        myBinder.callMethodInService();
    }

    private class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            Log.i("MainActivity","服务绑定成功，内存地址为："+myBinder.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
