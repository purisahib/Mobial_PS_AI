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
import com.pradeep.newcomcom.R;
import com.pradeep.newcomcom.call.AdapterRecord;
import com.pradeep.newcomcom.call.AddCalls;
import com.pradeep.newcomcom.extra.Constants;
import com.pradeep.newcomcom.call.MyDbHelper;
//Pradeep puri goswami
//calls
public class Frag3 extends Fragment {
    private FloatingActionButton setyourcall;
    private RecyclerView recordsRv;
    //db helper
    private MyDbHelper dbHelper;

    // action bar
    ActionBar actionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag3_layout, container, false);
        //actionBar set title
        UpdateTitle();

        recordsRv= (RecyclerView) root.findViewById(R.id.callRecord);

        //init db helper class
        dbHelper= new MyDbHelper(getContext());

        //load records
        loadRecords();
        setyourcall=(FloatingActionButton)root.findViewById(R.id.abbBtnContect);
        setyourcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AddCalls.class);
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
        AdapterRecord adapterRecord= new AdapterRecord(getContext(),dbHelper.getAllRecords(Constants.C_ADDED_TIMESTAMP + " DESC"));

        recordsRv.setAdapter(adapterRecord);

        //set num of records
        actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar!=null)actionBar.setSubtitle("Total: "+dbHelper.getRecordsCount());
    }
}
