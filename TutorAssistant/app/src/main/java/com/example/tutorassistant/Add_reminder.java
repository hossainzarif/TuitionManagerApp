package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Add_reminder extends AppCompatActivity {

    private Button button_confirm,delete_kore_dbo ;
    private EditText nameeditText ,AmountEditText,PaymountEditText,Classtaken ;
    MyDatabaseHelper myDatabaseHelper ;
    BottomNavigationView bottomNavigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        nameeditText =findViewById(R.id.name_of_tuition) ;
        AmountEditText =findViewById(R.id.amount_of_tuition) ;
        PaymountEditText =(EditText)findViewById(R.id.payment_type) ;
        Classtaken = (EditText)findViewById(R.id.classes_taken) ;



        button_confirm =(Button)findViewById(R.id.button_for_confirm) ;


        myDatabaseHelper = new MyDatabaseHelper(this) ;
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase() ;


        nameeditText.addTextChangedListener(AddTextWatcher);
        AmountEditText.addTextChangedListener(AddTextWatcher);
        PaymountEditText.addTextChangedListener(AddTextWatcher);
        Classtaken.addTextChangedListener(AddTextWatcher);



        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationbottom_reminder);



        button_confirm.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View view) {

                String nameInputs = nameeditText.getText().toString();
                String amountInputs = AmountEditText.getText().toString();
                String paymentinputs = PaymountEditText.getText().toString();
                String mytotalclasses = Classtaken.getText().toString();


                if(ValidateEdittext() && view.getId()== R.id.button_for_confirm)
                {





                   long rowid = myDatabaseHelper.insertData(nameInputs,amountInputs,paymentinputs,mytotalclasses);

                   if(rowid>0)
                   {
                       Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Sorry!Could'nt Add",Toast.LENGTH_LONG).show();
                   }



                    nameeditText.getText().clear();
                    AmountEditText.getText().clear();
                    PaymountEditText.getText().clear();
                    Classtaken.getText().clear();
                    nameeditText.setError(null);
                    AmountEditText.setError(null);
                    PaymountEditText.setError(null);
                    Classtaken.setError(null);

                }











            }
        });


        bottomNavigationView.setSelectedItemId(R.id.add);
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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0) ;
                        return true ;
                    case R.id.add:

                    return true ;

                }
                return true ;
            }
        });





    }


    public void showdata(String title,String data)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setTitle(title) ;
        builder.setMessage(data) ;
        builder.setCancelable(true) ;
        builder.show() ;
    }

    private TextWatcher AddTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {




        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            String nameInput = nameeditText.getText().toString().trim() ;
            String amountInput = AmountEditText.getText().toString().trim() ;
            String paymentinput = PaymountEditText.getText().toString().trim() ;
            String mytotalclass = Classtaken.getText().toString().trim();



            if(nameInput.isEmpty())
            {
                nameeditText.setError("Field Can't be Empty");
            }
            else
            {
                nameeditText.setError(null);
            }

            if(amountInput.isEmpty())
            {
                AmountEditText.setError("Field Can't be Empty");
            }
            else
            {
                AmountEditText.setError(null);
            }

            if(paymentinput.isEmpty())
            {
                PaymountEditText.setError("Field Can't be Empty");
            }
            else
            {
               PaymountEditText.setError(null);
            }
            if(mytotalclass.isEmpty())
            {
               Classtaken.setError("Field Can't be Empty");
            }
            else
            {
                Classtaken.setError(null);
            }




        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    } ;



    private boolean ValidateEdittext()
    {
        String nameInput = nameeditText.getText().toString().trim() ;
        String amountInput = AmountEditText.getText().toString().trim() ;
        String paymentinput = PaymountEditText.getText().toString().trim() ;
        String mytotalclass = Classtaken.getText().toString().trim();


        if(  !nameInput.isEmpty()&& !amountInput.isEmpty()&&!paymentinput.isEmpty()&&!mytotalclass.isEmpty())
        {
            nameeditText.setError(null);
            AmountEditText.setError(null);
            PaymountEditText.setError(null);
            Classtaken.setError(null);

            return true ;

        }
        else
        {

            if(nameInput.isEmpty())
            {
                nameeditText.setError("Field Can't be Empty");
            }
            else
            {
                nameeditText.setError(null);
            }
            if(amountInput.isEmpty())
            {
                AmountEditText.setError("Field Can't be Empty");
            }
            else
            {
                AmountEditText.setError(null);
            }
            if(paymentinput.isEmpty())
            {
                PaymountEditText.setError("Field Can't be Empty");

            }
            else
            {
                PaymountEditText.setError(null);
            }
            if(mytotalclass.isEmpty())
            {
                Classtaken.setError("Field Can't be Empty");

            }
            else
            {
                Classtaken.setError(null);
            }




            return false ;

        }



    }















    public void openActivity()
    {
        Intent intent = new Intent(this,Add_Daily_Reminder.class) ;
        startActivity(intent);
    }



}
