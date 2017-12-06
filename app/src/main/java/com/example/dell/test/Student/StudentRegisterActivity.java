package com.example.dell.test.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentRegisterActivity extends AppCompatActivity {
    EditText user_name, password, confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
    }

    public void StudentRegister(View view) {
        user_name = (EditText) findViewById(R.id.editText_student_register_id);
        password = (EditText) findViewById(R.id.editText_student_register_password);
        confirm = (EditText) findViewById(R.id.editText_student_register_password_confirm);

        if(validate()){
            if(successful()){
                DialogUtil.showDialog(this, "注册成功！");
                Intent intent = new Intent(this, StudentLoginActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                DialogUtil.showDialog(this, "注册失败，已有该账户！");
                clear();
            }
        }
        else{
            clear();
        }
    }

    private boolean validate(){
        String username = user_name.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String confirm = password.getText().toString().trim();
        Log.d("check if:", pwd+" "+confirm );
        if(username.equals("")){
            DialogUtil.showDialog(this, "用户账户是必填项！");
            return false;
        }
        if(pwd.equals("")){
            DialogUtil.showDialog(this, "用户密码是必填项！");
            return false;
        }
        if(!pwd.equals(confirm)){
            DialogUtil.showDialog(this, "两次密码不正确！");
            return false;
        }
        return true;
    }

    private boolean successful(){
        int temp = -233;
        String username = user_name.getText().toString();
        String pwd = password.getText().toString();
        JSONObject jsonObj;
        try{
            jsonObj = send(username, pwd);
            Log.d("test user name", username );
            if((temp = jsonObj.getInt("user_id"))>0){
                return true;
            }else{
                DialogUtil.showDialog(this, Integer.toString(temp));
            }
        }catch (Exception e){
            DialogUtil.showDialog(this, e.getMessage());
        }
        return false;
    }

    public void clear(){
        user_name.setText("");
        password.setText("");
        confirm.setText("");
    }

    private JSONObject send(String username, String pwd) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("user", username);
        map.put("pass", pwd);
        String url = HttpUtil.BASE_URL + "Register";

        return new JSONObject(HttpUtil.postRequest(url, map));
    }


}
