package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    MyDatabaseHelper myDatabaseHelper ;
    ArrayList<TuitionModelClass> objModelclassArraylist ;
    MyAdapterClass myAdapterClass ;
    BottomNavigationView bottomNavigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        objModelclassArraylist = new ArrayList<>() ;
        recyclerView= findViewById(R.id.recycleme) ;
        myDatabaseHelper = new MyDatabaseHelper(this) ;
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase() ;
         bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationbottom_home_main) ;




        ShowTheDatase() ;
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),HistoryofTuition.class));
                        overridePendingTransition(0,0) ;
                        return true ;
                    case R.id.home:
                        return true ;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(),Add_reminder.class));
                        overridePendingTransition(0,0) ;
                        return true ;

                }
                return true ;
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                String BROH = String.valueOf(viewHolder.getAdapterPosition()+1) ;



                objModelclassArraylist.remove(viewHolder.getAdapterPosition()) ;
                boolean check =myDatabaseHelper.deleteData(BROH);
                int checkin = objModelclassArraylist.size();
                myDatabaseHelper.update_Rank(checkin,viewHolder.getAdapterPosition()) ;
                myAdapterClass.notifyItemRemoved(viewHolder.getAdapterPosition());










            }
        }).attachToRecyclerView(recyclerView);



    }


    public void ShowTheDatase() {



            Cursor cursorresult = myDatabaseHelper.displayAlldeta() ;

            if(cursorresult.getCount()==0)

            {

                Toast.makeText(getApplicationContext(),"No ROW",Toast.LENGTH_LONG).show();

                myDatabaseHelper.reset();
                return;

            }
            else
            {

                while(cursorresult.moveToNext())
                {
                    objModelclassArraylist.add(new TuitionModelClass(cursorresult.getString(1),
                                                cursorresult.getString(2),
                                                cursorresult.getString(3),
                                                cursorresult.getString(4)
                    )) ;
                }


                myAdapterClass = new MyAdapterClass(objModelclassArraylist) ;

                recyclerView.hasFixedSize() ;
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(myAdapterClass);


                myAdapterClass.setOnItemClickListener(new MyAdapterClass.Clicklistener() {
                    @Override
                    public void OnItemclick(int pos, View v) {

                       int posti = pos+1 ;
                       String position = String.valueOf(posti);


                      Intent intent = new Intent(MainActivity.this,Add_Daily_Reminder.class) ;
                      intent.putExtra("Position",position) ;
                      startActivity(intent);


                    }

                    @Override
                    public void OnItemLongClick(int pos, View v) {



                    }
                });






            }


    }


    /*
    UPDATE Table_tablename
SET ContactName =
WHERE CustomerID = 1;
     */



}
