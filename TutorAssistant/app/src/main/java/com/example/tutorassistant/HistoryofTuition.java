package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryofTuition extends AppCompatActivity {


    BottomNavigationView bottomNavigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyof_tuition);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationbottom_history);


        bottomNavigationView.setSelectedItemId(R.id.history);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.history:

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
}
