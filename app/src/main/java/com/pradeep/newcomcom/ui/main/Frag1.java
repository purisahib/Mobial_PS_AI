package com.pradeep.newcomcom.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.pradeep.newcomcom.Apps.DatabaseSqlone;
import com.pradeep.newcomcom.R;
import com.pradeep.newcomcom.background.Backgroundworkstart;
import com.pradeep.newcomcom.background.Start_Audio_To_Text;
import com.pradeep.newcomcom.background.Towiking_Easy;
import com.pradeep.newcomcom.call.MyDbHelper;
import com.pradeep.newcomcom.question_answer.DatabaseSqltwo;

import java.util.ArrayList;
import java.util.Locale;
//Pradeep puri goswami
public class Frag1 extends Fragment {
    private EditText yourQuestion;
    private TextView youranswer;
    //inisilize database
    private DatabaseSqlone databaseSqlone;
    private DatabaseSqltwo databaseSqltwo;
    private MyDbHelper myDbHelper;
    private Towiking_Easy tokingEasy;
    private String ss;
    Button start;
    String query=null;
    //TextToSpeech
    TextToSpeech textToSpeech;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag1_layout, container, false);
        //instlize classes
        databaseSqlone= new DatabaseSqlone(getContext());
        databaseSqltwo= new DatabaseSqltwo(getContext());
        myDbHelper= new MyDbHelper(getContext());
        tokingEasy=new Towiking_Easy();

        //init
        yourQuestion=(EditText)root.findViewById(R.id.yourQuestion);
        youranswer=(TextView) root.findViewById(R.id.yourAnswer);
        Button answerButton=(Button)root.findViewById(R.id.answerButton);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ss="";
                transferData();
                getSendData();
            }
        });
        textToSpeech= new TextToSpeech(((AppCompatActivity)getActivity()).getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    //if there is no error
                    textToSpeech.setLanguage( Locale.ENGLISH);

                }else
                {
                    Toast.makeText( getContext(),"Error",Toast.LENGTH_SHORT ).show();
                }
            }

        } );
        Button start=(Button)root.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Start_Audio_To_Text.class);
                startActivity(intent);
            }
        });
        //Intent intent = new Intent(getContext(), Backgroundworkstart.class);
        //intent.putExtra("timer",100);
        //intent.putExtra("reciver",reciver);
        //startService(intent);
        return root;
    }

    private void transferData() {
        query=yourQuestion.getText().toString().trim();
    }
    private void getSendData() {
        if(query.isEmpty()){
            yourQuestion.setError("Please enter Your data...");
            yourQuestion.requestFocus();
        }
        else {
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
            ss+=databaseSqltwo.searchData(query);
            youranswer.setText(ss);
            if(ss!=null){
                saySomeThing(ss);
            }
        }
    }
    public void callHome(String num){
        Uri number =Uri.parse("tel:"+num);
        Intent callIntent= new Intent(Intent.ACTION_DIAL,number);
        if (callIntent.resolveActivity(((AppCompatActivity)getActivity()).getPackageManager())!=null){
            startActivity(callIntent);
        }else {
            Toast.makeText(getContext(), "Calling error...", Toast.LENGTH_SHORT).show();
        }
    }
    public void openApp(String app){
        Intent launchIntent=((AppCompatActivity)getActivity()).getPackageManager().getLaunchIntentForPackage(app);
        if (launchIntent!=null){
        startActivity(launchIntent);
        }else {
        Toast.makeText(getContext(), "app not start... ", Toast.LENGTH_SHORT).show();
        }
    }


    private void saySomeThing(String toSpek){
        if(toSpek.equals( "" )){
            // Thete is no text in edit text
            //Toast.makeText( getContext(),"Please enter text...",Toast.LENGTH_SHORT ).show();
        }
        else
        {
            Toast.makeText( getContext(),toSpek,Toast.LENGTH_SHORT ).show();
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
