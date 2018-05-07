package boxuegu.com.boxueguproject.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import boxuegu.com.boxueguproject.R;
import boxuegu.com.boxueguproject.utils.MD5Utils;
public class RegisterActivity extends AppCompatActivity {
  private TextView tv_main_title;   //标题
    private TextView tv_back; //返回按钮
    private Button btn_register; //注册按钮
    private EditText et_user_name,et_psw_again,et_psw;//用户名、密码、再次输入的密码的控件
    private  String userName,psw,pswAgain;//用户名、密码、再次输入的密码的控件的获取值
    private RelativeLayout rl_title_bar;//标题布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        //从main_title_bar.xml页面布局中获取得对应的UI控件
        tv_main_title=(TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back =(TextView)findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        //从activity_register.xml页面布局中获取对应的UI控件
        btn_register=(Button)findViewById(R.id.btn_register);
        et_user_name=(EditText)findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_psw_again=(EditText) findViewById(R.id.et_psw_again);
        tv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在响应控件中的字符串
                getEditString();
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"输入两次的密码不一样",Toast.LENGTH_SHORT).show();
                    return;
                }else if (isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this,"此账号名以存在",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    //把密码和用户名保存到SharePreferences仲
                    saveRegisterInfo(userName,psw);
                    //注册成功后吧用户名传送到LoginActivity.java中
                    Intent data = new Intent();
                    data.putExtra("userName",userName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    /**
     * 保存用户名和密码到sharedPreferences中
     * @param userName
     * @param psw
     */
    private void saveRegisterInfo(String userName, String psw) {
    String md5Psw = MD5Utils.md5(psw);//吧密码用MD5加密
        //loginInfo表示文件名
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        //以用户名为key，密码为value保存到sharedpreferences仲
        editor.putString(userName,md5Psw);
        editor.commit();//提交修改
    }

    /**
     * 从SharefPreferences中读取输入的用户名，并判断此用户名是否存在
     * @param userName
     * @return
     */
    private boolean isExistUserName(String userName) {
        boolean has_userName=false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            has_userName=true;
        }
        return has_userName;
    }

    /**
     * 获取控件中的字符串
     */
    private void getEditString() {
        userName = et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain =et_psw_again.getText().toString().trim();
    }
}
