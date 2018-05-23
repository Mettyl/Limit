package com.serviceslimit.limit.slave.block;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.serviceslimit.limit.R;


public class BlockingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
    }

    /**
     * Starts message dialog on button click.
     *
     * @param view
     */
    public void askForPermissionButton(View view) {

        DialogFragment dialogFragment = new MessageDialog();
        dialogFragment.show(getSupportFragmentManager(), "message_dialog");
    }

    /**
     * Closes activity on button click.
     *
     * @param view
     */
    public void closeButton(View view) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(startMain);
        finish();
    }
}
