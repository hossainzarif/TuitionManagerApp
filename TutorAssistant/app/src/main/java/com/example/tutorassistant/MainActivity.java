package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    MyDatabaseHelper myDatabaseHelper ;
    ArrayList<TuitionModelClass> objModelclassArraylist ;
    MyAdapterClass myAdapterClass ;
    BottomNavigationView bottomNavigationView ;
    private AlertDialog.Builder alert_delet ,edit_dialogue;
    private boolean check ;
    private int checkin ;
    private String BROH ;
 private RecyclerView.ViewHolder viewHolder1 ;
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
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {


                viewHolder1 =viewHolder ;

                alert_delet = new AlertDialog.Builder(MainActivity.this) ;
                alert_delet.setTitle("DELETE?") ;
                alert_delet.setMessage("  Do you want to delete?") ;

                alert_delet.setIcon(R.drawable.delete_alert_foreground);


               BROH = String.valueOf(viewHolder1.getAdapterPosition()+1) ;


                alert_delet.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        objModelclassArraylist.remove(viewHolder1.getAdapterPosition()) ;
                        check =myDatabaseHelper.deleteData(BROH);
                        checkin = objModelclassArraylist.size();
                        myDatabaseHelper.update_Rank(checkin,viewHolder1.getAdapterPosition()) ;
                        myAdapterClass.notifyItemRemoved(viewHolder1.getAdapterPosition());

                        dialogInterface.cancel();

                    }
                }) ;

                alert_delet.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        myAdapterClass.notifyItemRemoved(viewHolder1.getAdapterPosition() + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        myAdapterClass.notifyItemRangeChanged(viewHolder1.getAdapterPosition(), myAdapterClass.getItemCount());
                        dialogInterface.cancel();

                    }
                });

                alert_delet.setCancelable(false) ;

               AlertDialog alertDialog = alert_delet.create() ;
               alertDialog.show();

            }

            @Override
           public void onChildDraw (Canvas c,
                              RecyclerView recyclerView,
                              RecyclerView.ViewHolder viewHolder,
                              float dX,
                              float dY,
                              int actionState,
                              boolean isCurrentlyActive)
            {
                new RecyclerViewSwipeDecorator.Builder(MainActivity.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.RED))
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);





            }


        }).attachToRecyclerView(recyclerView);



    }





    public void ShowTheDatase() {



            Cursor cursorresult = myDatabaseHelper.displayAlldeta() ;

            if(cursorresult.getCount()==0)

            {

                Toast.makeText(getApplicationContext(),"No ROW",Toast.LENGTH_LONG).show();

               // myDatabaseHelper.reset();
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
                    public void OnItemLongClick(final int pos, View v) {







                        edit_dialogue = new AlertDialog.Builder(MainActivity.this) ;
                        edit_dialogue.setTitle("Edit ") ;
                        edit_dialogue.setMessage("  Edit existing data?") ;

                        edit_dialogue.setIcon(R.drawable.edit_foreground);






                        edit_dialogue.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(MainActivity.this,Edit_Tuition.class) ;
                                intent.putExtra("Position",pos+1) ;
                                startActivity(intent);


                            }
                        }) ;





                       AlertDialog editdialogue = edit_dialogue.create() ;
                        editdialogue.show();

























                    }
                });






            }


    }


    /*
    UPDATE Table_tablename
SET ContactName =
WHERE CustomerID = 1;
     */



    @Override
    public void onBackPressed()
    {

    }

}
