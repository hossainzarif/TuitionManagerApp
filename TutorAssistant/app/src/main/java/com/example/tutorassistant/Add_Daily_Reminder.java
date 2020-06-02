package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Time;
import java.util.Calendar;

public class Add_Daily_Reminder extends AppCompatActivity {


    private TextView NameofTuition,Totalclass,Amount,classpmonth,Calender_show,Time_show ;
    MyDatabaseHelper myDatabaseHelper ;
    BottomNavigationView bottomNavigationView_add_daily ;
    AlertDialog.Builder alert_delet ;
    Button dateButton,Add_button,Remove_button ,Confirm_reminder;
    private int count ;
    private String OkFatman ,myposition,ultimate_class,date_string,time_string;
    private  static String Achieve_class ;
private long bruh ;
    private CharSequence timeCharSequence,dateCharSequence ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__daily__reminder);

        myDatabaseHelper = new MyDatabaseHelper(this) ;

        dateButton = findViewById(R.id.button_for_date);

        Add_button =findViewById(R.id.button_for_ADD);
        Remove_button =findViewById(R.id.button_for_Remove);

        Calender_show = findViewById(R.id.calender) ;
        Time_show = findViewById(R.id.time) ;


        NameofTuition = (TextView)findViewById(R.id.nameofthetuition) ;
        Amount = (TextView)findViewById(R.id.amountoftaka) ;
        classpmonth = (TextView)findViewById(R.id.ClassPermonth_SSSS) ;
        Totalclass=  (TextView)findViewById(R.id.Daysyouvegone) ;
        bottomNavigationView_add_daily = (BottomNavigationView) findViewById(R.id.navigationbottom_add_daily);
        Confirm_reminder = findViewById(R.id.button_to_save_reminder) ;





    Bundle bundle = getIntent().getExtras() ;
    if(bundle!=null)
    {
         myposition =bundle.getString("Position");


    String ans[] = ShowTheDatasefromReminder(myposition) ;

    ultimate_class=ans[1] ;
    }


        bottomNavigationView_add_daily.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {

                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),HistoryofTuition.class));
                        overridePendingTransition(0,0) ;
                        return true ;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0) ;
                        return true ;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(),Add_reminder.class));
                        overridePendingTransition(0,0) ;
                        return true ;

                }
                return true ;
            }
        });


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton() ;
                handleTimeButton() ;
            }
        });


        Confirm_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               long bruh = yeahbuoyy() ;


                if(bruh>0)
                {
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Reminder Set", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Select Date and Time", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ans_add[]=ShowTheDatasefromReminder(myposition) ;
                Achieve_class =  ans_add[0] ;

              count = Integer.parseInt(Achieve_class) ;
                count = count+1 ;

                if(count >= Integer.parseInt(ultimate_class))
                {




                    //Toast.makeText(getApplicationContext(),"Max Class Count Reached Already",Toast.LENGTH_SHORT).show();



                    alert_delet = new AlertDialog.Builder(Add_Daily_Reminder.this) ;
                    alert_delet.setTitle("Reset ") ;
                    alert_delet.setMessage("  Max class reached start over?") ;

                    alert_delet.setIcon(R.drawable.reset_alert_foreground);






                    alert_delet.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            count= 0;
                            reset_class(count);

                        }
                    }) ;

                    alert_delet.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            count= Integer.parseInt(ultimate_class);
                            reset_class(count);

                        }
                    });

                    alert_delet.setCancelable(false) ;

                    AlertDialog alertDialog = alert_delet.create() ;
                    alertDialog.show();



                }



                reset_class(count) ;



            }
        });





        Remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans_remove[]=ShowTheDatasefromReminder(myposition) ;
                Achieve_class =  ans_remove[0] ;

                int count = Integer.parseInt(Achieve_class) ;
                count = count-1 ;

                if(count <=0)
                {

                    count= 0 ;
                    //Toast.makeText(getApplicationContext(),"Max Class Count Reached Already",Toast.LENGTH_SHORT).show();
                }

                reset_class(count);

            }
        });






    }

    private void reset_class(int counting) {

        String Final_out = String.valueOf(counting) ;
        myDatabaseHelper.update_TotalClasses(myposition,Final_out) ;
        Totalclass.setText(Final_out) ;


    }


    public String[] ShowTheDatasefromReminder(String position) {


        String[] ans = new String[2];


        Cursor cursorresult_reminder = myDatabaseHelper.displayAlldetafromReminder(position) ;

        StringBuilder stringBuilder_1 = new StringBuilder() ;
        StringBuilder stringBuilder_2 = new StringBuilder() ;
        StringBuilder stringBuilder_3 = new StringBuilder() ;
        StringBuilder stringBuilder_4 = new StringBuilder() ;
        if(cursorresult_reminder.getCount()==0)

        {
            Toast.makeText(getApplicationContext(),"No ROW",Toast.LENGTH_LONG).show();

            return null ;

        }
        else
        {

            while(cursorresult_reminder.moveToNext())
            {
                stringBuilder_1.append("Name : "+cursorresult_reminder.getString(1)) ;
                stringBuilder_2.append("Amount : "+cursorresult_reminder.getString(2)) ;
                stringBuilder_3.append("Class Per Month : "+cursorresult_reminder.getString(3)) ;
                stringBuilder_4.append(cursorresult_reminder.getString(4)) ;
                ans[0]= cursorresult_reminder.getString(4) ;
               ans[1]=cursorresult_reminder.getString(3);


            }




            NameofTuition.setText(stringBuilder_1);

            Amount.setText(stringBuilder_2);
            classpmonth.setText(stringBuilder_3);
            Totalclass.setText(stringBuilder_4) ;

            return ans ;
        }


    }


    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,  R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
//                String dateString = year + " " + month + " " + date;
//                dateTextView.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                 dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);

                Time_show.setText(dateCharSequence);

               date_string= String.valueOf(dateCharSequence) ;

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();

    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                 timeCharSequence = DateFormat.format("hh:mm a", calendar1);


                Calender_show.setText(timeCharSequence);
                time_string= String.valueOf(timeCharSequence) ;
            }
        }, HOUR, MINUTE, is24HourFormat);



        timePickerDialog.show();

    }



    private long yeahbuoyy()
    {
        MyDatabaseHelper myDatabaseHelper =new MyDatabaseHelper(this) ;


        if(time_string!=null && date_string!=null) {

             bruh = myDatabaseHelper.insertReminder(Integer.parseInt(myposition), date_string, time_string);
        }

        return bruh ;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(0,0) ;
    }
}
