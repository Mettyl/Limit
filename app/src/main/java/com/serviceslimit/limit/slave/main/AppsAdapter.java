package com.serviceslimit.limit.slave.main;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serviceslimit.limit.R;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppHolder> {

    private Context context;
    private PackageManager packageManager;
    private List<ApplicationInfo> infoList;

    public AppsAdapter(Context context, List<ApplicationInfo> infoList,PackageManager packageManager){
        this.context = context;
        this.infoList = infoList;
        this.packageManager = packageManager;
    }

    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_recycler_row, parent, false);

        return new AppHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppHolder holder, int position) {

        ApplicationInfo info = infoList.get(position);

        holder.icon.setImageDrawable(info.loadIcon(packageManager));
        holder.name.setText((String) packageManager.getApplicationLabel(info));
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }


    class AppHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView icon;

        public AppHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.app_recycler_name_tv);
            icon = itemView.findViewById(R.id.app_recycler_icon_iv);
        }
    }
}
