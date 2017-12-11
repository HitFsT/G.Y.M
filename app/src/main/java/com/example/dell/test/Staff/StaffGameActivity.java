package com.example.dell.test.Staff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.test.Equipment.Equipment;
import com.example.dell.test.Equipment.StaffEquipmentAdapter;
import com.example.dell.test.Game.Game;
import com.example.dell.test.Game.StaffGameAdapter;
import com.example.dell.test.R;

import java.util.ArrayList;
import java.util.List;

public class StaffGameActivity extends AppCompatActivity {

    private List<Game> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_game);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_staff);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("我的比赛");

        initGame();  //game数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_staff_game);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StaffGameAdapter adapter = new StaffGameAdapter(gameList);
        adapter.setActivity(StaffGameActivity.this);
        recyclerView.setAdapter(adapter);

        ImageView imageview = findViewById(R.id.imageView_add);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StaffGameActivity.this, AddGameActivity.class);
                startActivity(intent);
            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            finish();
        }
        return false;
    }

    public void initGame() {
        Game game = new Game();
        game.setName("足球");
        game.setStart("1");
        game.setEnd("5");
        gameList.add(game);
    }
}
