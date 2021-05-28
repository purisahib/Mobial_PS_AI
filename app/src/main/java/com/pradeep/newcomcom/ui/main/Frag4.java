package com.pradeep.newcomcom.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.pradeep.newcomcom.R;
import com.pradeep.newcomcom.extra.Constants;
import com.pradeep.newcomcom.question_answer.AdapterQuestionAnswer;
import com.pradeep.newcomcom.question_answer.DatabaseSqltwo;
import androidx.fragment.app.Fragment;
//Pradeep puri goswami
//question answer
public class Frag4 extends Fragment {
    private EditText questioEt, answerEt;
    private Button questionAnswerBtn;

    //db helper
    private DatabaseSqltwo databaseSqltwo;
    private RecyclerView questionanswerRecord;
    //actionbar
    private ActionBar actionBar;
    private String question,answer;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag4_layout, container, false);

        //init
        UpdateTitle();
        // inti Data
        questioEt=(EditText)root.findViewById(R.id.questioEt);
        answerEt=(EditText)root.findViewById(R.id.answerEt);
        questionAnswerBtn=(Button) root.findViewById(R.id.questinAnswerBtn);
        //recycler view
        questionanswerRecord=(RecyclerView) root.findViewById(R.id.questionanswerRecord);
        //init db helper
        databaseSqltwo= new DatabaseSqltwo(getContext());

        //seton click listener
        questionAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });
        loadRecords();
        return root;
    }

    private void loadRecords() {
        AdapterQuestionAnswer adapterRecord= new AdapterQuestionAnswer(getContext(),
                databaseSqltwo.getAllRecords(Constants.C_ID + " DESC"));

        questionanswerRecord.setAdapter(adapterRecord);

        //set num of records
        actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar!=null)actionBar.setSubtitle("Total: "+databaseSqltwo.getRecordsCount());
    }

    private void UpdateTitle() {

        if (getActivity() instanceof AppCompatActivity){
            actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
            if (actionBar!=null) actionBar.setTitle("Add Record");
        }
    }

    private void inputData() {
        //get data
        question=""+questioEt.getText().toString().trim();
        answer=""+answerEt.getText().toString().trim();

        //save to db
        String timestamp=""+System.currentTimeMillis();
        long id=databaseSqltwo.insertRecord(
                ""+question,
                ""+answer,
                ""+timestamp,
                ""+timestamp
        );
        Toast.makeText(getContext(), "Record Added against ID: "+id, Toast.LENGTH_SHORT).show();
    }
}
