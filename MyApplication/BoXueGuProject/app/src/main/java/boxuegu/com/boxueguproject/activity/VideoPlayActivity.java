package boxuegu.com.boxueguproject.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import boxuegu.com.boxueguproject.R;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController controller;
    private String videoPath;//本地视频地址
    private int position;//传递视频详细界面点击的视频
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        //设置此界面为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //获取从播放记录界面传递过来的视频地址
        videoPath=getIntent().getStringExtra("videoPath");
        position=getIntent().getIntExtra("position",0);
        init();
    }

    /**
     * 初始化UI控件
     */
    private void init() {
        videoView=(VideoView)findViewById(R.id.videoView);
        controller=new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }

    /**
     * 播放视频
     */
    private void play() {
        if(TextUtils.isEmpty(videoPath)){
            Toast.makeText(this,"本地没有此视频，暂无法播放",Toast.LENGTH_SHORT).show();
            return;
        }
        String uri="androif.resource://"+getPackageName()+"/"+R.raw.video11;
        videoView.setVideoPath(uri);
        videoView.start();
    }
    @Override
    public boolean onKeyDown(int ketCode,KeyEvent event){
        //爸视频详细界面传递过来的被点击视频的位置传递回去
        Intent data=new Intent();
        data.putExtra("position",position);
        setResult(RESULT_OK,data);
        return super.onKeyDown(ketCode,event);
    }
}
