package com.serviceslimit.limit.slave.block;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.serviceslimit.limit.R;
import com.serviceslimit.limit.slave.main.MainActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.serviceslimit.limit.slave.main.MainActivity.TAG;


public class BlockingService extends Service {

    public int loopSpeedInMillies = 500;
    private boolean started = false;
    private Handler handler = new Handler();
    private List<String> packages;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            String s = getForegroundApp();

            if (packages != null) {
                for (String p : packages) {
                    if (s.equals(p)) {
                        showBlockingScreen();
                    }
                }
            }
            if (started) {
                start();
            }
        }
    };

    public BlockingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service started.");

        if (intent != null) {
            packages = intent.getStringArrayListExtra("info");
        } else {
            Log.d(TAG, "Intent is null, no app blocked");
        }
        start();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification =
                new Notification.Builder(this)
                        .setContentTitle("Limit")
                        .setContentText("Controling usage")
                        .setSmallIcon(R.drawable.lock_icon)
                        .setContentIntent(pendingIntent)
                        .setTicker("Limit")
                        .build();

        startForeground(1, notification);


        return START_STICKY;
    }

    /**
     * Stops a handler thread.
     */
    public void stop() {
        started = false;
        handler.removeCallbacks(runnable);
    }

    /**
     * Starts a handler thread for checking currently running app.
     */
    public void start() {
        started = true;
        handler.postDelayed(runnable, loopSpeedInMillies);
    }

    public void showBlockingScreen() {
        Intent intent = new Intent(this, BlockingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    /**
     * This method returns package name of currently running application.
     *
     * @return String - app package name
     */
    public String getForegroundApp() {
        String currentApp = "NULL";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            UsageStatsManager usm = (UsageStatsManager) this.getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);

            long time = System.currentTimeMillis();

            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time);

            if (appList != null && appList.size() > 0) {

                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();

                for (UsageStats usageStats : appList) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (!mySortedMap.isEmpty()) {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        } else {

            ActivityManager am = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            if (am != null) {
                List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
                currentApp = tasks.get(0).processName;
            } else {
                Log.d("debug", "Activity Manager is null");
            }
        }

        return currentApp;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
