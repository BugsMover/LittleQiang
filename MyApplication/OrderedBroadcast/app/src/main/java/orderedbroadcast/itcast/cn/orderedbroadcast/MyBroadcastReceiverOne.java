package orderedbroadcast.itcast.cn.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;

public class MyBroadcastReceiverOne extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyBroadcastReceiverOne","自定义的广播接受者One,接受到了广播事件");
    }
}
