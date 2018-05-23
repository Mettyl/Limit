package com.serviceslimit.limit.slave.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.serviceslimit.limit.R;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppHolder> {

    private Context context;
    private List<InstalledAppData> appDataList;

    public AppsAdapter(Context context, List<InstalledAppData> appList){
        this.context = context;
        this.appDataList = appList;
    }

    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_recycler_row, parent, false);

        return new AppHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppHolder holder, int position) {

        final InstalledAppData appData = appDataList.get(position);

        holder.name.setText(appData.getName());
        holder.icon.setImageDrawable(appData.getIcon());
        holder.aSwitch.setOnCheckedChangeListener(null);
        holder.aSwitch.setChecked(appData.isBlocked());

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                appData.setBlocked(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appDataList.size();
    }

    public ArrayList<String> getCheckedPackages() {
        ArrayList<String> list = new ArrayList<>();
        for (InstalledAppData a : appDataList) {
            if (a.isBlocked()) {
                list.add(a.getPackageName());
            }
        }

        return list;
    }


    class AppHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView icon;
        private Switch aSwitch;

        public AppHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.app_recycler_name_tv);
            icon = itemView.findViewById(R.id.app_recycler_icon_iv);
            aSwitch = itemView.findViewById(R.id.app_recycler_switch);
        }
    }
}
