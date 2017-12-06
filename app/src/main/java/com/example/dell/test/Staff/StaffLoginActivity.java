package com.example.dell.test.Staff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dell.test.R;

public class StaffLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        TextView textview = findViewById(R.id.textView_staff_register);
        textview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StaffLoginActivity.this, StaffRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void StaffLogin(View view) {
        Intent intent = new Intent(this, StaffActivity.class);
        startActivity(intent);
        finish();
    }


}
