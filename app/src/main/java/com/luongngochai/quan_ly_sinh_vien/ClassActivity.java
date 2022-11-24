package com.luongngochai.quan_ly_sinh_vien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.luongngochai.quan_ly_sinh_vien.models.Class;
import com.luongngochai.quan_ly_sinh_vien.models.ClassDAO;

public class ClassActivity extends AppCompatActivity {
    Button btnAdd, btnCannel;
    EditText edID, edName;
    ListView lvClass;
    ClassDAO dao;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        //mapping
        edID = (EditText) findViewById(R.id.edIdClass);
        edName = (EditText) findViewById(R.id.edNameClass);
        btnAdd = (Button) findViewById(R.id.btnSaveClass);
        btnCannel = (Button) findViewById(R.id.btnCannelClass);
        lvClass = (ListView) findViewById(R.id.lvClass);
        //
        dao = new ClassDAO(ClassActivity.this);
        adapter = new ArrayAdapter<Class>(this, android.R.layout.simple_list_item_1, dao.getAll());
        lvClass.setAdapter(adapter);
        lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class cls = dao.getAll().get(position);
                edID.setText(cls.getIdClass());
                edName.setText((cls.getNameClass()));
            }
        });
    }

    public void resetForm(View view) {
        edID.setText("");
        edName.setText("");
    }

    public void addClass(View view) {
        Class cls = new Class(edID.getText().toString(), edName.getText().toString());
        int rs = dao.add(cls);
        if (rs > 0) {
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            adapter = new ArrayAdapter<Class>(this, android.R.layout.simple_list_item_1, dao.getAll());
            lvClass.setAdapter(adapter);
            resetForm(view);
        } else {
            Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateClass(View view) {
        Class cls = new Class(edID.getText().toString(), edName.getText().toString());
        int rs = dao.Update(cls);
        if (rs >= 0) {
            Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            adapter = new ArrayAdapter<Class>(this, android.R.layout.simple_list_item_1, dao.getAll());
            lvClass.setAdapter(adapter);
            resetForm(view);
        } else {
            Toast.makeText(getApplicationContext(),"Không tìm thấy mã lớp", Toast.LENGTH_SHORT).show();
        }
    }
    public void DelClass(View view) {
        int rs = dao.Delete(edID.getText().toString());
        if (rs >= 0) {
            Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            adapter = new ArrayAdapter<Class>(this, android.R.layout.simple_list_item_1, dao.getAll());
            lvClass.setAdapter(adapter);
            resetForm(view);
        } else {
            Toast.makeText(getApplicationContext(),"Không tìm thấy mã lớp", Toast.LENGTH_SHORT).show();
        }
    }
}