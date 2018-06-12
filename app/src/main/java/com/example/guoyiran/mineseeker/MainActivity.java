package com.example.guoyiran.mineseeker;

import android.app.Activity;
import android.widget.Button;

import com.example.guoyiran.mineseeker.model.GameLogic;
import com.example.guoyiran.mineseeker.model.OptionInfo;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static com.example.guoyiran.mineseeker.R.id.gameBorad;

public class MainActivity extends Activity {


    OptionInfo optionInfo = OptionInfo.getOptionInfo();
    private int rowNum = optionInfo.getRowNumber();
    private int colNum = optionInfo.getColNumber();
    private int mineNum = optionInfo.getMineNumber();
    private Button[][] buttonTable = new Button[rowNum][colNum];


    private int [][] numberTable;
    private int [][] checkTable;
    private boolean [][] visitTable;

    private int countScan = 0;

    private int found = 0;
    private GameLogic newGame;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        GameLogic newGame = new GameLogic(rowNum,colNum,mineNum);

        // number on each cell
        numberTable = new int[rowNum][colNum];
        numberTable = newGame.getCellNum();


        // 1 or 0 , cell with mine is 1.
        checkTable = new int[rowNum][colNum];
        checkTable = newGame.getPrinted();


        visitTable = new boolean[rowNum][colNum];

        createGameBoard(rowNum,colNum);
        setBtnOnclick();
        updateTextView();
        updateScanCountTV();

    }

    private void updateScanCountTV(){

        TextView scanNum = (TextView) findViewById(R.id.scanNum);
        scanNum.setText("scaned "+ countScan + " time");
    }


    private void createGameBoard(int rowNum,int colNum) {
        TableLayout gameBoard = (TableLayout) findViewById(gameBorad);
        for( int row = 0; row < rowNum; row++){

            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            gameBoard.addView(newRow);
            for(int col = 0; col < colNum; col++){


                final Button newButton = new Button(this);
                // set layout
                newButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                newButton.setPadding(0,0,0,0);
                buttonTable[row][col] = newButton;
                newRow.addView(newButton);
            }

        }



    }

    private void setBtnOnclick() {


        for (int row = 0; row < rowNum; row++){
            for(int col = 0; col < colNum; col++){

                final Button littleBtn = buttonTable[row][col];
                final int num = numberTable[row][col];
                final boolean VISITED = visitTable[row][col];
                // if the cell has mine
                if(checkTable[row][col] == 1){

                    final int currentRow = row;
                    final int currentCol = col;

                    littleBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            littleBtn.setBackgroundResource(R.mipmap.ic_launcher);
                            found++;
                            updateTextView();
                            if(VISITED == false){

//                                onclickDisplayNumOnCell(currentRow,currentCol);
                                visitTable[currentRow][currentCol] = true;

                            }

                        }
                    });

                }
                else{ //if is a normal cell

                    if (VISITED == false){
                        littleBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                littleBtn.setText(num+ "");
                                countScan++;
                                updateScanCountTV();
                            }
                        });
                        visitTable[row][col] = true;
                    }

                }

            }
        }

    }

//    private void onclickDisplayNumOnCell(int row,int col){
//
//            final int num = numberTable[row][col];
//            final Button clickedBtn = buttonTable[row][col];
//
//
//            clickedBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    clickedBtn.setText(num+ "");
//                    countScan++;
//                    updateScanCountTV();
//                }
//            });
//    }
    private void updateTextView() {
        TextView mineInfo = (TextView) findViewById(R.id.mineInfo);
        mineInfo.setText("Found "+ found+ " of" + mineNum + " mines");
    }
}
