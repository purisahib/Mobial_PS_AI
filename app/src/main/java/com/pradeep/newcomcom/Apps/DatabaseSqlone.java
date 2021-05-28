package com.pradeep.newcomcom.Apps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pradeep.newcomcom.extra.Constants;

import java.util.ArrayList;

//Pradeep puri goswami
public class DatabaseSqlone extends SQLiteOpenHelper {
    private static SQLiteOpenHelper getContext;

    public DatabaseSqlone(@Nullable Context context) {
        super(context, Constants.DB_NAMETwo, null, Constants.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(Constants.CREATE_TABLETwo);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade database (if there is any structure change the change db version)

        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAMETwo);
        //create table again
        onCreate(db);
    }
    //insert record to db
    public long insertRecord(String name, String packagename, String path, String addedTime, String updatedTime){
        //get writable database because we want to write data
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        // id wil be inserted automically as we set AutoIncrement in query
        // insert data
        values.put(Constants.C_NAME,name);
        values.put(Constants.C_PACKAGENAME,packagename);
        values.put(Constants.C_PATH,path);
        values.put(Constants.C_ADDED_TIMESTAMP,addedTime);
        values.put(Constants.C_UPDATED_TIMESTAMP,updatedTime);

        //insert row, it will return record id of seved record
        long id= db.insert(Constants.TABLE_NAMETwo, null,values);

        //close db connection
        db.close();

        //return id of insert
        return id;
    }
    //get all data
    public ArrayList<ModelRecordOne> getAllRecords(String orderBy){

        ArrayList<ModelRecordOne> recordsList=new ArrayList<>();
        //query to select records
        String selectQuery="SELECT * FROM "+ Constants.TABLE_NAMETwo+" ORDER BY "+orderBy;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                //looping throw all records and add to list
                if (cursor.moveToFirst()) {
                    do {
                        ModelRecordOne modelRecordOne = new ModelRecordOne(
                                "" + cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                                "" + cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                                "" + cursor.getString(cursor.getColumnIndex(Constants.C_PACKAGENAME)),
                                "" + cursor.getString(cursor.getColumnIndex(Constants.C_PATH)),
                                "" + cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                                "" + cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP)));
                        //add record to list
                        recordsList.add(modelRecordOne);
                    } while (cursor.moveToNext());
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
    public  ArrayList<ModelRecordOne> searchRecords(String query){

        ArrayList<ModelRecordOne> recordsList=new ArrayList<>();
        //query to select records
        String selectQuery="SELECT * FROM "+ Constants.TABLE_NAMETwo+" WHERE "+ Constants.C_NAME + " LIKE '%" + query + "%'";


        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping throw all records and add to list
        if (cursor.moveToFirst()){
            do {
                ModelRecordOne modelRecord= new ModelRecordOne(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PACKAGENAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_PATH)),
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
    public String searchData(String query){
        String send = "";

        //query to select records
        String selectQuery="SELECT * FROM "+ Constants.TABLE_NAMETwo+" WHERE "+ Constants.C_NAME + " LIKE '" + query + "%'";


        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor!=null){
            //looping throw all records and add to list
            if (cursor.moveToFirst()){
                do {

                    //""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID));
                    //""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                    send=""+cursor.getString(cursor.getColumnIndex(Constants.C_PACKAGENAME));
                    //""+cursor.getString(cursor.getColumnIndex(Constants.C_PATH)),
                    //""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                    //""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP)));

                }while (cursor.moveToNext());
            }
        }

        //close db connection
        db.close();

        //return the list
        return send;
    }

    //get number of records
    public int getRecordsCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAMETwo;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }
    //delete data using id
    public void deleteData(String id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Constants.TABLE_NAMETwo,Constants.C_ID+"=?",new String[]{id});
        db.close();

    }

}
