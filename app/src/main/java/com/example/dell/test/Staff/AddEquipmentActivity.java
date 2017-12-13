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
import com.example.dell.test.Http.GymORM;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class AddEquipmentActivity extends AppCompatActivity {
    EditText name, start, end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);
        DialogUtil.showDialog(this, valueOf(Gym.getGym_id()));
        name = (EditText) findViewById(R.id.editText_add_equipment_name);
        start = (EditText) findViewById(R.id.editText_add_equipment_start_time);
        end = (EditText) findViewById(R.id.editText_add_equipment_end_time);
    }

    public void AddEquipment(View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(AddEquipmentActivity.this);
        dialog.setTitle("添加信息");
        dialog.setMessage("添加成功");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String equip_name = name.getText().toString().trim();
                String equip_start = start.getText().toString().trim();
                String equip_end = end.getText().toString().trim();

                try{
                    String status = add_equip(equip_name, equip_start, equip_end);
                    DialogUtil.showDialog(AddEquipmentActivity.this, status);
                }catch (Exception e){
                    DialogUtil.showDialog(AddEquipmentActivity.this, e.getMessage());
                }
//                finish();
            }
        });
        dialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddEquipmentActivity.this);
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

    private String add_equip(String equip_name, String equip_start, String equip_end) throws Exception{
        Map<String, String> map = new HashMap<>();
        /* 1 means add */
        map.put("operation", "1");
        map.put("gym_id", valueOf(Gym.getGym_id()));
        map.put("name", equip_name);
        DialogUtil.showDialog(this, "你要插入的设施名为："+equip_name);
        map.put("start", equip_start);
        map.put("end", equip_end);
        String url = HttpUtil.BASE_URL + "EditEquip";
        return HttpUtil.postRequest(url, map);
    }

}
