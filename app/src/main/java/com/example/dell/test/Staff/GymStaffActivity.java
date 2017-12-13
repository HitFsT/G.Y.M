package com.example.dell.test.Staff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.test.Gym.Gym;
import com.example.dell.test.Http.Cache;
import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.GymORM;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;

import org.json.JSONArray;

public class GymStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_staff);

        Intent get_intent = getIntent();
        int position = get_intent.getIntExtra("position", 0);

        ImageView gym_image = (ImageView) findViewById(R.id.imageView_gym_staff_image);
        TextView gym_name = (TextView) findViewById(R.id.textView_gym_staff_name);
        TextView gym_address = (TextView) findViewById(R.id.textView_gym_staff_address);
        TextView gym_contact = (TextView) findViewById(R.id.textView_gym_staff_contact);
        try {
            JSONArray gym_list =getGymlist();
            Gym.setGym_id(gym_list.getJSONObject(position).getInt("gym_id"));
            gym_image.setImageBitmap(HttpUtil.getpic(gym_list.getJSONObject(position).getString("gym_picture")));
            gym_name.setText(gym_list.getJSONObject(position).getString("gym_name"));
            gym_address.setText("地址：" + gym_list.getJSONObject(position).getString("gym_address"));
            gym_contact.setText("电话：" + gym_list.getJSONObject(position).getString("gym_phone" )+
                    " 联系人："+ gym_list.getJSONObject(position).getString("gym_contact" ));
        }catch(Exception e){
            DialogUtil.showDialog(this, e.getMessage());
        }

        /******************这里用position set GYMSTAFF界面  参考GYMSTUDENT********************/
    }

    private JSONArray getGymlist() throws Exception{
        String url = HttpUtil.BASE_URL + "GymList";
        return new JSONArray(HttpUtil.getRequest(url));
    }

    public void Edit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void StaffEquipment(View view) {
        Intent intent = new Intent(this, StaffEquipmentActivity.class);
        startActivity(intent);
    }

    public void StaffGame(View view) {
        Intent intent = new Intent(this, StaffGameActivity.class);
        startActivity(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            finish();
        }
        return false;
    }

}
