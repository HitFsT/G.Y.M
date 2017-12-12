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
import com.example.dell.test.Http.CompetitionORM;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.EquipmentORM;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

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
        JSONArray games = new JSONArray();
        try {
            games = cacheGames();
            for (int i = 0; i < games.length(); i++) {
                Game game = new Game();
                game.setName(games.getJSONObject(i).getString("competition_name"));
                game.setGame_id(games.getJSONObject(i).getInt("competition_id"));
                game.setUser_id(RefreshORM.get(this, "user_id"));
                game.setStart(games.getJSONObject(i).getString("competition_start"));
                game.setEnd(games.getJSONObject(i).getString("competition_end"));
                game.setSelected(false);
                gameList.add(game);
            }
        } catch (Exception e) {
            DialogUtil.showDialog(this, e.getMessage());
        }

    }

    public JSONArray cacheGames() {
        JSONArray games = new JSONArray();
        if (RefreshORM.get(this, "competition") >= 0) {
            if (RefreshORM.get(this, "competition") == 1) {
                try {
                    games = getGames();
                    CompetitionORM.insertGames(this, games);
                    RefreshORM.setfalse(this, "competition");
                } catch (Exception e) {
                    DialogUtil.showDialog(this, e.getMessage());
                }
            } else {
                DialogUtil.showDialog(this, "使用缓存");
                games = CompetitionORM.getGames(this);
            }
        } else {
            DialogUtil.showDialog(this, "缓存出错");
        }
        return games;
    }

    private JSONArray getGames() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", valueOf(RefreshORM.get(this, "user_id")));
        /* type 1 means equipment */
        map.put("type", "0");
        String url = HttpUtil.BASE_URL + "ReserRequest";

        return new JSONArray(HttpUtil.postRequest(url, map));

    }
}


