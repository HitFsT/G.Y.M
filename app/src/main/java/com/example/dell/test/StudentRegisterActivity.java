package com.example.dell.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StudentRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
    }

    public void StudentRegister(View view) {
        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
    }
}
