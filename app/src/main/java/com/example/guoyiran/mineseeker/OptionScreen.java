package com.example.guoyiran.mineseeker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toast;

public class OptionScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

//        setPopUpWindow();
//        android:theme="@style/AppTheme.PopUpWindow"

        setRadioBtn();
    }


    private void setPopUpWindow() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.6),(int) (height*0.8));
    }
    
    private void setRadioBtn() {
        RadioGroup boardSize = (RadioGroup) findViewById(R.id.boardSize);
        RadioGroup mineNum = (RadioGroup) findViewById(R.id.mineNum);

        int[] rowNum = getResources().getIntArray(R.array.rowNum);
        int[] colNum = getResources().getIntArray(R.array.colNum);
        int[] mineArray = getResources().getIntArray(R.array.mineNum);

        for(int i = 0; i < rowNum.length; i++){
            final int row = rowNum[i];
            final int col = colNum[i];

            RadioButton littleBtn= new RadioButton(this);
            littleBtn.setText(row + " row by " + col + " col ");
            boardSize.addView(littleBtn);

            littleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionScreen.this,"you selected " + row + " rows, " + col + " columns ",Toast.LENGTH_LONG).show();
                    saveBoardSize(row,col);
                }
            });

        }

        for (int i = 0; i < mineArray.length; i++){

            final int mine = mineArray[i];
            RadioButton littleMine = new RadioButton(this);
            littleMine.setText(mine + " mines ");
            mineNum.addView(littleMine);
            littleMine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionScreen.this,"you selected " + mine + " Mines",Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private void saveBoardSize(int row, int col) {

        SharedPreferences share = this.getSharedPreferences("share",MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt("board size",0);
        editor.apply();
    }

    static public int getBoardSize(Context context){
        return 0;

    }




}
