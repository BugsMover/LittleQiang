package androidapplicationmarket.itcast.cn.androidapplicationmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] names = {"京东商城","QQ","QQ斗地主","新浪微博","天猫","UC浏览器","微信"};
    private int[] icons = {R.drawable.jd,R.drawable.qq,R.drawable.dz,R.drawable.xl,R.drawable.tm,R.drawable.uc,R.drawable.wx};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv);
        MyBaseAdapter myAdapter = new MyBaseAdapter();
        mListView.setAdapter(myAdapter);
    }

    class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this,R.layout.list_item,null);
            TextView mTextView = (TextView) view.findViewById(R.id.item_tv);
            mTextView.setText(names[position]);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            imageView.setBackgroundResource(icons[position]);
            return view;
        }
    }
}
