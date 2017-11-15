package com.example.dell.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class GymCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_create);
    }

    public void GymCreate (View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(GymCreateActivity.this);
        dialog.setTitle("创建信息");
        dialog.setMessage("创建成功");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            public void onClick(DialogInterface dialog, int which)  {
                Intent intent = new Intent(GymCreateActivity.this, StaffActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(GymCreateActivity.this);
            dialog.setTitle("退出添加");
            dialog.setMessage("确定取消添加吗?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which)  {
                    Intent intent = new Intent(GymCreateActivity.this, StaffActivity.class);
                    startActivity(intent);
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which)  {
                }
            });
            dialog.show();
        }
        return false;
    }
}
