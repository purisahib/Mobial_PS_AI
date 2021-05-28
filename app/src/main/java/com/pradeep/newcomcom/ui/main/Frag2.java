package com.pradeep.newcomcom.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pradeep.newcomcom.Apps.AdapterApps;
import com.pradeep.newcomcom.Apps.AddApps;
import com.pradeep.newcomcom.Apps.DatabaseSqlone;
import com.pradeep.newcomcom.R;
import com.pradeep.newcomcom.extra.Constants;
//Pradeep puri goswami
///Appsss
public class Frag2 extends Fragment {
    private FloatingActionButton setyourApp;
    private RecyclerView appRecord;
    //db helper
    private DatabaseSqlone databaseSqlone;

    // action bar
    ActionBar actionBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag2_layout, container, false);
        UpdateTitle();

        appRecord= (RecyclerView) root.findViewById(R.id.appRecord);

        //init db helper class
        databaseSqlone= new DatabaseSqlone(getContext());

        //load records
        loadRecords();
        setyourApp=(FloatingActionButton)root.findViewById(R.id.appBtn);
        setyourApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddApps.class);
                startActivity(intent);

            }
        });

        return root;
    }

    private void UpdateTitle() {
        if (getActivity() instanceof AppCompatActivity){
            actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
            if (actionBar!=null) actionBar.setTitle(R.string.sub_title);
        }
    }


    private void loadRecords() {
        AdapterApps adapterRecord= new AdapterApps(getContext(),databaseSqlone.getAllRecords(Constants.C_ID + " DESC"));
        appRecord.setAdapter(adapterRecord);
        //set num of records
        actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar!=null)actionBar.setSubtitle("Total: "+databaseSqlone.getRecordsCount());
    }
    @Override
    public void onResume() {
        super.onResume();
        loadRecords();//referse records list
    }

}
