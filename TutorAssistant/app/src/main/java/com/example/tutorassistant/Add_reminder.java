package com.example.tutorassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_reminder extends AppCompatActivity {

    private Button button_confirm ;
    private EditText nameeditText ,AmountEditText,PaymountEditText ;
    MyDatabaseHelper myDatabaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        nameeditText =findViewById(R.id.name_of_tuition) ;
        AmountEditText =findViewById(R.id.amount_of_tuition) ;

        PaymountEditText =(EditText)findViewById(R.id.payment_type) ;



        button_confirm =(Button)findViewById(R.id.button_for_confirm) ;


        myDatabaseHelper = new MyDatabaseHelper(this) ;
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase() ;


        nameeditText.addTextChangedListener(AddTextWatcher);
        AmountEditText.addTextChangedListener(AddTextWatcher);
        PaymountEditText.addTextChangedListener(AddTextWatcher);





        button_confirm.setOnClickListener(new View.OnClickListener() {
            String nameInput = nameeditText.getText().toString().trim() ;
            String amountInput = AmountEditText.getText().toString().trim() ;
            String paymentinput = PaymountEditText.getText().toString().trim() ;



            @Override
            public void onClick(View view) {


                if(ValidateEdittext() && view.getId()== R.id.button_for_confirm)
                {

                }
                else
                {

                }





            }
        });

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


        if(  !nameInput.isEmpty()&& !amountInput.isEmpty()&&!paymentinput.isEmpty())
        {
            nameeditText.setError(null);
            AmountEditText.setError(null);
            PaymountEditText.setError(null);
            return true ;

        }
        else
        {

            if(nameInput.isEmpty())
            {
                nameeditText.setError("Field Can't be Empty");
            }
            if(amountInput.isEmpty())
            {
                AmountEditText.setError("Field Can't be Empty");
            }
            if(paymentinput.isEmpty())
            {
                PaymountEditText.setError("Field Can't be Empty");
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
