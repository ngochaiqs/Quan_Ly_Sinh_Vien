package com.luongngochai.quan_ly_sinh_vien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAddClass, btnShowClass, btnStudent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnAddClass = findViewById(R.id.btnAddClass);
        btnStudent = findViewById(R.id.btnStudent);

    }
    public void classManager (View view) {
        intent = new Intent(MainActivity.this, ClassActivity.class);
        startActivity(intent);
    }
    public void studentManager (View view) {
        intent = new Intent (MainActivity.this, StudentActivity.class);
        startActivity(intent);
    }
}