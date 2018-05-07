package imageview.itcast.cn.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
     protected static final int CHANGE_UI=1;
     protected static final int ERROR=2;
     private EditText et_path;
     private ImageView ivPic;
     private Handler handler=new Handler(){
         public void handleMessage(Message msg){
             if (msg.what==CHANGE_UI){
                 Bitmap bitmap=(Bitmap) msg.obj;
                 ivPic.setImageBitmap(bitmap);
             }else if (msg.what==ERROR){
                 Toast.makeText(MainActivity.this,"显示图像错误",Toast.LENGTH_SHORT).show();
             }
         }
     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path=(EditText) findViewById(R.id.et_path);
        ivPic=(ImageView)findViewById(R.id.iv_pic);
    }
    public void click(View view) {
        final String path=et_path.getText().toString().trim();
        if (TextUtils.isEmpty(path)){
            Toast.makeText(this,"图像路径不能为空",Toast.LENGTH_SHORT).show();
        }else {
            //子线程请求网络，Android4.0以后访问网络不能放在主线
            new Thread(){
              private HttpURLConnection conn;
              private Bitmap bitmap;
              public void run(){
                  //连接服务器GET请求获取图片
                  try {
                  //创建URL对象
                      URL url = new URL(path);
                      //根据URL发送HTTP的请求
                      conn =(HttpURLConnection) url.openConnection();
                      //设置请求方式
                      conn.setRequestMethod("GET");
                      //设置超时时间
                      conn.setConnectTimeout(5000);
                      //得到服务器返回的响应
                      int code =conn.getResponseCode();
                      //请求网络成功返回码是200
                      if(code==200){
                          //获取输入流
                          InputStream is =conn.getInputStream();
                          //将转换成Bitmap对象
                          bitmap= BitmapFactory.decodeStream(is);
                          //将更改主界面的消息发送给主线程
                          Message msg=new Message();
                          msg.what=CHANGE_UI;
                          msg.obj=bitmap;
                          handler.sendMessage(msg);
                      }else {
                          //返回不等于200请求服务器失败
                          Message msg = new Message();
                          msg.what = ERROR;
                          handler.sendMessage(msg);
                      }
                  } catch (java.io.IOException e) {
                      e.printStackTrace();
                      Message msg=new Message();
                      msg.what=ERROR;
                      handler.sendMessage(msg);
                  }
                  //关闭连接
                  conn.disconnect();
              }
            }.start();
        }
    }
}
