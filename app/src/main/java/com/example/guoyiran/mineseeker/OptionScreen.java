package com.example.guoyiran.mineseeker;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

//        setPopUpWindow();
//        android:theme="@style/AppTheme.PopUpWindow"

        setRadioBtn();
    }

    private void setRadioBtn() {
        RadioGroup boardSize = (RadioGroup) findViewById(R.id.boardSize);
        RadioGroup mineNum = (RadioGroup) findViewById(R.id.mineNum);

        int[] rowNum = getResources().getIntArray(R.array.rowNum);
        int[] colNum = getResources().getIntArray(R.array.colNum);
        int[] mineArray = getResources().getIntArray(R.array.mineNum);

        for(int i = 0; i < rowNum.length; i++){

            int row = rowNum[i];
            int col = colNum[i];
            RadioButton littleBtn= new RadioButton(this);
            littleBtn.setText(row + " row by " + col + " col ");
            boardSize.addView(littleBtn);
        }

        for (int i = 0; i < mineArray.length; i++){

            int mine = mineArray[i];
            RadioButton littleMine = new RadioButton(this);
            littleMine.setText(mine + " mines ");
            mineNum.addView(littleMine);

        }
    }

    private void setPopUpWindow() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.6),(int) (height*0.8));
    }
}
