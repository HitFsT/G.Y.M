package com.example.dell.test.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dell.test.R;
import com.example.dell.test.Staff.StaffLoginActivity;
import com.example.dell.test.Student.StudentLoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.dell.test.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StudentLogin(View view) {
        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
    }

    public void StaffLogin(View view) {
        Intent intent = new Intent(this, StaffLoginActivity.class);
        startActivity(intent);
    }
}
