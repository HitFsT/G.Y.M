package com.example.dell.test.Student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Gym.GymAdapter;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Main.MainActivity;
import com.example.dell.test.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Gym> gymList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_student);

        initGym();  //gym数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GymAdapter adapter = new GymAdapter(gymList);
        recyclerView.setAdapter(adapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "还没想好放点什么", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        navigationView1.setNavigationItemSelectedListener(this);

//        /* Set the TextView text */
//        TextView gym_name_1 = (TextView) findViewById(R.id.textView_gym_id_3);
//        TextView gym_address_1 = (TextView) findViewById(R.id.textView_gym_address_3);
//        TextView gym_name_2 = (TextView) findViewById(R.id.textView_gym_id_4);
//        TextView gym_address_2 = (TextView) findViewById(R.id.textView_gym_address_4);
//        try {
//            JSONArray gym_list = getGymlist();
//            gym_name_1.setText(gym_list.getJSONObject(0).getString("gym_name"));
//            gym_address_1.setText(gym_list.getJSONObject(0).getString("gym_address"));
//            gym_name_2.setText(gym_list.getJSONObject(1).getString("gym_name"));
//            gym_address_2.setText(gym_list.getJSONObject(1).getString("gym_address"));
//        }catch(Exception e){
//            DialogUtil.showDialog(this, e.getMessage());
//        }
//        CardView cardview = findViewById(R.id.card_view_staff_3);
//        cardview.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(StudentActivity.this, GymStudentActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    private JSONArray getGymlist() throws Exception{
        String url = HttpUtil.BASE_URL + "GymList";
        return new JSONArray(HttpUtil.getRequest(url));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.student, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_student_reservation) {
            Intent intent = new Intent(this, StudentReservationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_student_game) {
            Intent intent = new Intent(this, StudentTimeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_student_plan) {
            Intent intent = new Intent(this, StudentPlanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact_us) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(StudentActivity.this);
            dialog.setTitle("联系我们");
            dialog.setMessage("邮箱:396433458@qq.com\n电话:13946162640");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.
                    OnClickListener() {
                public void onClick(DialogInterface dialog, int which)  {
                }
            });
            dialog.show();

        }else if (id == R.id.nav_student_logout) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(StudentActivity.this);
            dialog.setTitle("退出登录");
            dialog.setMessage("确定退出登录吗?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.
                    OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)  {
                            Intent intent = new Intent(StudentActivity.this, MainActivity.class);
                            startActivity(intent);
                            }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.
                    OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)  {
                        }
            });
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(StudentActivity.this);
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

    private void initGym(){
        for (int i=0;i<=10;i++)
        {
            Gym gym = new Gym("哈工大体育馆", "圆通快递菜鸟驿站对面", R.drawable.ic_mygym);
            gymList.add(gym);
        }
    }
}
