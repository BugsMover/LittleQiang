package intentsenddata.cn.intentsenddata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
   private TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tv_show=(TextView)findViewById(R.id.tv_show);
        Intent intent=getIntent();
        if (intent!=null){
            String name=intent.getStringExtra("name");
            String  age=intent.getStringExtra("age");
            tv_show.setText("恭喜您"+name+"！来到这个世界"+age+"年！");
        }

    }
}
