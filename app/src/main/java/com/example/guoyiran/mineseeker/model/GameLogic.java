package com.example.guoyiran.mineseeker.model;

import android.widget.Button;

import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {



    /* this is a class for game logic,
        class can generate random planets position in the game board, stored in a 2D array
        and generate numbers on each cell, also store in a 2D array
    */




    private OptionInfo optionInfo = OptionInfo.getOptionInfo();
    private int rowNum;
    private int colNum;
    private int mineNum;

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
}
