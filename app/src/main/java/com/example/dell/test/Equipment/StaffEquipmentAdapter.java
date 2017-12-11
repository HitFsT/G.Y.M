package com.example.dell.test.Equipment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.R;
import com.example.dell.test.Staff.StaffEquipmentActivity;

import java.util.List;

/**
 * Created by DELL on 2017/12/11.
 */

public class StaffEquipmentAdapter extends RecyclerView.Adapter<StaffEquipmentAdapter.ViewHolder> {

    private List<Equipment> mEquipmentList;



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView equipmentName;
        TextView equipmentTime;
        ImageView equipmentClear;

        public ViewHolder(View view){
            super(view);
            equipmentName = (TextView) view.findViewById(R.id.staff_equipment_name);
            equipmentTime = (TextView) view.findViewById(R.id.staff_equipment_time);
            equipmentClear = (ImageView) view.findViewById(R.id.staff_equipment_clear);

        }
    }

    public StaffEquipmentAdapter(List<Equipment> equipmentList){
        mEquipmentList = equipmentList;
    }

    @Override
    public StaffEquipmentAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_staff_equipment, parent ,false);
        final StaffEquipmentAdapter.ViewHolder holder = new StaffEquipmentAdapter.ViewHolder(view);
        holder.equipmentClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Equipment equipment = mEquipmentList.get(position);
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
                        Intent intent = new Intent(parent.getContext(), StaffEquipmentActivity.class);
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
    public void onBindViewHolder(StaffEquipmentAdapter.ViewHolder holder, int position){
        Equipment equipment = mEquipmentList.get(position);
        holder.equipmentName.setText(equipment.getName());
        holder.equipmentTime.setText(equipment.getStart() + ":00-" + equipment.getEnd() + ":00");
        holder.equipmentClear.setImageResource(R.drawable.ic_clear);

    }

    @Override
    public int getItemCount(){
        return mEquipmentList.size();
    }
}