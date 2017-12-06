package com.example.dell.test.Gym;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dell.test.R;
import com.example.dell.test.Student.GymStudentActivity;


import java.util.List;



/**
 * Created by DELL on 2017/12/6.
 */

public class GymAdapter extends RecyclerView.Adapter<GymAdapter.ViewHolder>{

    private List<Gym> mGymList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View gymView;
        ImageView gymImage;
        TextView gymName;
        TextView gymAddress;

        public ViewHolder(View view){
            super(view);
            gymView = (View) view.findViewById(R.id.gymView);
            gymImage = (ImageView) view.findViewById(R.id.gymImage);
            gymName = (TextView) view.findViewById(R.id.gymName);
            gymAddress = (TextView) view.findViewById(R.id.gymAddress);
        }
    }

    public GymAdapter(List<Gym> gymList){
        mGymList = gymList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_gym, parent ,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.gymView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Gym gym = mGymList.get(position);
                Intent intent = new Intent(parent.getContext(), GymStudentActivity.class);
                intent.putExtra("position", position);
                parent.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Gym gym = mGymList.get(position);
        holder.gymImage.setImageBitmap(gym.getImage());
        holder.gymName.setText(gym.getName());
        holder.gymAddress.setText(gym.getAddress());
    }

    @Override
    public int getItemCount(){
        return mGymList.size();
    }
}
