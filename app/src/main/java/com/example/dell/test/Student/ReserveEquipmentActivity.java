package com.example.dell.test.Student;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.test.Equipment.Equipment;
import com.example.dell.test.Equipment.Reserve;
import com.example.dell.test.Equipment.ReserveEquipmentAdapter;
import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.EquipmentORM;
import com.example.dell.test.Http.GymORM;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

public class ReserveEquipmentActivity extends AppCompatActivity {

    private List<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_equipment);

        initEquip();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_reserve_equipment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ReserveEquipmentAdapter adapter = new ReserveEquipmentAdapter(equipmentList);
        recyclerView.setAdapter(adapter);

    }

    private void initEquip() {
        try{
            JSONArray equips = cacheEquip();
            for (int i = 0; i < equips.length(); i++) {
                if(equips.getJSONObject(i).getInt("equip_gym_id") == Gym.getGym_id()){
                    Equipment equipment = new Equipment();
                    equipment.setName(equips.getJSONObject(i).getString("equip_name"));
                    equipment.setEquip_id(equips.getJSONObject(i).getInt("equip_id"));
                    equipment.setId(RefreshORM.get(this, "user_id"));
//                    DialogUtil.showDialog(this,"用户id"+equipment.getId());
//                    DialogUtil.showDialog(this,"Equip_id"+equipment.getEquip_id());
                    equipment.setStart(equips.getJSONObject(i).getString("equip_start"));
                    equipment.setEnd(equips.getJSONObject(i).getString("equip_end"));
                    equipment.setSelected(false);
                    equipmentList.add(equipment);
                }
            }
        }catch(Exception e){
            DialogUtil.showDialog(this,e.getMessage());
        }

    }

    public JSONArray cacheEquip(){
        JSONArray equips = new JSONArray();
        if(RefreshORM.get(this,"equip")>=0){
            if(RefreshORM.get(this,"equip") == 1){
                try{
                    equips = getEquips();
                    EquipmentORM.insertEquips(this,equips);
                    RefreshORM.setfalse(this, "equip");
                }catch(Exception e){
                    DialogUtil.showDialog(this, e.getMessage());
                }
            }else{
//                DialogUtil.showDialog(this, "使用缓存");
                equips = EquipmentORM.getEquips(this);
            }
        }else{
            DialogUtil.showDialog(this, "缓存出错");
        }
        return equips;
    }
    private JSONArray getEquips() throws Exception{
        String url = HttpUtil.BASE_URL + "UserEquip";
        JSONArray all = new JSONArray(HttpUtil.getRequest(url));
        JSONArray reserve = getreserEquips();
        for(int i = 0; i < reserve.length(); i++){
            for(int j = 0; j < all.length(); j++){
                if(reserve.getJSONObject(i).getInt("equip_id")== all.getJSONObject(j).getInt("equip_id")){
//                    DialogUtil.showDialog(this, "已预约id"+reserve.getJSONObject(i).getInt("equip_id"));
                    all.remove(j);
                }
            }

        }
        return all;
    }

    private JSONArray getreserEquips() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("user_id", valueOf(RefreshORM.get(this, "user_id")));
//        DialogUtil.showDialog(this, valueOf(RefreshORM.get(this, "user_id")));
        /* type 1 means equipment */
        map.put("type", "1");
        String url = HttpUtil.BASE_URL + "ReserRequest";

        return new JSONArray(HttpUtil.postRequest(url, map));
    }



}
