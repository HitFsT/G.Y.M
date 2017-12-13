package com.example.dell.test.Equipment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;
import com.example.dell.test.Staff.StaffEquipmentActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * Created by DELL on 2017/12/11.
 */

public class StaffEquipmentAdapter extends RecyclerView.Adapter<StaffEquipmentAdapter.ViewHolder> {

    private List<Equipment> mEquipmentList;
    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

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
                final Equipment equipment = mEquipmentList.get(position);
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
                        int gym_id = Gym.getGym_id();
                        DialogUtil.showDialog(parent.getContext(), valueOf(gym_id));
                        int equip_id = equipment.getEquip_id();
                        DialogUtil.showDialog(parent.getContext(), valueOf(equip_id));
                        try{
                            delete(gym_id, equip_id);
                            RefreshORM.settrue(parent.getContext(), "equip");
                        }catch (Exception e){
                            DialogUtil.showDialog(parent.getContext(), e.getMessage());
                        }

                        activity.finish();
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

    private String delete(int gym_id, int equip_id) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("gym_id", valueOf(gym_id));
        map.put("equip_id", valueOf(equip_id));
        /* 0 means delete */
        map.put("operation", valueOf(0));
        String url = HttpUtil.BASE_URL + "EditEquip";
        return HttpUtil.postRequest(url, map);
    }
}