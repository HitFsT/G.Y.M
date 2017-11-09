package com.example.dell.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StaffRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_register);
    }

    public void StaffRegister(View view) {
        Intent intent = new Intent(this, StaffLoginActivity.class);
        startActivity(intent);
    }
}
