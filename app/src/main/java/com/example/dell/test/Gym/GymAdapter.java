package com.example.dell.test.Gym;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.R;

import java.util.List;

/**
 * Created by DELL on 2017/12/6.
 */

public class GymAdapter extends RecyclerView.Adapter<GymAdapter.ViewHolder>{

    private List<Gym> mGymList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView gymImage;
        TextView gymName;
        TextView gymAddress;

        public ViewHolder(View view){
            super(view);
            gymImage = (ImageView) view.findViewById(R.id.gymImage);
            gymName = (TextView) view.findViewById(R.id.gymName);
            gymAddress = (TextView) view.findViewById(R.id.gymAddress);
        }
    }

    public GymAdapter(List<Gym> gymList){
        mGymList = gymList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_gym, parent ,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Gym gym = mGymList.get(position);
        holder.gymImage.setImageResource(gym.getImageID());
        holder.gymName.setText(gym.getName());
        holder.gymAddress.setText(gym.getAddress());
    }

    @Override
    public int getItemCount(){
        return mGymList.size();
    }
}
