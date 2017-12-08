package com.example.dell.test.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.test.Game.Game;
import com.example.dell.test.Game.GameAdapter;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StudentTimeActivity extends AppCompatActivity {

    private List<Game> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_time);

        initGame();  //game数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_student_time);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GameAdapter adapter = new GameAdapter(gameList);
        recyclerView.setAdapter(adapter);

    }



    private void initGame() {
        for (int i = 0; i < 10; i++) {
            Game game = new Game();
            game.setName("足球比赛");
            game.setAddress("哈工大体育馆");
            game.setStart(10);
            game.setEnd(12);
            gameList.add(game);
        }
    }

}
