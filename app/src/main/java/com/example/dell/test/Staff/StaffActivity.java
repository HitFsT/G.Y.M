package com.example.dell.test.Staff;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Gym.GymAdapter;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Main.MainActivity;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class StaffActivity extends AppCompatActivity {

    private List<Gym> gymList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_staff);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_staff_refresh);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("我的体育馆");

        gymList = new ArrayList<>();
        initGym();  //gym数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gym_staff_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(StaffActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        GymAdapter adapter = new GymAdapter(gymList);
        adapter.setIndex(1);
        recyclerView.setAdapter(adapter);


        ImageView imageview = findViewById(R.id.imageView_refresh);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                gymList = new ArrayList<>();
                initGym();  //gym数据
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gym_staff_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(StaffActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                GymAdapter adapter = new GymAdapter(gymList);
                adapter.setIndex(1);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(StaffActivity.this);
            dialog.setTitle("退出登录");
            dialog.setMessage("确定退出登录吗?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which)  {
                    finish();
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which)  {
                }
            });
            dialog.show();
        }
        return false;
    }

    private JSONArray getGymlist() throws Exception{
        String url = HttpUtil.BASE_URL + "GymList";
        return new JSONArray(HttpUtil.getRequest(url));
    }

    public void initGym() {
        try{
            JSONArray gyms = getGymlist();
            for (int i = 0; i < gyms.length(); i++)
            {
                String name = gyms.getJSONObject(i).getString("gym_name");
                String address = gyms.getJSONObject(i).getString("gym_address");
                String picture_url = gyms.getJSONObject(i).getString("gym_picture");
                Bitmap myBitmap = HttpUtil.getpic(picture_url);
                Gym gym = new Gym(name, address, myBitmap);
                gymList.add(gym);
            }
        }catch(Exception e){
            DialogUtil.showDialog(this, e.getMessage());
        }
    }

}
