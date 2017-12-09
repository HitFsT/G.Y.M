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
import com.example.dell.test.Equipment.ReserveEquipmentAdapter;
import com.example.dell.test.R;

import java.util.ArrayList;
import java.util.List;

public class ReserveEquipmentActivity extends AppCompatActivity {

    private List<Equipment> equipmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_equipment);

        initGame();  //game数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_reserve_equipment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ReserveEquipmentAdapter adapter = new ReserveEquipmentAdapter(equipmentList);
        recyclerView.setAdapter(adapter);

    }

    private void initGame() {
        for (int i = 0; i < 5; i++) {
            Equipment equipment = new Equipment();
            equipment.setName("篮球场");
            equipment.setStart(10);
            equipment.setEnd(12);
            equipment.setSelected(false);
            equipmentList.add(equipment);

            Equipment equipment2 = new Equipment();
            equipment2.setName("足球场");
            equipment2.setStart(15);
            equipment2.setEnd(17);
            equipment2.setSelected(true);
            equipmentList.add(equipment2);
        }
    }
}
