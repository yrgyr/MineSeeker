package com.example.guoyiran.mineseeker;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.guoyiran.mineseeker.model.GameLogic;
import com.example.guoyiran.mineseeker.model.OptionInfo;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.guoyiran.mineseeker.R.id.gameBorad;

public class MainActivity extends Activity {


    OptionInfo optionInfo = OptionInfo.getOptionInfo();
    private int rowNum = optionInfo.getRowNumber();
    private int colNum = optionInfo.getColNumber();
    private int mineNum = optionInfo.getMineNumber();
    public Button[][] buttons = new Button[rowNum][colNum];


    private GameLogic newGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        updateTextView();

        createGameBoard(rowNum,colNum);
        GameLogic newGame = new GameLogic(rowNum,colNum,mineNum,buttons);

    }

    private void updateTextView() {
        TextView mineInfo = (TextView) findViewById(R.id.mineInfo);
        mineInfo.setText("There are "+ mineNum + " mines");
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

                final int currentRow = row;
                final int currentCol = col;
                final Button newButton = new Button(this);
                // set layout
                newButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                newButton.setPadding(0,0,0,0);
                //set onclickListener
//                newButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        newButton.setText(currentRow + ","+currentCol);
//                    }
//                });
                buttons[currentRow][currentCol] = newButton;
                newRow.addView(newButton);
            }

        }

    }
}
