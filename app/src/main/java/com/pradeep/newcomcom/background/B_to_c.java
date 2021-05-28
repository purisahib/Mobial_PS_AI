package com.pradeep.newcomcom.background;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pradeep.newcomcom.Apps.DatabaseSqlone;
import com.pradeep.newcomcom.call.MyDbHelper;
import com.pradeep.newcomcom.question_answer.DatabaseSqltwo;

//Pradeep puri goswami
public class B_to_c extends AppCompatActivity {
    private String yourQuestion;
    private String youranswer;
    //inisilize database
    private DatabaseSqlone databaseSqlone;
    private DatabaseSqltwo databaseSqltwo;
    private MyDbHelper myDbHelper;
    private Towiking_Easy tokingEasy;
    private String ss;
    String query=null;
    //TextToSpeech
    TextToSpeech textToSpeech;
    public void onCreate(String string) {
        query=string;
        onOneMethod();
    }
    private void onOneMethod() {
        databaseSqlone= new DatabaseSqlone(this);
        databaseSqltwo= new DatabaseSqltwo(this);
        myDbHelper= new MyDbHelper(this);
        tokingEasy=new Towiking_Easy();

        //init
                ss="";
                getSendData();
    }
    private void getSendData() {
        if(query.isEmpty()){
            Toast.makeText(this, "query is null...", Toast.LENGTH_SHORT).show();
            //yourQuestion.setError("Please enter Your data...");
            //yourQuestion.requestFocus();
        }
        else if (query!=null){
            String num=myDbHelper.searchData(query);
            if (num.isEmpty()){
            }else {
                callHome(num);
                ss+=num;
            }
            String app=databaseSqlone.searchData(query);
            if (app.isEmpty()){
            }else {
                openApp(app);
                ss+=app;
            }
            String speek=databaseSqltwo.searchData(query);
            if (speek.isEmpty()){
            }else {
                ss+=speek;
            }

            String ask=tokingEasy.getTowking(query);
            if (ask.isEmpty()){
            }else {
                ss+=ask;
            }
            youranswer=ss;
            if(ss!=null){
                saySomeThing(ss);
            }

        }
    }
    public void callHome(String num){
        Uri number =Uri.parse("tel:"+num);
        Intent callIntent= new Intent(Intent.ACTION_DIAL,number);
        if (callIntent.resolveActivity(((AppCompatActivity)this).getPackageManager())!=null){
            startActivity(callIntent);
        }else {
            Toast.makeText(this, "Calling error...", Toast.LENGTH_SHORT).show();
        }
    }
    public void openApp(String app){
        Intent launchIntent=((AppCompatActivity)this).getPackageManager().getLaunchIntentForPackage(app);
        if (launchIntent!=null){
            startActivity(launchIntent);
        }else {
            Toast.makeText(this, "app not start... ", Toast.LENGTH_SHORT).show();
        }
    }
    private void saySomeThing(String toSpek){
        if(toSpek.equals( "" )){
        }
        else
        {
            Toast.makeText( this,toSpek,Toast.LENGTH_SHORT ).show();
            //Speak the text
            textToSpeech.speak( toSpek, TextToSpeech.QUEUE_FLUSH,null );
        }
    }
    @Override
    public void onPause() {
        if(textToSpeech!=null || textToSpeech.isSpeaking()){
            //if is speaking the stop
            textToSpeech.stop();
            //mTTS.shutdown();
        }
        super.onPause();
    }
}

