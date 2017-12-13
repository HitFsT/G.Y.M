package com.example.dell.test.Game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by DELL on 2017/12/8.
 */

public class GameReservationAdapter extends RecyclerView.Adapter<GameReservationAdapter.ViewHolder>{
    private List<Game> mGameList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView gameName;
        TextView gameTime;
        ImageView gameImage;

        public ViewHolder(View view){
            super(view);
            gameName = (TextView) view.findViewById(R.id.game_reservation_name);
            gameTime = (TextView) view.findViewById(R.id.game_reservation_time);
            gameImage = (ImageView) view.findViewById(R.id.game_reservation_image);
        }
    }

    public GameReservationAdapter(List<Game> gameList){
        mGameList = gameList;
    }

    @Override
    public GameReservationAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game_reservation, parent ,false);
        final GameReservationAdapter.ViewHolder holder = new GameReservationAdapter.ViewHolder(view);
        holder.gameImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Game game = mGameList.get(position);
                if (game.isSelected()) {
                    delete(parent.getContext(),RefreshORM.get(parent.getContext(), "user_id"), game.getGame_id());
                    game.setSelected(false);
                    RefreshORM.settrue(parent.getContext(), "competition");
                    Log.d("我的输出", String.format("sss%d",position));
                    holder.gameImage.setImageResource(R.drawable.ic_circle);
                }
                else {
                    try{
                        DialogUtil.showDialog(parent.getContext(), update_id(game.getGame_id(), game.getUser_id()));
                    }catch (Exception e){
                        DialogUtil.showDialog(parent.getContext(),e.getMessage());
                    }
                    game.setSelected(true);
                    holder.gameImage.setImageResource(R.drawable.ic_check);
                    Log.d("我的输出", "select");
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GameReservationAdapter.ViewHolder holder, int position){
        Game game = mGameList.get(position);
        holder.gameName.setText(game.getName());
        holder.gameTime.setText(game.getStart() + ":00-" + game.getEnd() + ":00");
        if (game.isSelected()) {
            holder.gameImage.setImageResource(R.drawable.ic_check);
        }
        else {
            holder.gameImage.setImageResource(R.drawable.ic_circle);
        }
    }

    @Override
    public int getItemCount(){
        return mGameList.size();
    }

    public String update_id(int game_id, int user_id) throws Exception{
        Map<String, String> map = new HashMap<>();
        /* 1 means insert */
        map.put("operation", "1");
        map.put("item_id", String.valueOf(game_id));
        map.put("user_id", String.valueOf(user_id));
        map.put("type", "0");
        String url = HttpUtil.BASE_URL + "Reserve";

        return HttpUtil.postRequest(url, map);

    }


    public void delete(Context context, int user_id, int game_id) {
        Map<String, String> map = new HashMap<>();
        /* 0 means delete */
        map.put("operation", "0");
        map.put("item_id", String.valueOf(game_id));
        map.put("user_id", String.valueOf(user_id));
        map.put("type", "0");
        String url = HttpUtil.BASE_URL + "Reserve";
        try {
            DialogUtil.showDialog(context, "game_id = " + String.valueOf(game_id));
            DialogUtil.showDialog(context, "user_id = " + String.valueOf(user_id));
            DialogUtil.showDialog(context, HttpUtil.postRequest(url, map));
        } catch (Exception e) {
            DialogUtil.showDialog(context, e.getMessage());
        }
    }
}
