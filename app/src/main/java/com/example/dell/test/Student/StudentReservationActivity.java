package com.example.dell.test.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.test.Equipment.Equipment;
import com.example.dell.test.Equipment.EquipmentAdapter;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.EquipmentORM;
import com.example.dell.test.Http.GymORM;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

public class StudentReservationActivity extends AppCompatActivity {

    private List<Equipment> EquipmentList = new ArrayList<Equipment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reservation);

        initEquip();  //game数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_student_reservation);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        EquipmentAdapter adapter = new EquipmentAdapter(EquipmentList);
        recyclerView.setAdapter(adapter);
    }

    public void initEquip(){
        JSONArray equips = new JSONArray();

        try {
            equips = getEquips();
            DialogUtil.showDialog(this, equips.toString());
            for (int i = 0; i < equips.length(); i++){
                Equipment equipment = new Equipment();
                equipment.setName(equips.getJSONObject(i).getString("equip_name"));
                equipment.setName(equips.getJSONObject(i).getString("equip_name"));
                equipment.setStart(equips.getJSONObject(i).getString("equip_start"));
                equipment.setEnd(equips.getJSONObject(i).getString("equip_end"));
                equipment.setSelected(true);
                EquipmentList.add(equipment);

            }
        }catch (Exception e) {
            DialogUtil.showDialog(this, e.getMessage());
        }

    }

    public  JSONArray cacheGymlist(){
        JSONArray gyms = new JSONArray();
        if(RefreshORM.get(this,"gym")>=0){
            if(RefreshORM.get(this,"gym") == 1){
                try{
                    gyms = getGymlist();
                    GymORM.insertGyms(this,gyms);
                    RefreshORM.setfalse(this,"gym");
                }catch(Exception e){
                    DialogUtil.showDialog(this, e.getMessage());
                }
            }else{
                DialogUtil.showDialog(this, "使用缓存");
                gyms = GymORM.getGyms(this);
                RefreshORM.setfalse(this,"gym");
            }
        }else{
            DialogUtil.showDialog(this, "缓存出错");
        }
        return gyms;
    }

    private JSONArray getEquips() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("user_id", valueOf(RefreshORM.get(this, "user_id")));
        DialogUtil.showDialog(this, valueOf(RefreshORM.get(this, "user_id")));
        /* type 1 means equipment */
        map.put("type", "1");
        String url = HttpUtil.BASE_URL + "ReserRequest";

        return new JSONArray(HttpUtil.postRequest(url, map));
    }

    private JSONArray getGymlist() throws Exception{
        String url = HttpUtil.BASE_URL + "GymList";
        return new JSONArray(HttpUtil.getRequest(url));
    }
}

