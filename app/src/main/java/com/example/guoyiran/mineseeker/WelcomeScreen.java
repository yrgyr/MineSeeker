package com.example.guoyiran.mineseeker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    Handler TIME_OUT_HANDLER;
    Runnable myRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        myRun = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeScreen.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        };
        autoAdvance();
        skipBtnOnclick();
    }

    private void autoAdvance() {

        int timeOut = 4000;

        TIME_OUT_HANDLER = new Handler();


        TIME_OUT_HANDLER.postDelayed(myRun,timeOut);

    }

    private void skipBtnOnclick() {

        Button skipBtn = (Button) findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, MainMenu.class);
                startActivity(intent);
                TIME_OUT_HANDLER.removeCallbacks(myRun);
                finish();
            }
        });
    }
}
