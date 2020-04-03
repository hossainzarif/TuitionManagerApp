package com.example.tutorassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    private static  final String Database_name = "Tutor.db" ;
    private static  final String Table_name = "tuition_details" ;
    private static  final String Table_name_2 = "tuition_reminder" ;
    private static  final int version= 1 ;

   private static int Rank_Count ;
    private static int will_updated  ;
    private static  final String Tuition_name = "Name" ;
    private static  final String Tuition_id = "tuition_id" ;
    private static  final String Rank = "rank_id" ;
    private static  final String Amount = "amount" ;
    private static  final String Payment = "Payment" ;
    private static  final String Ref_id = "Refere_id" ;
    private static  final String Date = "date" ;
    private static  final String Time = "time" ;

    private static  final String Classes = "classes_taken" ;

    private static final  String initialQuery = "SELECT * From "+Table_name;
    private String ID_Passed ;


    private Context context ;
    private String Rank_string ;




    public MyDatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, version);
        this.context =context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Toast.makeText(context,"Tables ARe Created",Toast.LENGTH_LONG).show();             //toast for Oncreate

            sqLiteDatabase.execSQL("CREATE TABLE " + Table_name + "  ( " + Tuition_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Tuition_name + " VARCHAR2(100) NOT NULL," + Amount + " INTEGER NOT NULL," + Payment + " INTEGER NOT NULL," + Classes + " INTEGER NOT NULL," +Rank+ " INTEGER NOT NULL) ; ");

            sqLiteDatabase.execSQL("CREATE TABLE " + Table_name_2 + "  ( " + Ref_id + " INTEGER ," + Date  + " VARCHAR2(100) NOT NULL," + Time + " Varchar2(10) NOT NULL,"
                    + " FOREIGN KEY (" + Ref_id + ") REFERENCES " + Table_name + "(" + Rank + "));");
        } catch(Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void reset()
    {
        Rank_Count=0 ;
    }

    public long insertData(String name ,String taka ,String days,String Classestaken)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;



        Cursor cursor_rank = sqLiteDatabase.rawQuery(" Select MAX(rank_id) from tuition_details ;", null);

        if(cursor_rank.getCount()==0)
        {
            Rank_Count=0 ;
        }
        else
        {
            while(cursor_rank.moveToNext())

            {
                Rank_Count= cursor_rank.getInt(0) ;
            }
        }







        Rank_Count=Rank_Count+1 ;
        String rank_String_count = String.valueOf(Rank_Count) ;


        ContentValues contentValues = new ContentValues() ;
        contentValues.put(Tuition_name,name);
        contentValues.put(Amount,taka);
        contentValues.put(Payment,days);
        contentValues.put(Classes,Classestaken);
        contentValues.put(Rank,rank_String_count);
        //sqLiteDatabase.insert(Tuition_name,null,contentValues) ;
        long id = sqLiteDatabase.insert(Table_name,null,contentValues);

        return id ;

    }

    public boolean update_TotalClasses(String id,String Classestaken )
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;
        ContentValues contentValues_update = new ContentValues() ;
        contentValues_update.put(Classes,Classestaken);
        //sqLiteDatabase.insert(Tuition_name,null,contentValues) ;
         sqLiteDatabase.update(Table_name,contentValues_update,"rank_id = ?",new String[]{id}) ;

        return true ;

    }


    public Cursor displayAlldeta()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        Cursor cursor =sqLiteDatabase.rawQuery(initialQuery,null);

        return cursor ;

    }




    public Cursor displayAlldetafromReminder(String passed) {

        ID_Passed =passed ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        Cursor cursorRemind =sqLiteDatabase.rawQuery("SELECT * From "+Table_name+" where rank_id = "+ID_Passed,null);


        return cursorRemind ;
    }

    public boolean deleteData(String id )
    {



        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;


        sqLiteDatabase.execSQL("delete from "+Table_name+" where "+Rank+" = "+id+" ;") ;








        return true ;

    }

    public void update_Rank(int size,int adap_position) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;

       // Toast.makeText(context.getApplicationContext(), "position"+adap_position+" SIZE "+size, Toast.LENGTH_SHORT).show();



        if(size!=adap_position) {
            for (int i = adap_position+2; i <= size+1; i++) {


                sqLiteDatabase.execSQL("UPDATE tuition_details SET rank_id = rank_id-1 WHERE rank_id = "+i+" ;"
                );


            }


        }

        else
        {
          //  Toast.makeText(context.getApplicationContext(), "Objecklist SIze" + size, Toast.LENGTH_SHORT).show();
        }


    }

 /*   public Cursor display_ultimate(String passed) {

        ID_Passed =passed ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        Cursor cursorRemind =sqLiteDatabase.rawQuery("SELECT * From "+Table_name+" where "+Tuition_id+" = "+ID_Passed,null);


        return cursorRemind ;
    } */






 //ALL update happening






    public void update_name (String id,String name )
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;
        ContentValues contentValues_update = new ContentValues() ;
        contentValues_update.put(Tuition_name,name);
        //sqLiteDatabase.insert(Tuition_name,null,contentValues) ;
        sqLiteDatabase.update(Table_name,contentValues_update,"rank_id = ?",new String[]{id}) ;

         Toast.makeText(context.getApplicationContext(), "Entered" , Toast.LENGTH_SHORT).show();


    }


    public void update_amount(String id, String amount) {

        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;
        ContentValues contentValues_update = new ContentValues() ;
        contentValues_update.put(Amount,amount);
        //sqLiteDatabase.insert(Tuition_name,null,contentValues) ;
        sqLiteDatabase.update(Table_name,contentValues_update,"rank_id = ?",new String[]{id}) ;


    }
    public void update_payment(String id, String payt) {

        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase() ;
        ContentValues contentValues_update = new ContentValues() ;
        contentValues_update.put(Payment,payt);
        //sqLiteDatabase.insert(Tuition_name,null,contentValues) ;
        sqLiteDatabase.update(Table_name,contentValues_update,"rank_id = ?",new String[]{id}) ;


    }



}
