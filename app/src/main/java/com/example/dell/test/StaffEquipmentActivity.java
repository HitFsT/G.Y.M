package com.example.dell.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class StaffEquipmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_equipment);
    }

    public void StaffEquipment(View view) {
        Intent intent = new Intent(StaffEquipmentActivity.this, AddEquipmentActivity.class);
        startActivity(intent);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            Intent intent = new Intent(StaffEquipmentActivity.this, GymStaffActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
