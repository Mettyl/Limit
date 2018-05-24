package com.serviceslimit.limit.slave.block;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.serviceslimit.limit.slave.main.MainActivity.TAG;

public class BlockingBootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: Service restarted after boot.");
        Intent service = new Intent(context, BlockingService.class);
        context.startService(service);
    }
}
