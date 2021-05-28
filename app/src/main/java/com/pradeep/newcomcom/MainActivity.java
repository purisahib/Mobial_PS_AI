package com.pradeep.newcomcom;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pradeep.newcomcom.background.Backgroundworkstart;
import com.pradeep.newcomcom.background.MessageReciver;
import com.pradeep.newcomcom.ui.main.Frag3;
import com.pradeep.newcomcom.ui.main.SectionsPagerAdapter;
//Pradeep puri goswami
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //service
        MessageReciver reciver= new MessageReciver(new Message());
        Intent intent1 = new Intent(MainActivity.this, Backgroundworkstart.class);
        intent1.putExtra("timer",100);
        intent1.putExtra("reciver",reciver);
        startService(intent1);
        // another
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App restart....", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
    public class Message{
        public void displayMessage(int resultCode, Bundle resultData){
            String message= resultData.getString("message");
            Toast.makeText(MainActivity.this, resultCode+" "+message, Toast.LENGTH_SHORT).show();
        }
    }
}