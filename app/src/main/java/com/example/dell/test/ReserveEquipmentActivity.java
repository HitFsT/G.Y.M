package com.example.dell.test;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReserveEquipmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_equipment);
        TextView textview = findViewById(R.id.textView_equipment_reservation);
        textview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ReserveEquipmentActivity.this);
                dialog.setTitle("预约信息");
                dialog.setMessage("预约成功");
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
