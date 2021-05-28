package com.pradeep.newcomcom.background;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.pradeep.newcomcom.R;
import java.util.ArrayList;
import java.util.Locale;
//Pradeep puri goswami
public class A_to_b extends AppCompatActivity {
    private NotificationCompat.Builder nb;
    private NotificationManager notificationManager;
    private int time= 100;
    private static final int REQUEST_CODE_SPEECH_INPUT =1000 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        onStartMethod();
        audioToText();
    }

    private void onStartMethod() {
        nb= new NotificationCompat.Builder(this);
        nb.setContentText("Timer Done.");
        nb.setContentTitle("Hi!");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        //nb.addAction(R.mipmap.ic_launcher,"Start",intent2);
        //nb.setOngoing(true);
        //nb.setOnlyAlertOnce(true);
        nb.setProgress(time,0,false);

        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,nb.build());
    }
    private void audioToText() {
        //intent is show speech to text dialog

        Intent intent= new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);//getDefault() );
        intent.putExtra( RecognizerIntent.EXTRA_PROMPT, "HI speak something" );

        // Start intent
        try {
            //in there was no errror
            //show dilog
            startActivityForResult( intent, REQUEST_CODE_SPEECH_INPUT);
        }

        catch(Exception e){
            //show messageof error and show
            Toast.makeText( this,""+e.getMessage(), Toast.LENGTH_SHORT ).show();
        }
    }  //recive voice input and handle it
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK && null!=data){
                    //get text arry form voice intent
                    ArrayList<String> result=data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
                    //set to text view
                    String string = result.get( 0 ) ;
                    Intent intent1=new Intent(this,B_to_c.class);
                    B_to_c b_to_c=new B_to_c();
                    b_to_c.onCreate(string);
                    startActivity(intent1);
                    Toast.makeText( this,"Audio service complete:"+string, Toast.LENGTH_SHORT ).show();

                }
                break;

            }
        }
    }
}