package com.example.guoyiran.mineseeker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class winningDialog extends AppCompatDialogFragment {

    /* ===============================================================
        this is the dialog window for winning message.
        it will be pop up automatically when all planets are revealed.
        ===============================================================
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.winning_dialog,null);


        Button littleBtn = (Button) v.findViewById(R.id.okBtn);

        littleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });
        return new AlertDialog.Builder(getActivity()).setView(v).create();
    }
}
