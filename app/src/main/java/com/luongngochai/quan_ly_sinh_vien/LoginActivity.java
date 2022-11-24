package com.luongngochai.quan_ly_sinh_vien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edUserName, edPassWord;
    Button btnCannel, btnLogin;
    CheckBox chkRemerberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = (EditText) findViewById(R.id.edUserName);
        edPassWord = (EditText) findViewById(R.id.edPassWord);
        btnCannel = (Button) findViewById(R.id.btnCannel);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkRemerberPass = (CheckBox) findViewById(R.id.chkRememberPass);
    }
    public void checkLogin(View view){
        String u = edUserName.getText().toString();
        String p = edPassWord.getText().toString();
        if (u.equals("fpt") && p.equals("123")){
            Toast.makeText(getApplicationContext(),"Login thành công", Toast.LENGTH_SHORT).show();
            remerberUser(u, p, chkRemerberPass.isChecked());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Login thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void resetForm(View view){
        edUserName.setText("");
        edPassWord.setText("");
    }
    public void remerberUser(String username, String pass, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            editor.clear();
        } else {
            //them date vao file
            editor.putString("USERNAME",username);
            editor.putString("PASSWORD",pass);
            editor.putBoolean("REMEMBER", status);
        }
        //luu file
        editor.commit();

    }
}