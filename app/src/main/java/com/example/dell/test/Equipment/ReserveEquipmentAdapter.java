package com.example.dell.test.Equipment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/12/9.
 */

public class ReserveEquipmentAdapter extends RecyclerView.Adapter<ReserveEquipmentAdapter.ViewHolder> {

    private List<Equipment> mEquipmentList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView equipmentName;
        TextView equipmentTime;
        TextView equipmentReserve;

        public ViewHolder(View view){
            super(view);
            equipmentName = (TextView) view.findViewById(R.id.reserve_equipment_name);
            equipmentTime = (TextView) view.findViewById(R.id.reserve_equipment_time);
            equipmentReserve = (TextView) view.findViewById(R.id.reserve_equipment_reserve);
        }
    }

    public ReserveEquipmentAdapter(List<Equipment> equipmentList){
        mEquipmentList = equipmentList;
    }

    @Override
    public ReserveEquipmentAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reserve_equipment, parent ,false);
        final ReserveEquipmentAdapter.ViewHolder holder = new ReserveEquipmentAdapter.ViewHolder(view);
        holder.equipmentReserve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Equipment equipment = mEquipmentList.get(position);
                if (!equipment.isSelected()) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
                    update_id(equipment.getEquip_id(), equipment.getId());
                    dialog.setTitle("确认信息");
                    dialog.setMessage("预约成功");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.
                            OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)  {
                        }
                    });
                    dialog.show();
                    equipment.setSelected(true);
                    holder.equipmentReserve.setText("已预约");
                    holder.equipmentReserve.setTextColor(Color.parseColor("#616161"));
                } else{

                }

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ReserveEquipmentAdapter.ViewHolder holder, int position){
        Equipment equipment = mEquipmentList.get(position);
        holder.equipmentName.setText(equipment.getName());
        holder.equipmentTime.setText(equipment.getStart() + ":00-" + equipment.getEnd() + ":00");
        if (equipment.isSelected()) {
            holder.equipmentReserve.setText("已预约");
        }
        else {
            holder.equipmentReserve.setText("预约");
            holder.equipmentReserve.setTextColor(Color.parseColor("#3F51B5"));
        }
    }

    @Override
    public int getItemCount(){
        return mEquipmentList.size();
    }

    public void update_id(int equip_id, int user_id){
        Map<String, String> map = new HashMap<>();
        map.put("equip_id", String.valueOf(equip_id));
        map.put("user_id", String.valueOf(user_id));
        String url = HttpUtil.BASE_URL + "Reserve";
        try{
            HttpUtil.postRequest(url, map);
        }catch (Exception e){}

    }
}
