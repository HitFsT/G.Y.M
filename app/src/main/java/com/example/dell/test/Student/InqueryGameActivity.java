package com.example.dell.test.Student;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.test.Game.Game;
import com.example.dell.test.Game.GameAdapter;
import com.example.dell.test.Game.GameReservationAdapter;
import com.example.dell.test.R;

import java.util.ArrayList;
import java.util.List;

public class InqueryGameActivity extends AppCompatActivity {

    private List<Game> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquery_game);

        initGame();  //game数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_student_reservation);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GameReservationAdapter adapter = new GameReservationAdapter(gameList);
        recyclerView.setAdapter(adapter);
    }

    private void initGame() {
        for (int i = 0; i < 10; i++) {
            Game game = new Game();
            game.setName("足球比赛");
            game.setStart(10);
            game.setEnd(12);
            game.setSelected(false);
            gameList.add(game);
        }
    }
}


