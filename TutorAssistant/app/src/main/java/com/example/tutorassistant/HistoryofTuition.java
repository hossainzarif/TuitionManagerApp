package com.example.tutorassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryofTuition extends AppCompatActivity {


    GridLayout gridvenue;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyof_tuition);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationbottom_history);

        gridvenue = (GridLayout) findViewById(R.id.maingridforvenue);

        bottomNavigationView.setSelectedItemId(R.id.history);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.history:

                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(), Add_reminder.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return true;
            }
        });


        setSingleEvent(gridvenue);
    }

        private void setSingleEvent (GridLayout gridvenue)
        {


            //Loop all child item of Main Grid
            for (int i = 0; i < gridvenue.getChildCount(); i++) {
                //You can see , all child item is CardView , so we just cast object to CardView
                CardView cardView = (CardView) gridvenue.getChildAt(i);
                final int finalI = i;
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (finalI == 0) {
                            Intent intent = new Intent(HistoryofTuition.this,MainActivity.class);

                            startActivity(intent);
                        }
                        if (finalI == 1) {
                            Intent intent = new Intent(HistoryofTuition.this, MainActivity.class);

                            startActivity(intent);
                        }
                    }
                });
            }


        }


    }

