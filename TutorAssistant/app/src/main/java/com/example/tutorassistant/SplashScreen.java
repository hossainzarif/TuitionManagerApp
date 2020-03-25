package com.example.tutorassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    Animation topanim ,botanim ;
    ImageView img1,img2 ;
    private static  int SPLASH_SCREEN = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        topanim = AnimationUtils.loadAnimation(this,R.anim.upper_anim) ;
        botanim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim) ;

        img1 = findViewById(R.id.imageView);
        img2=findViewById(R.id.imageView1);


        img1.setAnimation(topanim);
        img2.setAnimation(botanim);




    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent (SplashScreen.this,Add_reminder.class);
            startActivity(intent);
            finish();
        }
    },SPLASH_SCREEN);
    }
}
