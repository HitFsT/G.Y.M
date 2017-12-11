package com.example.dell.test.Staff;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class AddGameActivity extends AppCompatActivity {
    EditText game_name, game_start, game_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        Intent get_intent = getIntent();
        int position = get_intent.getIntExtra("position", 0);
        game_name = (EditText) findViewById(R.id.editText_add_game_name);
        game_start = (EditText) findViewById(R.id.editText_add_game_start_time);
        game_end = (EditText) findViewById(R.id.editText_add_game_end_time);
    }

    public void AddGame(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddGameActivity.this);
        String name = game_name.getText().toString().trim();
        String start = game_start.getText().toString().trim();
        String end = game_end.getText().toString().trim();
        insert_game(name, start, end);
        dialog.setTitle("添加信息");
        dialog.setMessage("添加成功");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
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

    public void insert_game(String name, String start, String end){
        Map<String, String> map = new HashMap<>();
        map.put("game_name", name);
        map.put("game_start", name);
        map.put("game_end", name);
        String url = HttpUtil.BASE_URL + "InsertGame";

    }
}
