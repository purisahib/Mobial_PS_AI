package com.pradeep.newcomcom.Apps;

import android.app.Application;
import android.content.pm.PackageInfo;
//Pradeep puri goswami
public class AppData extends Application {

    PackageInfo packageInfo;

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }
}