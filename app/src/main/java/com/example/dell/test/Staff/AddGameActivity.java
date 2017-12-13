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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class AddGameActivity extends AppCompatActivity {
    EditText game_name, game_start, game_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        Intent get_intent = getIntent();
        int position = get_intent.getIntExtra("position", 0);
        DialogUtil.showDialog(this, valueOf(Gym.getGym_id()));
        game_name = (EditText) findViewById(R.id.editText_add_game_name);
        game_start = (EditText) findViewById(R.id.editText_add_game_start_time);
        game_end = (EditText) findViewById(R.id.editText_add_game_end_time);
    }

    public void AddGame(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddGameActivity.this);
        dialog.setTitle("添加信息");
        dialog.setMessage("添加成功");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String name = game_name.getText().toString().trim();
                String start = game_start.getText().toString().trim();
                String end = game_end.getText().toString().trim();
                try{
                    add_game(name, start, end);
                    RefreshORM.settrue(AddGameActivity.this, "game");
                }catch (Exception e){
                    DialogUtil.showDialog(AddGameActivity.this, e.getMessage());
                }
//                finish();
            }
        });
        dialog.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddGameActivity.this);
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

    private String add_game(String name, String start, String end) throws Exception{
        Map<String, String> map = new HashMap<>();
        /* 1 means add */
        map.put("operation", valueOf(1));
        map.put("gym_id", valueOf(Gym.getGym_id()));
        map.put("name", name);
        map.put("start", start);
        map.put("end", end);
        String url = HttpUtil.BASE_URL + "EditGame";
        return  HttpUtil.postRequest(url, map);
    }

}
