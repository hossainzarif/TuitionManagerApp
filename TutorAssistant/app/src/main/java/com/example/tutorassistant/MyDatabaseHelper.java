package com.example.tutorassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    private static  final String Database_name = "Tutor.db" ;
    private static  final String Table_name = "tuition_details" ;
    private static  final String Table_name_2 = "tuition_reminder" ;
    private static  final int version= 1 ;

    private static  final String Tuition_name = "Name" ;
    private static  final String Tuition_id = "tuition_id" ;
    private static  final String Amount = "amount" ;
    private static  final String Payment = "Payment" ;
    private static  final String Ref_id = "Refere_id" ;
    private static  final String Date = "date" ;
    private static  final String Time = "time" ;
    private static  final String Classes = "classes_taken" ;

    private Context context ;





    public MyDatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, version);
        this.context =context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Toast.makeText(context,"Tables ARe Created",Toast.LENGTH_LONG).show();             //toast for Oncreate

            sqLiteDatabase.execSQL("CREATE TABLE " + Table_name + "  ( " + Tuition_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Tuition_name + " VARCHAR2(100) NOT NULL," + Amount + " INTEGER NOT NULL," + Payment + " INTEGER NOT NULL) ; ");

            sqLiteDatabase.execSQL("CREATE TABLE " + Table_name_2 + "  ( " + Ref_id + " INTEGER ," + Date  + " VARCHAR2(100) NOT NULL," + Time + " Varchar2(10) NOT NULL," + Classes + " INTEGER NOT NULL,"
                    + " FOREIGN KEY (" + Ref_id + ") REFERENCES " + Table_name + "(" + Tuition_id + "));");
        } catch(Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertData(String name ,String taka ,String days)
    {
       SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put();
    }


}
