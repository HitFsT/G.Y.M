package com.example.dell.test.Staff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.Http.RefreshORM;
import com.example.dell.test.R;
import com.example.dell.test.Student.StudentActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StaffLoginActivity extends AppCompatActivity {
    EditText etName, etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        TextView textview = findViewById(R.id.textView_staff_register);
        textview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StaffLoginActivity.this, StaffRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void StaffLogin(View view) {
        /* send the username and password and check if they are valid
        if so then go to the StudentActivity, otherwise do something to handle
        it.*/
        etName = (EditText) findViewById(R.id.editText_staff_id);
        etPass = (EditText) findViewById(R.id.editText_staff_password);
        if(validate()){
            if(loginPro()){
                Intent intent = new Intent(this, StaffActivity.class);
                startActivity(intent);
                finish();
            }else{
                DialogUtil.showDialog(this, "用户名或密码错误");
            }
        }else{
            etName.setText("");
            etPass.setText("");
        }
    }

    /* It checks the information in the database, back in the
  servlet, i set the user_id default to be -1, and the user_id
  do not equals the user_name.
   */
    private boolean loginPro(){
        int temp = -233;
        String username = etName.getText().toString();
        String pwd = etPass.getText().toString();
        JSONObject jsonObj;
        try{
            jsonObj = query(username, pwd);
            Log.d("test user name", username );
            if((temp = jsonObj.getInt("user_id"))>0){
                RefreshORM.iniRefresh(this, temp);
                return true;
            }else{
                DialogUtil.showDialog(this, Integer.toString(temp));
            }
        }catch (Exception e){
            DialogUtil.showDialog(this, e.getMessage());
        }
        return false;
    }

    /*Check if the input is valid, this input simply do not allow empty
    input, it should do better(such as checking the format(xxx.email.com))
     */
    private boolean validate(){
        String username = etName.getText().toString().trim();
        String pwd = etPass.getText().toString().trim();
        if(username.equals("")){
            DialogUtil.showDialog(this, "用户账户是必填项！");
            return false;
        }
        if(pwd.equals("")){
            DialogUtil.showDialog(this, "用户密码是必填项！");
            return false;
        }
        return true;
    }

    private JSONObject query(String username, String pwd) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("user", username);
        map.put("pass", pwd);
        map.put("staff", "1");
        String url = HttpUtil.BASE_URL + "LoginServlet";

        return new JSONObject(HttpUtil.postRequest(url, map));
    }

}
