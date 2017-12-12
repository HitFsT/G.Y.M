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

public class EditActivity extends AppCompatActivity {
    EditText name, address, phone, contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        DialogUtil.showDialog(EditActivity.this, valueOf(Gym.getGym_id()));
        name = (EditText) findViewById(R.id.editText_gym_edit_name);
        address = (EditText) findViewById(R.id.editText_gym_edit_address);
        phone = (EditText) findViewById(R.id.editText_gym_edit_phone);
        contact = (EditText) findViewById(R.id.editText_gym_edit_contact);
    }
    public void Edit(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EditActivity.this);
        dialog.setTitle("修改信息");
        dialog.setMessage("修改成功");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            public void onClick(DialogInterface dialog, int which)  {
                String gym_name = name.getText().toString().trim();
                String gym_address = address.getText().toString().trim();
                String gym_phone = phone.getText().toString().trim();
                String gym_contact = contact.getText().toString().trim();
                try{
                    update_gym(gym_name, gym_address, gym_phone, gym_contact);
                    RefreshORM.settrue(EditActivity.this, "gym");
                }catch(Exception e){
                    DialogUtil.showDialog(EditActivity.this, e.getMessage());
                }

                finish();
            }
        });
        dialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(EditActivity.this);
            dialog.setTitle("退出修改");
            dialog.setMessage("确定取消修改吗?");
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

    private JSONObject update_gym(String gym_name, String gym_address, String gym_phone, String gym_contact) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("gym_id", valueOf(Gym.getGym_id()));
        map.put("gym_name", gym_name);
        map.put("gym_address", gym_address);
        map.put("gym_phone", gym_phone);
        map.put("gym_contact", gym_contact);
        String url = HttpUtil.BASE_URL + "UpdateGym";
        return new JSONObject(HttpUtil.postRequest(url, map));
    }


}
