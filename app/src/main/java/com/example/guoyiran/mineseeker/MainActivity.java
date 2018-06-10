package com.example.guoyiran.mineseeker;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import static com.example.guoyiran.mineseeker.R.id.gameBorad;

public class MainActivity extends Activity {

    int rowNum = 6;
    int colNum = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        createGameBoard();
    }

    private void createGameBoard() {

        TableLayout gameBoard = (TableLayout) findViewById(gameBorad);

        for(int row = 0;row < rowNum;row++){

            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            gameBoard.addView(newRow);

            for(int col = 0; col < colNum; col++){

                Button newButton = new Button(this);
                newButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                newRow.addView(newButton);
            }


        }


    }
}
