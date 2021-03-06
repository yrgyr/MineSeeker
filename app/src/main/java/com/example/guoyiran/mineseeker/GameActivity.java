package com.example.guoyiran.mineseeker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
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

    /*
    =========================================================================
    this is the game play activity, this activity include create game board
    and set onclicklistener for buttons. this game logic is in a separate file.
    has sound effect when scan and reveal planets.
    =============================================================================
     */

    OptionInfo optionInfo = OptionInfo.getOptionInfo();
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

        // ------------start a new game
        GameLogic newGame = new GameLogic(rowNum,colNum,mineNum);

        numberTable = new int[rowNum][colNum];
        numberTable = newGame.getCellNum();
        // 1 or 0 , cell with mine is 1.
        checkTable = new int[rowNum][colNum];
        checkTable = newGame.getPrinted();

        buttonTable = new Button[rowNum][colNum];
        visitTable = new boolean[rowNum][colNum];
        // ----------generate game board----------------------
        createGameBoard(rowNum,colNum);
        setBtnOnclick();

        updateTextView();
        updateScanCountTV();

    }

    // ============ Generate game board with buttons ===============================

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
    //================ Set onclickListener for buttons ======================================
    private void setBtnOnclick() {


        final MediaPlayer score_sound = MediaPlayer.create(this,R.raw.score_sound);
        for (int row = 0; row < rowNum; row++){
            for(int col = 0; col < colNum; col++){

                final Button littleBtn = buttonTable[row][col];
                final int num = numberTable[row][col];

                if(checkTable[row][col] == 1){

                    final int currentRow = row;
                    final int currentCol = col;

                    littleBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lockButtonSizes();
                            int newWidth = littleBtn.getWidth();
                            int newHeight = littleBtn.getHeight();
                            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.main_menu_image);
                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                            Resources resource = getResources();
                            littleBtn.setBackground(new BitmapDrawable(resource, scaledBitmap));

                            score_sound.start();
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
    // =================== helper function for setBtnOnclick(), display numbers for each cell when click ===============
    private void onclickDisplayNumOnCell(int row,int col){

        final int thisRow = row;
        final int thisCol = col;
        final MediaPlayer scan_sound = MediaPlayer.create(this,R.raw.scan_sound);
        final Button clickedBtn = buttonTable[row][col];
        clickedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean visited = visitTable[thisRow][thisCol];
                if(visited == false){
                    int num = numberTable[thisRow][thisCol];
                    clickedBtn.setText(getString(R.string.number_on_cell,num));
                    scan_sound.start();
                    countScan++;
                    updateScanCountTV();
//                    clickedBtn.setEnabled(false);
                    visitTable[thisRow][thisCol] = true;
                }
            }
        });

    }

    // ================ lock button size when display image background =========================
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

    // ===== helper function, update numbers for corresponding cell when reveal a planet============
    private void updateNumberTable(int row, int col) {

        for(int theCol = 0;theCol< colNum; theCol++){

            numberTable[row][theCol]--;

            if(visitTable[row][theCol] == true){
                int newNum = numberTable[row][theCol];
                Button updatedButtons = buttonTable[row][theCol];
                updatedButtons.setText(getString(R.string.update_number,newNum));
            }
        }
        for(int theRow = 0;theRow<rowNum;theRow++){

            numberTable[theRow][col]--;
            if(visitTable[theRow][col] == true){
                int newNum = numberTable[theRow][col];
                Button updatedButtons = buttonTable[theRow][col];
                updatedButtons.setText(getString(R.string.update_number,newNum));
            }
        }
        numberTable[row][col]++;
    }
    // ======= display dialog when reveal all the planets ===============================
    private void winnerChecking() {

        if(found == mineNum){
            android.support.v4.app.FragmentManager frag = getSupportFragmentManager();
            winningDialog popup = new winningDialog();
            popup.setCancelable(false);
            popup.show(frag,"hello world");

        }
    }
    // ==================update textView on left corner of screen ===============================
    private void updateTextView() {
        TextView mineInfo = (TextView) findViewById(R.id.mineInfo);
        mineInfo.setText(getString(R.string.num_mines_found,found,mineNum));
    }

    // =================== update textView on right corner of screen =============================
    private void updateScanCountTV(){

        TextView scanNum = (TextView) findViewById(R.id.scanNum);
        scanNum.setText(getString(R.string.scanned_num,countScan));
    }
}
