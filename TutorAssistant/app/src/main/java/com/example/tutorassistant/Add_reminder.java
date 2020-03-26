package com.example.tutorassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_reminder extends AppCompatActivity {

    Button button_confirm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);


        button_confirm =(Button)findViewById(R.id.button_for_confirm) ;

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity() ;
            }
        });

    }

    public void openActivity()
    {
        Intent intent = new Intent(this,Add_Daily_Reminder.class) ;
        startActivity(intent);
    }



}
