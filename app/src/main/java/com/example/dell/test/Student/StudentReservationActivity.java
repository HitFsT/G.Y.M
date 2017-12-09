package com.example.dell.test.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.test.Equipment.Equipment;
import com.example.dell.test.Equipment.EquipmentAdapter;
import com.example.dell.test.R;

import java.util.ArrayList;
import java.util.List;

public class StudentReservationActivity extends AppCompatActivity {

    private List<Equipment> EquipmentList = new ArrayList<Equipment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reservation);

        initGame();  //game数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_student_reservation);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        EquipmentAdapter adapter = new EquipmentAdapter(EquipmentList);
        recyclerView.setAdapter(adapter);
    }

    public void initGame(){
        for (int i = 0; i < 10; i++){
            Equipment equipment = new Equipment();
            equipment.setName("篮球场");
            equipment.setAddress("哈工大体育馆");
            equipment.setStart(14);
            equipment.setEnd(17);
            equipment.setSelected(true);
            EquipmentList.add(equipment);
        }
    }
}

