package com.example.dell.test.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.test.Game.Game;
import com.example.dell.test.Game.GameAdapter;
import com.example.dell.test.Http.CompetitionORM;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
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
        try{
            JSONArray games = getGames();
            for (int i = 0; i < games.length(); i++) {
                Game game = new Game();
                game.setName(games.getJSONObject(i).getString("competition_name"));
                game.setAddress("篮球场");
                game.setStart(games.getJSONObject(i).getString("competition_start"));
                game.setEnd(games.getJSONObject(i).getString("competition_end"));
                gameList.add(game);
            }
        }catch (Exception e){
            DialogUtil.showDialog(this, e.getMessage());
        }

    }


    private JSONArray getGames() throws Exception{
        String url = HttpUtil.BASE_URL + "UserCompet";
        return new JSONArray(HttpUtil.getRequest(url));
    }

}
