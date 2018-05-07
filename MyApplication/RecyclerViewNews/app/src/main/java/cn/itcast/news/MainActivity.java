package cn.itcast.news;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout loading;
    private RecyclerView recycleView;
//  private ListView lvNews;
    private List<NewsInfo> newsInfos;
    private TextView tv_title;
    private TextView tv_description;
    private TextView tv_type;
    private NewsInfo newsInfo;
    private SmartImageView siv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillDate();
    }
    private void initView() {
        loading = (LinearLayout) findViewById(R.id.loading);
//      lvNews = (ListView) findViewById(R.id.lv_news);
        recycleView= (RecyclerView) findViewById(R.id.id_recycleview);
    }


    private void fillDate() {
        AsyncHttpClient client = new AsyncHttpClient();
       client.get(getString(R.string.serverurl), new AsyncHttpResponseHandler() {
           @Override
           public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
               String json = null;
               try {
                   json = new String(bytes,"utf-8");
                   newsInfos = JsonParse.getNewInfo(json);
                   if (newsInfos == null){
                       Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                   }else{
                       loading.setVisibility(View.INVISIBLE);
//                       lvNews.setAdapter(new NewsAdapter());
                       recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                       HomeAdapter adapter=new HomeAdapter();
                       recycleView.setAdapter(adapter);
                   }
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }

           }

           @Override
           public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
               Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
           }
       });
    }
//    private class NewsAdapter extends BaseAdapter{
//
//        @Override
//        public int getCount() {
//            return newsInfos.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertview, ViewGroup parent) {
//            View view = View.inflate(MainActivity.this,R.layout.news_item,null);
//            siv = (SmartImageView) view.findViewById(R.id.siv_icon);
//            tv_title = (TextView) view.findViewById(R.id.tv_title);
//            tv_description = (TextView) view.findViewById(R.id.tv_description);
//            tv_type = (TextView) view.findViewById(R.id.tv_type);
//            newsInfo = newsInfos.get(position);
//
//            siv.setImageUrl(newsInfo.getIcon(),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
//            tv_title.setText(newsInfo.getTitle());
//            tv_description.setText(newsInfo.getTitle());
//            int type = newsInfo.getType();
//            switch (type){
//                case 1:
//                    tv_type.setText("评论：" + newsInfo.getComment() );
//                    break;
//                case 2:
//                    tv_type.setTextColor(Color.RED);
//                    tv_type.setText("专题");
//                    break;
//                case 3:
//                    tv_type.setTextColor(Color.BLUE);
//                    tv_type.setText("LIVE");
//                    break;
//            }
//
//            return view;
//        }
//    }
class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.news_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;

    }
//将数据与界面进行绑定
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        newsInfo = newsInfos.get(position);
        holder.siv.setImageUrl(newsInfo.getIcon(),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        holder.tv_title.setText(newsInfo.getTitle());
        holder.tv_description.setText(newsInfo.getContent());
        int type = newsInfo.getType();
        switch (type) {
            case 1:
                holder.tv_type.setText("评论:" + newsInfo.getComment());
                break;
            case 2:
                holder.tv_type.setTextColor(Color.RED);
                holder.tv_type.setText("专题");
                break;
            case 3:
                holder.tv_type.setTextColor(Color.BLUE);
                holder.tv_type.setText("LIVE");
                break;
        }
    }
//    获取数据的数量
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
            siv = (SmartImageView) view.findViewById(R.id.siv_icon);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            }
        }
    }
}
