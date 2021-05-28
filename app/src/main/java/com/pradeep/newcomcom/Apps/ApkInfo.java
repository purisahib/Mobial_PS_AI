package com.pradeep.newcomcom.Apps;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pradeep.newcomcom.R;
//Pradeep puri goswami
public class ApkInfo extends AppCompatActivity {
    private TextView appLabel, packageName, version, features;
    private TextView permissions, andVersion, installed, lastModify, path;
    PackageInfo packageInfo;
    private FloatingActionButton abbAppDataBtn;
    //db helper
    private DatabaseSqlone databaseSqlone;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_info);
        //init
        actionBar=getSupportActionBar();
        //title
        actionBar.setTitle("Add Record");
        //back button
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        findViewsById();

        AppData appData = (AppData) getApplicationContext();
        packageInfo = appData.getPackageInfo();

        setValues();
        databaseSqlone= new DatabaseSqlone(this);
        abbAppDataBtn=(FloatingActionButton)findViewById(R.id.abbAppDataBtn);
        abbAppDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentabbAppDataBtn=new Intent(ApkInfo.this, AddApps.class);
                addAppsInDatabase();
                startActivity(intentabbAppDataBtn);
            }
        });
    }

    private void addAppsInDatabase() {

        String timestamp=""+System.currentTimeMillis();
        long id=databaseSqlone.insertRecord(
                ""+getPackageManager().getApplicationLabel(packageInfo.applicationInfo),
                ""+packageInfo.packageName,
                ""+packageInfo.applicationInfo.sourceDir,
                ""+setDateFormat(packageInfo.firstInstallTime),
                ""+timestamp
        );
        Toast.makeText(this, "Record Added against ID: "+id, Toast.LENGTH_SHORT).show();

    }

    private void findViewsById() {
        appLabel = (TextView) findViewById(R.id.applabel);
        packageName = (TextView) findViewById(R.id.package_name);
        version = (TextView) findViewById(R.id.version_name);
        features = (TextView) findViewById(R.id.req_feature);
        permissions = (TextView) findViewById(R.id.req_permission);
        andVersion = (TextView) findViewById(R.id.andversion);
        path = (TextView) findViewById(R.id.path);
        installed = (TextView) findViewById(R.id.insdate);
        lastModify = (TextView) findViewById(R.id.last_modify);
    }
    private void setValues() {
        // APP name
        appLabel.setText(getPackageManager().getApplicationLabel(
                packageInfo.applicationInfo));

        // package name
        packageName.setText(packageInfo.packageName);

        // version name
        version.setText(packageInfo.versionName);

        // target version
        andVersion.setText(Integer
                .toString(packageInfo.applicationInfo.targetSdkVersion));

        // path
        path.setText(packageInfo.applicationInfo.sourceDir);

        // first installation
        installed.setText(setDateFormat(packageInfo.firstInstallTime));

        // last modified
        lastModify.setText(setDateFormat(packageInfo.lastUpdateTime));

        // features
        if (packageInfo.reqFeatures != null)
            features.setText(getFeatures(packageInfo.reqFeatures));
        else
            features.setText("-");

        // uses-permission
        if (packageInfo.requestedPermissions != null)
            permissions
                    .setText(getPermissions(packageInfo.requestedPermissions));
        else
            permissions.setText("-");
    }

    @SuppressLint("SimpleDateFormat")
    private String setDateFormat(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        String strDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            strDate = formatter.format(date);
        }
        return strDate;
    }

    // Convert string array to comma separated string
    private String getPermissions(String[] requestedPermissions) {
        String permission = "";
        for (int i = 0; i < requestedPermissions.length; i++) {
            permission = permission + requestedPermissions[i] + ",\n";
        }
        return permission;
    }

    // Convert string array to comma separated string
    private String getFeatures(FeatureInfo[] reqFeatures) {
        String features = "";
        for (int i = 0; i < reqFeatures.length; i++) {
            features = features + reqFeatures[i] + ",\n";
        }
        return features;
    }
}
