package com.pradeep.newcomcom.call;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pradeep.newcomcom.MainActivity;
import com.pradeep.newcomcom.R;

import java.util.ArrayList;
//Pradeep puri goswami
public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord> {

    // variables
    private  Context context;
    private ArrayList<ModelRecord> recordsList;
    private static String id;

    //constractor
    public AdapterRecord(Context context, ArrayList<ModelRecord> recordsList) {
        this.context = context;
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view= LayoutInflater.from(context).inflate(R.layout.row_record, parent,false);


        return new HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderRecord holder, int position) {
        //get data, set data, handel view clics in this method

        //getdata
        ModelRecord model=recordsList.get(position);
        id=model.getId();
        String phone=model.getPhone();
        String oneName=model.getNameOne();
        String twoName=model.getNameTwo();
        String threeName=model.getNameThree();
        String addedTime=model.getAddedTime();
        String updateTime=model.getUpdatedTime();

        //set data to views
        holder.phoneTv.setText(phone);
        holder.nameOneTv.setText(oneName);
        holder.nameTwoTv.setText(twoName);
        holder.nameThreeTv.setText(threeName);

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
        holder.moreBtnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //latr
                MyDbHelper myDbHelper = new MyDbHelper(context);
                myDbHelper.deleteData(id);
                Intent intent=new Intent(context,MainActivity.class);
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
        TextView nameOneTv,nameTwoTv, nameThreeTv,phoneTv;
        ImageButton moreBtnone;

        public HolderRecord(View item){
            super(item);

            //init views
            //profileIvv=item.findViewById(R.id.profileIvv);
            phoneTv=(TextView)item.findViewById(R.id.phoneTv);
            nameOneTv=(TextView)item.findViewById(R.id.nameOneTv);
            nameTwoTv=(TextView)item.findViewById(R.id.nameTwoTv);
            nameThreeTv=(TextView)item.findViewById(R.id.nameThreeTv);
            moreBtnone=(ImageButton)item.findViewById(R.id.moreBtnone);
        }
    }
}
