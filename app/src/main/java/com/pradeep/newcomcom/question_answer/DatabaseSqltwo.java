package com.pradeep.newcomcom.question_answer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.pradeep.newcomcom.extra.Constants;

import java.util.ArrayList;
//Pradeep puri goswami
public class DatabaseSqltwo extends SQLiteOpenHelper {
    public DatabaseSqltwo(@Nullable Context context) {
        super(context, Constants.DB_NAMEThree, null,Constants.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(Constants.CREATE_TABLEThree);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade database (if there is any structure change the change db version)
        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAMEThree);
        //create table again
        onCreate(db);
    }
    //insert record to db
    public long insertRecord(String s, String s1, String s2, String s3){
        //get writable database because we want to write data
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        // id wil be inserted automically as we set AutoIncrement in query

        // insert data
        values.put(Constants.C_QUESTION,s);
        values.put(Constants.C_ANSWER,s1);
        values.put(Constants.C_ADDED_TIMESTAMP,s2);
        values.put(Constants.C_UPDATED_TIMESTAMP,s3);

        //insert row, it will return record id of seved record
        long id= db.insert(Constants.TABLE_NAMEThree, null,values);

        //close db connection
        db.close();

        //return id of insert
        return id;
    }

    //get all data
    public ArrayList<ModelRecordtwo> getAllRecords(String orderBy){

        ArrayList<ModelRecordtwo> recordsList=new ArrayList<>();
        //query to select records
        String selectQuery="SELECT * FROM "+Constants.TABLE_NAMEThree+" ORDER BY "+orderBy;
        try {
            SQLiteDatabase db =this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor!=null){
                //looping throw all records and add to list
                if (cursor.moveToFirst()){
                    do {
                        ModelRecordtwo modelRecord= new ModelRecordtwo(
                                ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                                ""+cursor.getString(cursor.getColumnIndex(Constants.C_QUESTION)),
                                ""+cursor.getString(cursor.getColumnIndex(Constants.C_ANSWER)),
                                ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                                ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP)));
                        //add record to list
                        recordsList.add(modelRecord);
                    }while (cursor.moveToNext());
                }
            }
            //close db connection
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return the list
        return recordsList;
    }

    //search data
    public ArrayList<ModelRecordtwo> searchRecords(String query){

        ArrayList<ModelRecordtwo> recordsList=new ArrayList<>();
        //query to select records
        String selectQuery="SELECT * FROM "+Constants.TABLE_NAMEThree+" WHERE "+Constants.C_QUESTION + " LIKE '%" + query + "%'";

        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping throw all records and add to list
        if (cursor.moveToFirst()){
            do {
                ModelRecordtwo modelRecord= new ModelRecordtwo(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_QUESTION)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_ANSWER)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP)));
                //add record to list
                recordsList.add(modelRecord);
            }while (cursor.moveToNext());
        }
        //close db connection
        db.close();

        //return the list
        return recordsList;
    }
    //search custimize data
    public String searchData(String query) {
        String send = "";

        //query to select records
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAMEThree + " WHERE " + Constants.C_QUESTION + " LIKE '" + query + "%'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            //looping throw all records and add to list
            if (cursor.moveToFirst()) {
                do {

                    send = "" + cursor.getString(cursor.getColumnIndex(Constants.C_ANSWER));

                } while (cursor.moveToNext());
            }
        }
        //close db connection
        db.close();

        //return the list
        return send;
    }
        //get number of records
    public int getRecordsCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAMEThree;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }
    //delete data using id
    public void deleteData(String id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Constants.TABLE_NAMEThree,Constants.C_ID+"=?",new String[]{id});
        db.close();

    }
}
