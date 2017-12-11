package com.example.dell.test.Staff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.R;

public class GymStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_staff);

        Intent get_intent = getIntent();
        int position = get_intent.getIntExtra("position", 0);

        ImageView gym_image = (ImageView) findViewById(R.id.imageView_gym_staff_image);
        TextView gym_name = (TextView) findViewById(R.id.textView_gym_staff_name);
        TextView gym_address = (TextView) findViewById(R.id.textView_gym_staff_address);
        TextView gym_contact = (TextView) findViewById(R.id.textView_gym_staff_contact);

        /******************这里用position set GYMSTAFF界面  参考GYMSTUDENT********************/
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            finish();
        }
        return false;
    }

}
