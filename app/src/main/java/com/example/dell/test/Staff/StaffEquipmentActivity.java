package com.example.dell.test.Staff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.example.dell.test.Equipment.Equipment;
import com.example.dell.test.Equipment.StaffEquipmentAdapter;
import com.example.dell.test.R;

import java.util.ArrayList;
import java.util.List;

public class StaffEquipmentActivity extends AppCompatActivity {

    private List<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_equipment);

        initEquipment();  //equipment数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_staff_equipment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StaffEquipmentAdapter adapter = new StaffEquipmentAdapter(equipmentList);
        recyclerView.setAdapter(adapter);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            finish();
        }
        return false;
    }

    private void initEquipment() {
        for (int i = 0; i < 5; i++) {
            Equipment equipment = new Equipment();
            equipment.setName("篮球场");
            equipment.setStart(10);
            equipment.setEnd(12);
            equipment.setSelected(false);
            equipmentList.add(equipment);
        }
    }
}
