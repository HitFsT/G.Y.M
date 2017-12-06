package com.example.dell.test.Student;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.test.Http.DialogUtil;
import com.example.dell.test.Http.HttpUtil;
import com.example.dell.test.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentLoginActivity extends AppCompatActivity {
    EditText etName, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        setContentView(R.layout.activity_student_login);
        TextView textview = findViewById(R.id.textView_student_register);
        textview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StudentLoginActivity.this, StudentRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void StudentLogin(View view) {
        /* send the username and password and check if they are valid
        if so then go to the StudentActivity, otherwise do something to handle
        it.*/
        etName = (EditText) findViewById(R.id.editText_student_id);
        etPass = (EditText) findViewById(R.id.editText_student_password);
        if(validate()){
            if(loginPro()){
                Intent intent = new Intent(this, StudentActivity.class);
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
        String url = HttpUtil.BASE_URL + "LoginServlet";

        return new JSONObject(HttpUtil.postRequest(url, map));
    }

}
