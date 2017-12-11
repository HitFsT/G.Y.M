package com.example.dell.test.Game;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.R;
import com.example.dell.test.Staff.StaffGameActivity;

import java.util.List;

/**
 * Created by DELL on 2017/12/11.
 */

public class StaffGameAdapter extends RecyclerView.Adapter<StaffGameAdapter.ViewHolder> {

    private List<Game> mGameList;
    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView gameName;
        TextView gameTime;
        ImageView gameClear;

        public ViewHolder(View view){
            super(view);
            gameName = (TextView) view.findViewById(R.id.staff_game_name);
            gameTime = (TextView) view.findViewById(R.id.staff_game_time);
            gameClear = (ImageView) view.findViewById(R.id.staff_game_clear);

        }
    }

    public StaffGameAdapter(List<Game> gameList){
        mGameList = gameList;
    }

    @Override
    public StaffGameAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_staff_game, parent ,false);
        final StaffGameAdapter.ViewHolder holder = new StaffGameAdapter.ViewHolder(view);
        holder.gameClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Game game = mGameList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
                dialog.setTitle("删除信息");
                dialog.setMessage("是否删除?" + "龙哥这里需要重要的逻辑");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.
                        OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        /*************
                         * *********删除*******
                         * **************/
                        activity.finish();
                        Intent intent = new Intent(parent.getContext(), StaffGameActivity.class);
                        parent.getContext().startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.
                        OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(StaffGameAdapter.ViewHolder holder, int position){
        Game game = mGameList.get(position);
        holder.gameName.setText(game.getName());
        holder.gameTime.setText(game.getStart() + ":00-" + game.getEnd() + ":00");
        holder.gameClear.setImageResource(R.drawable.ic_clear);

    }

    @Override
    public int getItemCount(){
        return mGameList.size();
    }
}