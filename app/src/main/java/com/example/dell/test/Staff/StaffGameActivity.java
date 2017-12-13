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
import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Http.Cache;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.example.dell.test.Http.Cache.getGames;

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

    private void initGame() {
        try{
            JSONArray games = Cache.getGames();
            for (int i = 0; i < games.length(); i++) {
                if(games.getJSONObject(i).getInt("competition_gym_id") == Gym.getGym_id()) {
                    try{
                        Game game = new Game();
                        game.setName(games.getJSONObject(i).getString("competition_name"));
                        DialogUtil.showDialog(this, "比赛名字"+game.getName());
                        game.setGame_id(games.getJSONObject(i).getInt("competition_id"));
                        DialogUtil.showDialog(this, "比赛id"+game.getGame_id());
                        game.setUser_id(RefreshORM.get(this, "user_id"));
                        DialogUtil.showDialog(this, "比赛用户id"+game.getUser_id());
                        game.setStart(games.getJSONObject(i).getString("competition_start"));
                        DialogUtil.showDialog(this, "比赛开始时间"+game.getStart());
                        game.setEnd(games.getJSONObject(i).getString("competition_end"));
                        DialogUtil.showDialog(this, "比赛结束时间"+game.getEnd());
                        game.setSelected(false);
                        gameList.add(game);
                    }catch (Exception e){
                        DialogUtil.showDialog(this, e.getMessage());
                    }

                }
            }
        }catch(Exception e){
            DialogUtil.showDialog(this,e.getMessage());
        }

    }

}
