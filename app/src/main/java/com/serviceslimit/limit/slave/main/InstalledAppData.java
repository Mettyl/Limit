package com.serviceslimit.limit.slave.main;

import android.graphics.drawable.Drawable;

public class InstalledAppData {

    private String name;
    private Drawable icon;
    private boolean blocked;

    public InstalledAppData(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
