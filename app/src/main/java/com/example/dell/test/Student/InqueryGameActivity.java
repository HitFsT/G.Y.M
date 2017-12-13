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
import com.example.dell.test.Gym.Gym;
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
            games = getGames();
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

    private JSONArray getreserGames() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("user_id", valueOf(RefreshORM.get(this, "user_id")));
        /* type 1 means equipment */
        map.put("type", "0");
        String url = HttpUtil.BASE_URL + "ReserRequest";

        return new JSONArray(HttpUtil.postRequest(url, map));
    }



    private JSONArray getGames() throws Exception{
        String url = HttpUtil.BASE_URL + "UserCompet";
        JSONArray all = new JSONArray(HttpUtil.getRequest(url));
        JSONArray reserve = getreserGames();
//        DialogUtil.showDialog(this, "已预约的比赛"+reserve.toString());
        for(int i = 0; i < reserve.length() ; i++){
            for(int j = 0; j < all.length(); j++){
                try{
                    if(reserve.getJSONObject(i).getInt("competition_id")== all.getJSONObject(j).getInt("competition_id")){
//                    DialogUtil.showDialog(this, "已预约id"+reserve.getJSONObject(i).getInt("competition_id"));
                        all.remove(j);
                    }
                }catch (Exception e){
                    DialogUtil.showDialog(this, e.getMessage());
                }

            }
        }
//        DialogUtil.showDialog(this, "已预约比赛"+reserve.toString());
//        DialogUtil.showDialog(this, "所有比赛"+all.toString());
        return all;
    }
}


