package com.example.dell.test.Staff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.test.Equipment.Equipment;
import com.example.dell.test.Equipment.StaffEquipmentAdapter;
import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.EquipmentORM;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StaffEquipmentActivity extends AppCompatActivity {

    private List<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_equipment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_staff);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("我的设施");

        initEquip();  //equipment数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_staff_equipment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StaffEquipmentAdapter adapter = new StaffEquipmentAdapter(equipmentList);
        adapter.setActivity(StaffEquipmentActivity.this);
        recyclerView.setAdapter(adapter);

        ImageView imageview = findViewById(R.id.imageView_add);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StaffEquipmentActivity.this, AddEquipmentActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            finish();
        }
        return false;
    }

    private void initEquip() {
        try{
            JSONArray equips = getEquips();
            for (int i = 0; i < equips.length(); i++) {
                if(Gym.getGym_id() == equips.getJSONObject(i).getInt("equip_gym_id")){
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
        return new JSONArray(HttpUtil.getRequest(url));
    }
}
