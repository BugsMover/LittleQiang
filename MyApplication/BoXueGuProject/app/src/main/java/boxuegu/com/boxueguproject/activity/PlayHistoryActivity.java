package boxuegu.com.boxueguproject.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import boxuegu.com.boxueguproject.R;
import boxuegu.com.boxueguproject.adapter.PlayHistoryAdapter;
import boxuegu.com.boxueguproject.bean.VideoBean;
import boxuegu.com.boxueguproject.utils.AnalysisUtils;
import boxuegu.com.boxueguproject.utils.DBUtils;

public class PlayHistoryActivity extends AppCompatActivity {
   private TextView tv_main_title,tv_back,tv_none;
   private RelativeLayout rl_title_bar;
   private ListView lv_list;
   private PlayHistoryAdapter adapter;
   private List<VideoBean> vbl;
   private DBUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db= DBUtils.getInstance(this);
        vbl=new ArrayList<VideoBean>();
        vbl=db.getVideoHistory(AnalysisUtils.readLoginUserName(this));
        init();
    }

    /**
     * 初始化UI控件
     */
    private void init() {
        tv_main_title=(TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("播放记录");
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back=(TextView)findViewById(R.id.tv_back);
        lv_list=(ListView)findViewById(R.id.lv_list);
        tv_none=(TextView)findViewById(R.id.tv_none);
        if (vbl.size()==0){
            tv_none.setVisibility(View.VISIBLE);
        }
        adapter=new PlayHistoryAdapter(this);
        adapter.setData(vbl);
        lv_list.setAdapter(adapter);
        //后退键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayHistoryActivity.this.finish();
            }
        });
    }
}
