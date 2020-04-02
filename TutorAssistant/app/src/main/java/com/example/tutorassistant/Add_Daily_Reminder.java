package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import java.util.Calendar;

public class Add_Daily_Reminder extends AppCompatActivity {


    private TextView NameofTuition,Totalclass,Amount,classpmonth ;
    MyDatabaseHelper myDatabaseHelper ;
    BottomNavigationView bottomNavigationView_add_daily ;

    Button dateButton,Add_button,Remove_button;

    private String OkFatman ,myposition,ultimate_class;
            private  static String Achieve_class ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__daily__reminder);

        myDatabaseHelper = new MyDatabaseHelper(this) ;

        dateButton = findViewById(R.id.button_for_date);

        Add_button =findViewById(R.id.button_for_ADD);
        Remove_button =findViewById(R.id.button_for_Remove);



        NameofTuition = (TextView)findViewById(R.id.nameofthetuition) ;
        Amount = (TextView)findViewById(R.id.amountoftaka) ;
        classpmonth = (TextView)findViewById(R.id.ClassPermonth_SSSS) ;
        Totalclass=  (TextView)findViewById(R.id.Daysyouvegone) ;
        bottomNavigationView_add_daily = (BottomNavigationView) findViewById(R.id.navigationbottom_add_daily);






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


        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ans_add[]=ShowTheDatasefromReminder(myposition) ;
                Achieve_class =  ans_add[0] ;


                int count = Integer.parseInt(Achieve_class) ;
                count = count+1 ;

                if(count> Integer.parseInt(ultimate_class))
                {

                    count= Integer.parseInt(ultimate_class);
                    Toast.makeText(getApplicationContext(),"Max Class Count Reached Already",Toast.LENGTH_SHORT).show();
                }


                String Final_out = String.valueOf(count) ;



                myDatabaseHelper.update_TotalClasses(myposition,Final_out) ;
                Totalclass.setText(Final_out) ;




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

                String Final_out = String.valueOf(count) ;
                myDatabaseHelper.update_TotalClasses(myposition,Final_out) ;
                Totalclass.setText(Final_out);

            }
        });






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
                CharSequence dateCharSequence = DateFormat.format("MMM d, yyyy", calendar1);



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
                CharSequence timeCharSequence = DateFormat.format("hh:mm a", calendar1);


            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(0,0) ;
    }
}
