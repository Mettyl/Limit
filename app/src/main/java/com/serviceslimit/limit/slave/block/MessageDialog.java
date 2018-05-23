package com.serviceslimit.limit.slave.block;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.serviceslimit.limit.R;

public class MessageDialog extends DialogFragment {

    public MessageDialog() {
        // Null required
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View content = inflater.inflate(R.layout.message_dialog_layout, null);

        final EditText editText = content.findViewById(R.id.message_dialog_et);

        builder.setView(content)

                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        MessageDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
