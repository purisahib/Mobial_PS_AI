package com.pradeep.newcomcom.call;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pradeep.newcomcom.MainActivity;
import com.pradeep.newcomcom.extra.Constants;
import com.pradeep.newcomcom.extra.setCalls;

import java.util.ArrayList;
//Pradeep puri goswami
public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, Constants.DB_NAMEOne, null,Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(Constants.CREATE_TABLEOne);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade database (if there is any structure change the change db version)

        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAMEOne);
        //create table again
        onCreate(db);
    }
    //insert record to db
    public long insertRecord(String nameOne, String nameTwo, String nameThree, String number, String addedTime, String updatedTime){
        //get writable database because we want to write data
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        // id wil be inserted automically as we set AutoIncrement in query
        long id =0;
        try{
            // insert data
            values.put(Constants.C_NAMEONE,nameOne);
            values.put(Constants.C_NAMETWO,nameTwo);
            values.put(Constants.C_NAMETHREE,nameThree);
            values.put(Constants.C_PHONE,number);
            values.put(Constants.C_ADDED_TIMESTAMP,addedTime);
            values.put(Constants.C_UPDATED_TIMESTAMP,updatedTime);
            if (values!=null){
                //insert row, it will return record id of seved record
                id= db.insert(Constants.TABLE_NAMEOne, null,values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //close db connection
        db.close();

        //return id of insert
        return id;
    }

    //get all data
    public ArrayList<ModelRecord> getAllRecords(String orderBy){
        ArrayList<ModelRecord> recordsList=new ArrayList<>();
        //query to select records
        String selectQuery="SELECT * FROM "+Constants.TABLE_NAMEOne+" ORDER BY "+orderBy;

        try {
            SQLiteDatabase db =this.getWritableDatabase();
             Cursor cursor = db.rawQuery(selectQuery, null);
             if (cursor!=null){
                 //looping throw all records and add to list
                 if (cursor.moveToFirst()){
                     do {
                         ModelRecord modelRecord= new ModelRecord(
                                 ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                                 ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAMEONE)),
                                 ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAMETWO)),
                                 ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAMETHREE)),
                                 ""+cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)),
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
    public ArrayList<ModelRecord> searchRecords(String query){

        ArrayList<ModelRecord> recordsList=new ArrayList<>();
        //query to select records
        String selectQuery="SELECT * FROM "+Constants.TABLE_NAMEOne+" WHERE "+Constants.C_NAMEONE + " LIKE '%" + query + "%'";

        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping throw all records and add to list
        if (cursor.moveToFirst()){
            do {
                ModelRecord modelRecord= new ModelRecord(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAMEONE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAMETWO)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAMETHREE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)),
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
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAMEOne + " WHERE " + Constants.C_NAMEONE + " LIKE '" + query + "%'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            //looping throw all records and add to list
            if (cursor.moveToFirst()) {
                do {
                    send = "" + cursor.getString(cursor.getColumnIndex(Constants.C_PHONE));
                } while (cursor.moveToNext());
            }
        }
        //close db connection
        db.close();
        //setCalls setCall=new setCalls(send);
        //return the list
        return send;
    }

    //get number of records
    public int getRecordsCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAMEOne;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }
    //delete data using id
    public void deleteData(String id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Constants.TABLE_NAMEOne,Constants.C_ID+"=?",new String[]{id});
        db.close();

    }
    //delete all data from table
    public void deleteAllData(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+Constants.TABLE_NAMEOne);
        db.close();
    }
}
