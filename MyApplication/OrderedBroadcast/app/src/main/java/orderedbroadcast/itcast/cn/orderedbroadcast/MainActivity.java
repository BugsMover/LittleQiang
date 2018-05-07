package orderedbroadcast.itcast.cn.orderedbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sned(View view) {
        Intent intent = new Intent();
        intent.setAction("Intercept_Stitch");
        sendOrderedBroadcast(intent,null);
    }
}
