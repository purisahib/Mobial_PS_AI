package com.pradeep.newcomcom.question_answer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pradeep.newcomcom.Apps.DatabaseSqlone;
import com.pradeep.newcomcom.MainActivity;
import com.pradeep.newcomcom.R;

import java.util.ArrayList;
//Pradeep puri goswami
public class AdapterQuestionAnswer extends RecyclerView.Adapter<AdapterQuestionAnswer.HolderRecord> {

    // variables
    private Context context;
    private ArrayList<ModelRecordtwo> recordsList;

    //constractor
    public AdapterQuestionAnswer(Context context, ArrayList<ModelRecordtwo> recordsList) {
        this.context = context;
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view= LayoutInflater.from(context).inflate(R.layout.questionanswer_record, parent,false);


        return new HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder, int position) {
        //get data, set data, handel view clics in this method

        //getdata
        ModelRecordtwo model=recordsList.get(position);
        final String id=model.getId();
        String question=model.getQuestion();
        String answer=model.getAnswer();
        String addedTime=model.getAddedTime();
        String updateTime=model.getUpdatedTime();

        //set data to views
        holder.questionTv.setText(question);
        holder.answerTv.setText(answer);

        //handel item clicks (go to detail activites)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent= new Intent(context,RecordDetailActivity.class);
                //intent.putExtra("RECORD_ID",id);
                //context.startActivity(intent);
            }
        });

        //handel more button click listener (show option like edtit, delete et)
        holder.moreBtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //latr
                DatabaseSqltwo databaseSqlTwo = new DatabaseSqltwo(context);
                databaseSqlTwo.deleteData(id);
                Intent intent=new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordsList.size();// return size of list/ number or records
    }

    class HolderRecord extends RecyclerView.ViewHolder{
        //Views
        //ImageView profileIvv;
        TextView questionTv,answerTv;
        ImageButton moreBtnThree;

        public HolderRecord(View item){
            super(item);

            //init views
            //profileIvv=item.findViewById(R.id.profileIvv);
            questionTv=(TextView)item.findViewById(R.id.questionTv);
            answerTv=(TextView)item.findViewById(R.id.answerTv);
            moreBtnThree=(ImageButton)item.findViewById(R.id.morebtnThree);
        }
    }
}

