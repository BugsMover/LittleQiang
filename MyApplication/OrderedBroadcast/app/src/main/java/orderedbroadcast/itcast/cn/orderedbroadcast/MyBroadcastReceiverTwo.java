package orderedbroadcast.itcast.cn.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by asus on 2017-10-30.
 */

public class MyBroadcastReceiverTwo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadcastReceiverOne","自定义的广播接受者Two,接受到了广播事件");
    }
}
