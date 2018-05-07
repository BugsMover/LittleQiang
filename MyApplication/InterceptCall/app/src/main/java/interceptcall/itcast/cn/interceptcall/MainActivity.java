package interceptcall.itcast.cn.interceptcall;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_ipnumber;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ipnumber=(EditText) findViewById(R.id.et_ipnumber);
        sp=getSharedPreferences("config",MODE_PRIVATE);
    }
    public void click(View view){
        String number = et_ipnumber.getText().toString().trim();
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("number",number);
        editor.commit();
        Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
    }
}
