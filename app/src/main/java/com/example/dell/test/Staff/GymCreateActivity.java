package com.example.dell.test.Staff;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class GymCreateActivity extends AppCompatActivity {
    EditText name , address, phone, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        name = (EditText) findViewById(R.id.editText_gym_create_name);
        address = (EditText) findViewById(R.id.editText_gym_create_address);
        phone = (EditText) findViewById(R.id.editText_gym_create_phone);
        contact = (EditText) findViewById(R.id.editText_gym_create_contact);
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
                String gym_name = name.getText().toString().trim();
                String gym_address = address.getText().toString().trim();
                String gym_phone = phone.getText().toString().trim();
                String gym_contact = contact.getText().toString().trim();
                try{
                    add_gym(gym_name, gym_address, gym_phone, gym_contact);
                    RefreshORM.settrue(GymCreateActivity.this, "gym");
                }catch (Exception e){
                    DialogUtil.showDialog(GymCreateActivity.this, e.getMessage());
                }

//                finish();
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
                    finish();
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

    private JSONObject add_gym(String name, String address, String phone, String contact) throws Exception{
        Map<String, String> map = new HashMap<>();
        /* 1 means add */
        map.put("operation", valueOf(1));
        map.put("name", name);
        map.put("address", address);
        map.put("phone", phone);
        map.put("contact", contact);
        String url = HttpUtil.BASE_URL + "EditGym";
        return new JSONObject(HttpUtil.postRequest(url, map));
    }
}
