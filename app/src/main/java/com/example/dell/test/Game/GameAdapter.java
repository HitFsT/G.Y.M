package com.example.dell.test.Game;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Gym.GymAdapter;
import com.example.dell.test.R;
import com.example.dell.test.Student.GymStudentActivity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by DELL on 2017/12/7.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    private List<Game> mGameList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView gameName;
        TextView gameAddress;
        TextView gameTime;

        public ViewHolder(View view){
            super(view);
            gameName = (TextView) view.findViewById(R.id.gameName);
            gameAddress = (TextView) view.findViewById(R.id.gameAddress);
            gameTime = (TextView) view.findViewById(R.id.gameTime);
        }
    }

    public GameAdapter(List<Game> gameList){
        mGameList = gameList;
    }

    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent ,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Game game = mGameList.get(position);
        holder.gameName.setText(game.getName());
        holder.gameAddress.setText(game.getAddress());
        holder.gameTime.setText("比赛时间 " + game.getStart() + ":00-" + game.getEnd() + ":00");
    }

    @Override
    public int getItemCount(){
        return mGameList.size();
    }
//    public static void main(String args[])
//    {
//        Timestamp time = new Timestamp(System.currentTimeMillis());
//        String str = time.toString();
//        System.out.println(str);
//    }
}


