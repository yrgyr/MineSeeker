package com.example.guoyiran.mineseeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    // this class has three buttons for jump into different screens



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playGameBtnOnclick();
        optionBtnOnclick();
        helpBtnOnclick();
    }
    private void playGameBtnOnclick() {
        Button playGameBtn = (Button) findViewById(R.id.playGameBtn);
        playGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,GameActivity.class);
                startActivity(intent);
            }
        });
    }
    private void optionBtnOnclick() {
        Button optionBtn = (Button) findViewById(R.id.optionBtn);
        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,OptionScreen.class);
                startActivity(intent);
            }
        });

    }
    private void helpBtnOnclick(){

        Button helpBtn = (Button) findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,HelpScreen.class);
                startActivity(intent);
            }
        });
    }
}
