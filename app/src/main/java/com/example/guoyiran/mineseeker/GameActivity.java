package com.example.guoyiran.mineseeker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
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

public class GameActivity extends AppCompatActivity {


    OptionInfo optionInfo = OptionInfo.getOptionInfo();


//    private int rowNum = optionInfo.getRowNumber();
//    private int colNum = optionInfo.getColNumber();
//    private int mineNum = optionInfo.getMineNumber();



    private int rowNum;
    private int colNum;
    private int mineNum;

    private Button[][] buttonTable;

    private int [][] numberTable;
    private int [][] checkTable;
    private boolean [][] visitTable;

    private int countScan = 0;
    private int found = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        rowNum = OptionScreen.getRowNumberSaved(GameActivity.this);
        colNum = OptionScreen.getColNumberSaved(GameActivity.this);
        mineNum = OptionScreen.getMineNumberSaved(GameActivity.this);
        GameLogic newGame = new GameLogic(rowNum,colNum,mineNum);



        // number on each cell
        numberTable = new int[rowNum][colNum];
        numberTable = newGame.getCellNum();

        // 1 or 0 , cell with mine is 1.
        checkTable = new int[rowNum][colNum];
        checkTable = newGame.getPrinted();

        buttonTable = new Button[rowNum][colNum];
        visitTable = new boolean[rowNum][colNum];


        createGameBoard(rowNum,colNum);
        setBtnOnclick();
        updateTextView();
        updateScanCountTV();

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

//                int width = littleBtn.getWidth();
//                littleBtn.setMinWidth(width);
//                littleBtn.setMaxWidth(width);
//
//                int height = littleBtn.getHeight();
//                littleBtn.setMinHeight(height);
//                littleBtn.setMaxHeight(height);

                // if the cell has mine
                if(checkTable[row][col] == 1){

                    final int currentRow = row;
                    final int currentCol = col;

                    littleBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lockButtonSizes();
                            int newWidth = littleBtn.getWidth();
                            int newHeight = littleBtn.getHeight();
                            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.winner_image);
                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                            Resources resource = getResources();
                            littleBtn.setBackground(new BitmapDrawable(resource, scaledBitmap));

                            found++;
                            winnerChecking();
                            updateTextView();
//                            littleBtn.setBackgroundResource(R.mipmap.ic_launcher_round);
                            updateNumberTable(currentRow,currentCol);
                            onclickDisplayNumOnCell(currentRow,currentCol);
                        }
                    });
                }
                else{ //if is a normal cell
                    onclickDisplayNumOnCell(row,col);
                }

            }
        }

    }

    private void lockButtonSizes() {
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                Button button = buttonTable[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }


    private void updateNumberTable(int row, int col) {

        for(int theCol = 0;theCol< colNum; theCol++){

            numberTable[row][theCol]--;

            if(visitTable[row][theCol] == true){
                int newNum = numberTable[row][theCol];
                Button updatedButtons = buttonTable[row][theCol];
                updatedButtons.setText(newNum+"");
            }
        }

        for(int theRow = 0;theRow<rowNum;theRow++){

            numberTable[theRow][col]--;

            if(visitTable[theRow][col] == true){

                int newNum = numberTable[theRow][col];
                Button updatedButtons = buttonTable[theRow][col];
                updatedButtons.setText(newNum+"");
            }
        }
        numberTable[row][col]++;
    }



    private void onclickDisplayNumOnCell(int row,int col){

        final int thisRow = row;
        final int thisCol = col;

        final Button clickedBtn = buttonTable[row][col];
        clickedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean visited = visitTable[thisRow][thisCol];
                if(visited == false){
                    int num = numberTable[thisRow][thisCol];
                    clickedBtn.setText(num+ "");
                    countScan++;
                    updateScanCountTV();
//                    clickedBtn.setEnabled(false);
                    visitTable[thisRow][thisCol] = true;
                }
            }
        });

    }

    private void winnerChecking() {

        if(found == mineNum){

            android.support.v4.app.FragmentManager frag = getSupportFragmentManager();
            winningDialog popup = new winningDialog();
//            popup.setCancelable(false);
            popup.show(frag,"hello world");

        }
    }


    private void updateTextView() {
        TextView mineInfo = (TextView) findViewById(R.id.mineInfo);
        mineInfo.setText("Found "+ found+ " of " + mineNum + " mines");
    }
    private void updateScanCountTV(){

        TextView scanNum = (TextView) findViewById(R.id.scanNum);
        scanNum.setText("scaned "+ countScan + " time");
    }
}
