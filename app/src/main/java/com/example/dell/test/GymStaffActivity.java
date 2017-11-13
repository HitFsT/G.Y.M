package com.example.dell.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GymStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_staff);
    }

    public void Edit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void StaffEquipment(View view) {
        Intent intent = new Intent(this, StaffEquipmentActivity.class);
        startActivity(intent);
    }

    public void StaffGame(View view) {
        Intent intent = new Intent(this, StaffGameActivity.class);
        startActivity(intent);
    }
}
