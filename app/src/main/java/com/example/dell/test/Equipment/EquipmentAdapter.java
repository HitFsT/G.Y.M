package com.example.dell.test.Equipment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.R;

import java.util.List;

/**
 * Created by DELL on 2017/12/9.
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {

    private List<Equipment> mEquipmentList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView equipmentName;
        TextView equipmentAddress;
        TextView equipmentTime;
        ImageView equipmentImage;

        public ViewHolder(View view){
            super(view);
            equipmentName = (TextView) view.findViewById(R.id.equipment_name);
            equipmentAddress = (TextView) view.findViewById(R.id.equipment_address);
            equipmentTime = (TextView) view.findViewById(R.id.equipment_time);
            equipmentImage = (ImageView) view.findViewById(R.id.equipment_image);
        }
    }

    public EquipmentAdapter(List<Equipment> equipmentList){
        mEquipmentList = equipmentList;
    }

    @Override
    public EquipmentAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipment, parent ,false);
        final EquipmentAdapter.ViewHolder holder = new EquipmentAdapter.ViewHolder(view);
        holder.equipmentImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Equipment equipment = mEquipmentList.get(position);
                if (equipment.isSelected()) {
                    equipment.setSelected(false);
                    Log.d("我的输出", String.format("sss%d",position));
                    holder.equipmentImage.setImageResource(R.drawable.ic_circle);
                }
                else {
                    equipment.setSelected(true);
                    holder.equipmentImage.setImageResource(R.drawable.ic_check);
                    Log.d("我的输出", "select");
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(EquipmentAdapter.ViewHolder holder, int position){
        Equipment equipment = mEquipmentList.get(position);
        if (equipment.isSelected()) {
            holder.equipmentName.setText(equipment.getName());
            holder.equipmentAddress.setText(equipment.getAddress());
            holder.equipmentTime.setText(equipment.getStart() + ":00-" + equipment.getEnd() + ":00");
            holder.equipmentImage.setImageResource(R.drawable.ic_check);
        }
    }

    @Override
    public int getItemCount(){
        return mEquipmentList.size();
    }
}
