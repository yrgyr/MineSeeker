package com.example.guoyiran.mineseeker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toast;

import com.example.guoyiran.mineseeker.model.OptionInfo;

public class OptionScreen extends AppCompatActivity {


    private OptionInfo optionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

//        setPopUpWindow();
//        android:theme="@style/AppTheme.PopUpWindow"

        optionInfo = OptionInfo.getOptionInfo();

        setRadioBtn();


        int savedRow = getRowNumberSaved(this);
        int savedCol = getColNumberSaved(this);
        int saveMine = getMineNumberSaved(this);
        Toast.makeText(this,"saved "+savedRow + "row, " + savedCol + " col" + ",and "+ saveMine + " mines",Toast.LENGTH_SHORT)
                .show();
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

            RadioButton sizeBtn= new RadioButton(this);
            sizeBtn.setText(row + " row by " + col + " col ");


            sizeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(OptionScreen.this,"you selected " + row + " rows, " + col + " columns ",Toast.LENGTH_LONG)
//                            .show();

                    optionInfo.setRowNumber(row);
                    optionInfo.setColNumber(col);
                    saveSize(optionInfo);
                }
            });
            boardSize.addView(sizeBtn);

            if(row == getRowNumberSaved(this)&& col == getColNumberSaved(this)){
                optionInfo.setRowNumber(row);
                optionInfo.setColNumber(col);
                sizeBtn.setChecked(true);
            }

        }

        for (int i = 0; i < mineArray.length; i++){

            final int mine = mineArray[i];
            RadioButton littleMine = new RadioButton(this);
            littleMine.setText(mine + " mines ");
            littleMine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(OptionScreen.this,"you selected " + mine + " Mines",Toast.LENGTH_LONG).show();
                    optionInfo.setMineNumber(mine);
                    saveMine(optionInfo);
                }
            });

            mineNum.addView(littleMine);
            if(mine == getMineNumberSaved(this)){
                optionInfo.setMineNumber(mine);
                littleMine.setChecked(true);

            }



        }

    }

    // =========================Methods for saving values ==========================================
   private void saveSize(OptionInfo Info) {

        SharedPreferences sizeShare = this.getSharedPreferences("sizeShare",MODE_PRIVATE);
        SharedPreferences.Editor editor = sizeShare.edit();

        editor.putInt("row number",Info.getRowNumber());
        editor.putInt("col number",Info.getColNumber());
        editor.apply();
    }

    private void saveMine(OptionInfo Info) {

        SharedPreferences mineShare = this.getSharedPreferences("mineShare",MODE_PRIVATE);
        SharedPreferences.Editor editor = mineShare.edit();

        editor.putInt("mine number",Info.getMineNumber());
        editor.apply();
    }

    // =========================== Methods for getting saved values ======================================

    static public int getRowNumberSaved(Context context){
        SharedPreferences share = context.getSharedPreferences("sizeShare",MODE_PRIVATE);

        int default_row = context.getResources().getInteger(R.integer.default_row);
        return share.getInt("row number",default_row);

    }

    static public int getColNumberSaved(Context context){
        SharedPreferences share = context.getSharedPreferences("sizeShare",MODE_PRIVATE);

        int default_col = context.getResources().getInteger(R.integer.default_col);
        return share.getInt("col number",default_col);

    }


    static public int getMineNumberSaved(Context context){
        SharedPreferences share = context.getSharedPreferences("mineShare",MODE_PRIVATE);

        int default_mine = context.getResources().getInteger(R.integer.default_mine);
        return share.getInt("mine number",default_mine);

    }

}
