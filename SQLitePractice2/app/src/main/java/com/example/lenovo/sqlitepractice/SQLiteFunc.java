package com.example.lenovo.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteFunc extends SQLiteOpenHelper{

    private static String  Mytable="Mytable";
    private static  String  fname="firstName";
    private static   String  lname="lastName";
    private static  String  email="email";
    private static  String dbname="mydatabase.db";

    public SQLiteFunc(Context context) {
        super(context,dbname,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

      //  String sql="CREATE TABLE "+MyTable+"";
        String sql="CREATE TABLE " +Mytable+ " ( " +fname+ " TEXT , " +lname+ " TEXT , " +email+ " TEXT " + " ) ";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Mytable);
            onCreate(db);
    }


   public boolean insertIntoSQLiteDb(String fName, String LName, String Email){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(fname,fName);
        contentValues.put(lname,LName);
        contentValues.put(email,Email);
     long check=sqLiteDatabase.insert(Mytable,null,contentValues);

     if(check==-1)
     {
         return false;
     }
     else
         return true;
   }
}
