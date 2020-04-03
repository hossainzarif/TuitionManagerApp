package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Edit_Tuition extends AppCompatActivity {


    private EditText nameeditText_up ,AmountEditText_up,PaymountEditText_up,Classtaken_up ;
    Button button ;
    private String myposition ;
    BottomNavigationView bottomNavigationView_edit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__tuition);

        final MyDatabaseHelper myDatabaseHelper ;

        nameeditText_up =findViewById(R.id.name_of_tuition_update) ;
        AmountEditText_up =findViewById(R.id.amount_of_tuition_update) ;
        PaymountEditText_up =(EditText)findViewById(R.id.payment_type_update) ;

        bottomNavigationView_edit =(BottomNavigationView) findViewById(R.id.botttom_update) ;

        button = (Button) findViewById(R.id.button_for_confirm_update) ;

        myDatabaseHelper = new MyDatabaseHelper(this) ;



        Bundle bundle = getIntent().getExtras() ;
        if(bundle!=null)
        {
            myposition =String.valueOf(bundle.getInt("Position"));


        }


      button.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View view) {

                String nameInputs = nameeditText_up.getText().toString();
                String amountInputs = AmountEditText_up.getText().toString();
                String paymentinputs = PaymountEditText_up.getText().toString();



                if(  Check()  && view.getId()== R.id.button_for_confirm_update)
                {


                    if(!nameInputs.isEmpty())

                    {

                        Toast.makeText(getApplicationContext().getApplicationContext(), "Validation WOrks"
                                 , Toast.LENGTH_SHORT).show();

                        myDatabaseHelper.update_name(myposition,nameInputs);
                    }

                    if(!amountInputs.isEmpty())

                    {

                        Toast.makeText(getApplicationContext().getApplicationContext(), "Validation WOrks amount"
                                , Toast.LENGTH_SHORT).show();

                        myDatabaseHelper.update_amount(myposition,amountInputs);
                    }
                    if(!paymentinputs.isEmpty())

                    {

                        Toast.makeText(getApplicationContext().getApplicationContext(), "Validation WOrks payment"
                                , Toast.LENGTH_SHORT).show();

                        myDatabaseHelper.update_payment(myposition,paymentinputs);
                    }


                }











            }
        });



        bottomNavigationView_edit.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
















    }




   public boolean Check()
    {
        String nameInputs = nameeditText_up.getText().toString();
        String amountInputs = AmountEditText_up.getText().toString();
        String paymentinputs = PaymountEditText_up.getText().toString();




        if(nameInputs.isEmpty()&&amountInputs.isEmpty()&& paymentinputs.isEmpty())
        {

            return false ;
        }

        else
        {
            return true ;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(0,0) ;


    }
}
