package com.example.dell.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class StaffGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_game);
    }

    public void StaffGame(View view) {
        Intent intent = new Intent(StaffGameActivity.this, AddGameActivity.class);
        startActivity(intent);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            Intent intent = new Intent(StaffGameActivity.this, GymStaffActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
