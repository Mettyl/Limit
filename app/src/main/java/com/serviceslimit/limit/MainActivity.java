package com.serviceslimit.limit;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

//        Intent intent = new Intent();
//        intent.setAction(Settings.ACTION_USAGE_ACCESS_SETTINGS);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//        startActivity(intent);
//

        PackageManager packageManager = null;
        List<ApplicationInfo> apps = new ArrayList<>();
        try {
            packageManager = getPackageManager();
            if (packageManager != null) {
                List<PackageInfo> packs = packageManager.getInstalledPackages(0);
                for (int i = 0; i < packs.size(); i++) {
                    PackageInfo p = packs.get(i);
                    ApplicationInfo a = p.applicationInfo;

                    if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                        continue;
                    }
                    apps.add(a);
                }
            }
        } catch (Exception e1) {
            Log.i("debug", "error: ", e1);
        }


        RecyclerView recyclerView = findViewById(R.id.apps_reycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new AppsAdapter(getApplicationContext(), apps, packageManager));
    }
}
