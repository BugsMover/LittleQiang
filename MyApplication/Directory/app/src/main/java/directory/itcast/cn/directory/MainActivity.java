package directory.itcast.cn.directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    MyHelper myHelper;
    private EditText mEtName;
    private EditText mEPhone;
    private TextView mTvShow;
    private Button mBtnAdd;
    private Button mBtnQuery;
    private Button mBtnUpdata;
    private Button mBtnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        init();

    }

    private void init() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEPhone = (EditText) findViewById(R.id.et_phone);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnUpdata = (Button) findViewById(R.id.btn_updata);
        mBtnAdd.setOnClickListener(this);
        mBtnUpdata.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name;
        String phone;
        SQLiteDatabase db;
        ContentValues values;
        boolean flact;
        switch (v.getId()){
            case R.id.btn_add:
                name = mEtName.getText().toString();
                phone = mEPhone.getText().toString();
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                db.insert("information",null,values);
                Toast.makeText(this,"信息已添加",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_query:
                db = myHelper.getReadableDatabase();
                Cursor cursor = db.query("information",null,null,null,null,null,null);
                if(cursor.getCount() ==0){
                    mTvShow.setText("");
                    Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
                }else{
                    cursor.moveToFirst();
                    mTvShow.setText("Name:"+cursor.getString(1)+"； Tel："+ cursor.getString(2));
                }while(cursor.moveToNext()){
                mTvShow.append("\n" + "Name:"+cursor.getString(1)+";Tel:"+cursor.getString(2));
            }
                cursor.close();
                db.close();
                break;
            case R.id.btn_updata:
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("phone",phone = mEPhone.getText().toString());
                db.update("information",values,"name=?",new String[]{mEtName.getText().toString()});
                Toast.makeText(this,"信息已修改",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_delete:
                db = myHelper.getWritableDatabase();
                if(flact = true) {
                    db.delete("information", "name=?", new String[]{mEtName.getText().toString()});
                    db.delete("information", "phone=?", new String[]{mEPhone.getText().toString()});
                }
                Toast.makeText(this,"信息已删除",Toast.LENGTH_SHORT).show();
                mTvShow.setText("");
                db.close();
                break;
        }
    }

     class MyHelper extends SQLiteOpenHelper{
         public MyHelper(Context context) {
             super(context,"itcast.db",null,1);
         }


         @Override
         public void onCreate(SQLiteDatabase db) {
             db.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20))");
         }

         @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

         }
     }
}
