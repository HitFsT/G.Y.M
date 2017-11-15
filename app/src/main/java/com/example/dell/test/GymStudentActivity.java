package com.example.dell.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;

public class GymStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_student);
        TextView gym_name = (TextView) findViewById(R.id.textView_gym_id);
        TextView gym_address = (TextView) findViewById(R.id.textView_gym_address);
        TextView gym_phone = (TextView) findViewById(R.id.textView_gym_phone);
        try {
            JSONArray gym_list = getGymlist();
            gym_name.setText(gym_list.getJSONObject(0).getString("gym_name"));
            gym_address.setText("地址：" + gym_list.getJSONObject(0).getString("gym_address"));
            gym_phone.setText("电话：" + gym_list.getJSONObject(0).getString("gym_phone" )+
                    " 联系人："+ gym_list.getJSONObject(0).getString("gym_contact" ));
        }catch(Exception e){
            DialogUtil.showDialog(this, e.getMessage());
        }
    }

    private JSONArray getGymlist() throws Exception{
        String url = HttpUtil.BASE_URL + "GymList";
        return new JSONArray(HttpUtil.getRequest(url));
    }

    public void ReserveEquipment(View view) {
        Intent intent = new Intent(this, ReserveEquipmentActivity.class);
        startActivity(intent);
    }

    public void InqueryGame(View view) {
        Intent intent = new Intent(this, InqueryGameActivity.class);
        startActivity(intent);
    }
}
