package com.pradeep.newcomcom.background;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.pradeep.newcomcom.MainActivity;
import com.pradeep.newcomcom.R;
//Pradeep puri goswami
public class Backgroundworkstart extends IntentService {

    public Backgroundworkstart(){
        super("Timer Services");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("timer","Timer services has started.");

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null){
            int time= 100;
            Intent intent1=new Intent(this, A_to_b.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent intent2= PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder nb= new NotificationCompat.Builder(this);
            nb.setContentText("Timer Done.");
            nb.setContentTitle("Hi!");
            nb.setSmallIcon(R.mipmap.ic_launcher);
            nb.addAction(R.mipmap.ic_launcher,"Start",intent2);
            //nb.setOngoing(true);
            //nb.setOnlyAlertOnce(true);
            nb.setProgress(time,0,false);

            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1,nb.build());

            for (int i=0; i<time; i++){
                Log.v("timer","i (intent is null/offline) = "+i);
                try {
                    Thread.sleep(1000);
                    Intent intent11=new Intent(this,A_to_b.class);
                    //startActivity(intent11);
                    nb.setProgress(time,i,false);
                    notificationManager.notify(2,nb.build());
                }catch (Exception e){

                }
            }

            return;
        }
        ResultReceiver reciver=intent.getParcelableExtra("reciver");
        int time=intent.getIntExtra("timer",0);
        Intent intent1=new Intent(this, A_to_b.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent intent2= PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder nb= new NotificationCompat.Builder(this);
        nb.setContentText("Timer Done.");
        nb.setContentTitle("Hi!");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.addAction(R.mipmap.ic_launcher,"Start",intent2);
        //nb.setOngoing(true);
        //nb.setOnlyAlertOnce(true);
        nb.setProgress(time,0,false);

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3,nb.build());

        for (int i=0; i<time; i++){
            Log.v("timer","i (intent is not null/online) = "+i);
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
        Bundle bundle= new Bundle();
        bundle.putString("message","Counting done...");

        reciver.send(1234,bundle);
    }

}

