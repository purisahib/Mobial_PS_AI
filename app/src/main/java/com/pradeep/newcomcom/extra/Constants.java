package com.pradeep.newcomcom.extra;
//Pradeep puri goswami
public class Constants {
    //db name
    public static final String DB_NAMEOne = "AI_RecordOne";
    public static final String DB_NAMETwo = "AI_RecordTwo";
    public static final String DB_NAMEThree = "AI_RecordThree";
    //db version
    public static final int DB_VERSION = 1;
    //TABLE NAME
    public static final String TABLE_NAMEOne = "Calls";
    public static final String TABLE_NAMETwo = "Application";
    public static final String TABLE_NAMEThree = "QuestionAnswer";
    //COLUMS/FIELDS OF Comman Table
    public static final String C_ID = "ID";
    public static final String C_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final String C_UPDATED_TIMESTAMP = "UPDATE_TIME_STAMP";
    //COLUMS/FIELDS OF TABLE One
    public static final String C_NAMEONE = "NAMEONE";
    public static final String C_NAMETWO = "NAMETWO";
    public static final String C_NAMETHREE = "NAMETHREE";
    public static final String C_PHONE = "PHONE";
    //COLUMS/FIELDS OF TABLE Two
    public static final String C_NAME = "NAME";
    public static final String C_PACKAGENAME = "PACKAGENAME";
    public static final String C_PATH = "PATH";
    //COLUMS/FIELDS OF TABLE Three
    public static final String C_QUESTION = "QUESTION";
    public static final String C_ANSWER = "ANSWER";

    //CREATE TABLE QUERY One
    public static final String CREATE_TABLEOne = "CREATE TABLE " + TABLE_NAMEOne + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAMEONE + " TEXT,"
            + C_NAMETWO + " TEXT,"
            + C_NAMETHREE + " TEXT,"
            + C_PHONE + " TEXT,"
            + C_ADDED_TIMESTAMP + " TEXT,"
            + C_UPDATED_TIMESTAMP + " TEXT"
            + " )";

    //CREATE TABLE QUERY Two
    public static final String CREATE_TABLETwo = "CREATE TABLE " + TABLE_NAMETwo + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME + " TEXT,"
            + C_PACKAGENAME + " TEXT,"
            + C_PATH + " TEXT,"
            + C_ADDED_TIMESTAMP + " TEXT,"
            + C_UPDATED_TIMESTAMP + " TEXT"
            + " )";

    //CREATE TABLE QUERY Three
    public static final String CREATE_TABLEThree = "CREATE TABLE " + TABLE_NAMEThree + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_QUESTION + " TEXT,"
            + C_ANSWER + " TEXT,"
            + C_ADDED_TIMESTAMP + " TEXT,"
            + C_UPDATED_TIMESTAMP + " TEXT"
            + " )";

}