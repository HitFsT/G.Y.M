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
        JSONArray games = Cache.cacheGames(this);
        try{
            for (int i = 0; i < games.length(); i++) {
                if(Gym.getGym_id() == games.getJSONObject(i).getInt("competition_gym_id")){
                    Game game = new Game();
                    game.setName(games.getJSONObject(i).getString("game_name"));
                    game.setGame_id(games.getJSONObject(i).getInt("game_id"));
                    game.setUser_id(RefreshORM.get(this, "user_id"));
                    DialogUtil.showDialog(this,"用户id"+game.getUser_id());
                    DialogUtil.showDialog(this,"Equip_id"+game.getGame_id());
                    game.setStart(games.getJSONObject(i).getString("game_start"));
                    game.setEnd(games.getJSONObject(i).getString("game_end"));
                    game.setSelected(false);
                    gameList.add(game);
                }
            }
        }catch(Exception e){
            DialogUtil.showDialog(this,e.getMessage());
        }

    }

}
