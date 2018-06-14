package com.example.guoyiran.mineseeker.model;

import android.view.View;
import android.widget.Button;

import com.example.guoyiran.mineseeker.MainActivity;
import com.example.guoyiran.mineseeker.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {

    private OptionInfo optionInfo = OptionInfo.getOptionInfo();

    private int rowNum;
    private int colNum;
    private int mineNum;

    //why i can not initialize it globally
    private int[][] printed;
    private int[][] cellNumList;


    public int[][] getCellNum() {
        return cellNumList;
    }

    public int[][] getPrinted() {
        return printed;
    }

    public GameLogic(int rows,int cols,int mines){

        rowNum = rows;
        colNum = cols;
        mineNum = mines;
        setRandomMine();
        setCellInfo();
    }
    private void setRandomMine() {

        printed = new int[rowNum][colNum];
        for(int mine = 0;mine < mineNum;mine++){

            int randRow = ThreadLocalRandom.current().nextInt(0,rowNum);
            int randCol = ThreadLocalRandom.current().nextInt(0,colNum);

            while(printed[randRow][randCol] == 1){

                randRow = ThreadLocalRandom.current().nextInt(0,rowNum);
                randCol = ThreadLocalRandom.current().nextInt(0,colNum);
            }
            printed[randRow][randCol] = 1;
        }
    }
    private void setCellInfo() {
        //for every cell
        cellNumList = new int[rowNum][colNum];
        for(int row = 0; row < rowNum; row++){
            for (int col = 0;col<colNum; col++){
                cellNumList[row][col] = scanItsRowAndCol(row,col);

            }

        }
    }
    private int scanItsRowAndCol(int currentRow,int currentCol) {

        int countMine = 0;
        for (int col = 0; col < colNum;col++){
            if (printed[currentRow][col] == 1){
                countMine++; } }
        for (int row = 0; row < rowNum;row++){
            if (printed[row][currentCol] == 1){
                countMine++; } }
        if(printed[currentRow][currentCol] == 1){
            countMine--; }
        return countMine;
    }


    private void mineFound(Button btn){




    }
}
