package com.example.lenovo.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 1/1/2018.
 */

// extending SQLiteOpenHelper Class
public class MySqLite extends SQLiteOpenHelper {


    private static String database_name="myDatabase.db";
    private static String table="Mytable";
    private static String Colum1="ID";
    private static String Colum2="FirstName";
    private static String Colum3="LastName";
    private static String Colum4="email";
  ///  private static String table="Mytable";

    public MySqLite(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query="CREATE TABLE "+table+" ( " +Colum1+ " Integer PRIMARY KEY , "+Colum2+" TEXT , "+Colum3+" TEXT , " +Colum4+" TEXT " + " ) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }
    public Boolean addToAddTable(String id,String firstName,String lastName,String email)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Colum1,id);
        contentValues.put(Colum2,firstName);
        contentValues.put(Colum3,lastName);
        contentValues.put(Colum4,email);
       // SQLiteDatabase.insert();
       long checker=sqLiteDatabase.insert(table,null,contentValues);
       if(checker==-1)
       {
           return false;
       }
       else
           return true;

    }
    public Cursor Display()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * from "+table,null);

        return cursor;
    }

    public  boolean UpdateData(String id,String firstName,String lastName,String email)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Colum1,id);
        contentValues.put(Colum2,firstName);
        contentValues.put(Colum3,lastName);
        contentValues.put(Colum4,email);
        sqLiteDatabase.update(table,contentValues," ID = ? ",new String[]{id});
        return true;

    }

    public boolean deleteDate(String id)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(table," ID = ?",new String[]{id});
        return true;
    }
}
