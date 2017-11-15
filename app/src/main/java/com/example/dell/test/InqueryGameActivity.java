package com.example.dell.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InqueryGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquery_game);
        TextView textview = findViewById(R.id.textView_game_reservation);
        textview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(InqueryGameActivity.this);
                dialog.setTitle("预订信息");
                dialog.setMessage("预定成功");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.
                        OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)  {
                    }
                });

                dialog.show();
            }
        });
    }
}
