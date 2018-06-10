package com.example.guoyiran.mineseeker.model;

public class OptionInfo {

    private int rowNumber;
    private int colNumber;
    private int mineNumber;



    private OptionInfo(){}

    private static OptionInfo instance;
    public static OptionInfo getOptionInfo(){

        if (instance == null){
            instance = new OptionInfo();
        }
        return instance;

    }

    public int getRowNumber() {
        return rowNumber;
    }


    public int getColNumber() {
        return colNumber;
    }


    public int getMineNumber() {
        return mineNumber;
    }

    public void setRowNumber(int rowNumber) {
        instance.rowNumber = rowNumber;
    }


    public void setColNumber(int colNumber) {
        instance.colNumber = colNumber;
    }


    public void setMineNumber(int mineNumber) {
        instance.mineNumber = mineNumber;
    }



}
