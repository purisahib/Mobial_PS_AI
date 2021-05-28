package com.pradeep.newcomcom.call;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pradeep.newcomcom.R;
//Pradeep puri goswami
public class AddCalls extends AppCompatActivity {
    private EditText nameOne, nameTwo, nameThree, numberBtn;
    private Button callDetailBtn;

    //db helper
    private MyDbHelper dbHelper;

    //actionbar
    private ActionBar actionBar;
    private String name1,name2,name3,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calls);
        //init
        actionBar=getSupportActionBar();
        //title
        actionBar.setTitle("Add Record");
        //back button
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // inti Data
        nameOne=(EditText)findViewById(R.id.nameOne);
        nameTwo=(EditText)findViewById(R.id.nameTwo);
        nameThree=(EditText)findViewById(R.id.nameThree);
        numberBtn=(EditText)findViewById(R.id.numberBtn);
        callDetailBtn=(Button) findViewById(R.id.callDetailBtn);
        //init db helper
        dbHelper= new MyDbHelper(this);

        //seton click listener
        callDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });
    }


    private void inputData() {
        //get data
        name1=""+nameOne.getText().toString().trim();
        name2=""+nameTwo.getText().toString().trim();
        name3=""+nameThree.getText().toString().trim();
        number =""+numberBtn.getText().toString().trim();

        //save to db
        String timestamp=""+System.currentTimeMillis();
        long id=dbHelper.insertRecord(
                ""+name1,
                ""+name2,
                ""+name3,
                ""+number,
                ""+timestamp,
                ""+timestamp
        );
        Toast.makeText(this, "Record Added against ID: "+id, Toast.LENGTH_SHORT).show();
    }
}
