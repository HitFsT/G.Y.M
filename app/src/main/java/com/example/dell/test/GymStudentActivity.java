package com.example.dell.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GymStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_student);
    }

    public void ReserveEquipment(View view) {
        Intent intent = new Intent(this, ReserveEquipmentActivity.class);
        startActivity(intent);
    }

    public void InqueryGame(View view) {
        Intent intent = new Intent(this, InqueryGameActivity.class);
        startActivity(intent);
    }
}
