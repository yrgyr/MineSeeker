package com.example.guoyiran.mineseeker.model;

import android.view.View;
import android.widget.Button;

import com.example.guoyiran.mineseeker.MainActivity;
import com.example.guoyiran.mineseeker.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {

    private OptionInfo optionInfo = OptionInfo.getOptionInfo();

    private int rowNum = optionInfo.getRowNumber();
    private int colNum = optionInfo.getColNumber();
    private int mineNum = optionInfo.getMineNumber();
    private Button[][] buttons;



    public GameLogic(){
        setRandomMine();

    }
    public GameLogic(Button[][] buttonList){

        buttons = buttonList;
        setRandomMine();


    }

    private void setRandomMine() {



        int[][] printed = new int[rowNum][colNum];

        for(int mine = 0;mine < mineNum;mine++){

            int randRow = ThreadLocalRandom.current().nextInt(0,rowNum);
            int randCol = ThreadLocalRandom.current().nextInt(0,colNum);

            while(printed[randRow][randCol] == 1){

                randRow = ThreadLocalRandom.current().nextInt(0,rowNum);
                randCol = ThreadLocalRandom.current().nextInt(0,colNum);
            }

            final Button littleBtn = buttons[randRow][randCol];
            littleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    littleBtn.setBackgroundResource(R.mipmap.ic_launcher);
                }
            });

            printed[randRow][randCol] = 1;

        }




    }


}
