package sendone.itcast.cn.sendone;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private TextView textView;
   private Button button;
   private int i=0;
   private Handler mHandler = new Handler(){
       public void handleMessage(android.os.Message msg){
           textView = (TextView) findViewById(R.id.textView);
           textView.setText(i);
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler.sendEmptyMessage(0);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<=99){
                i = i+1;
             Log.i("数数",""+i);
                }
//                textView.setText(i);
            }
        });

        }

}
