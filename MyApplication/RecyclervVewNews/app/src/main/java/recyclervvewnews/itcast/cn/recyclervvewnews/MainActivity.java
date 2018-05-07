package recyclervvewnews.itcast.cn.recyclervvewnews;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;
import org.apache.http.Header;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * android studio 3.0以上Recyclerview jar是com.android.support:recyclerview-v7:27.0.1
 *应改为com.android.support:recyclerview-v7:26.0.0-alpha1不然会报错
 */
public class MainActivity extends AppCompatActivity {
    private LinearLayout loading;
    private RecyclerView recyclerView;
    private List<NewsInfo> newsInfos;
    private NewsInfo newsInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillDate();
    }
    private void initView() {
        loading = (LinearLayout) findViewById(R.id.loading);
        recyclerView = (RecyclerView) findViewById(R.id.Re_news);
    }
    private void fillDate() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getString(R.string.serverurl), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String json =null;
                try {
                    json =new String(bytes,"utf-8");
                    newsInfos =JsonParse.getNewsInfo(json);
                    if(newsInfos==null){
                        Toast.makeText(MainActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    }else {
                        loading.setVisibility(View.INVISIBLE);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        HomeAdapter adapter =new HomeAdapter();
                        recyclerView.setAdapter(adapter);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.news_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        newsInfo =newsInfos.get(position);
        holder.siv.setImageUrl(newsInfo.getIcon(),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        //设置新闻标题
        holder.tv_title.setText(newsInfo.getTitle());
        //设置新闻描述
        holder.tv_description.setText(newsInfo.getContent());
        //1.一般新闻 2.专题 3.live
        int type = newsInfo.getType();
        switch (type){
            //不同新闻类型设置不同的颜色和不同的内容
            case 1:
                holder.tv_type.setText("评论："+newsInfo.getComment());
                break;
            case 2:
                holder.tv_type.setTextColor(Color.RED);
                holder.tv_type.setText("专题");
                break;
            case 3:
                holder.tv_type.setTextColor(Color.BLUE);
                holder.tv_type.setText("LIVE");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return newsInfos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private TextView tv_description;
        private TextView tv_type;
        private SmartImageView siv;
        public MyViewHolder(View view) {
            super(view);
            siv= (SmartImageView) view.findViewById(R.id.siv_icon);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description =(TextView) view.findViewById(R.id.tv_description);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
        }
    }
}
}
