package startservicemusicplayer.itcast.cn.startservicemusicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class StartService extends Service {
    private static final String TAG = "MusicService";
    public MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
     return null;
    }

    public void onCreate(){
        play();
        super.onCreate();
    }

    private void play() {
            try {
                if (mediaPlayer ==null){
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource("mnt/sdcard/Music/a.mp3");
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
                } else{
                    int position = getCurrentProgross();
                    mediaPlayer.seekTo(position);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void onDestroy(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public int getCurrentProgross() {
        if (mediaPlayer !=null&mediaPlayer.isPlaying()){
        return mediaPlayer.getCurrentPosition();
        }
        else if(mediaPlayer!=null&(!mediaPlayer.isPlaying())){
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }
}
