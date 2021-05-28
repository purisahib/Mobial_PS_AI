package com.pradeep.newcomcom.Apps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pradeep.newcomcom.MainActivity;
import com.pradeep.newcomcom.R;
import com.pradeep.newcomcom.call.MyDbHelper;

import java.util.ArrayList;
//Pradeep puri goswami
public class AdapterApps extends RecyclerView.Adapter<AdapterApps.HolderRecord> {

// variables
private Context context;
private ArrayList<ModelRecordOne> recordsList;

//constractor
public AdapterApps(Context context, ArrayList<ModelRecordOne> recordsList) {
        this.context = context;
        this.recordsList = recordsList;
        }
    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view= LayoutInflater.from(context).inflate(R.layout.app_record, parent,false);


        return new HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder, int position) {
        //get data, set data, handel view clics in this method

        //getdata
        ModelRecordOne model=recordsList.get(position);
        final String id=model.getId();
        String name=model.getName();
        String packageName=model.getPackageName();
        String path=model.getPath();
        String addedTime=model.getAddedTime();
        String updateTime=model.getUpdatedTime();
        senddata(packageName);

        //set data to views
        holder.nameTv.setText(name);
        holder.pacakageNameTv.setText(packageName);
        holder.pathTv.setText(path);

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
        holder.moreBtntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //latr
                DatabaseSqlone databaseSqlone = new DatabaseSqlone(context);
                databaseSqlone.deleteData(id);
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
        TextView nameTv,pacakageNameTv, pathTv;
        ImageButton moreBtntwo;

        public HolderRecord(View item){
            super(item);

            //init views
            //profileIvv=item.findViewById(R.id.profileIvv);
            nameTv=(TextView)item.findViewById(R.id.nameTv);
            pacakageNameTv=(TextView)item.findViewById(R.id.packageNameTv);
            pathTv=(TextView)item.findViewById(R.id.pathTv);
            //nameThreeTv=(TextView)item.findViewById(R.id.nameThreeTv);
            moreBtntwo=(ImageButton)item.findViewById(R.id.moreBtnTwo);
        }
    }
    public String senddata(String packageName){
    return packageName;
    }
}
