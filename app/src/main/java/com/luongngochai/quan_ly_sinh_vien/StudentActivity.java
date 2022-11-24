package com.luongngochai.quan_ly_sinh_vien;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.luongngochai.quan_ly_sinh_vien.models.Class;
import com.luongngochai.quan_ly_sinh_vien.models.ClassDAO;
import com.luongngochai.quan_ly_sinh_vien.models.Student;
import com.luongngochai.quan_ly_sinh_vien.models.StudentDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StudentActivity extends AppCompatActivity {
    Spinner spnClass;
    ClassDAO clsDao = null;
    StudentDAO stDao = null;
    EditText edStudentID, edStudentName, editTextDate;
    List<Class> lsClass = new ArrayList<>();
    String strIDClass = null;
    final Calendar myCalendar = Calendar.getInstance();
    ListView lvStudent;
    ArrayAdapter adapter;
    StudentDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        //mapping
        lvStudent = findViewById(R.id.lvStudent);
        edStudentID = (EditText) findViewById(R.id.edtIdStudent);
        edStudentName = (EditText) findViewById(R.id.edtNameStudent);
        editTextDate = (EditText) findViewById(R.id.edtDOB);
        spnClass = (Spinner) findViewById(R.id.spnClass);
        getClassRooms();
        //
        dao = new StudentDAO(StudentActivity.this);
        adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, dao.getAll());
        lvStudent.setAdapter(adapter);
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student st = dao.getAll().get(position);
                edStudentID.setText(st.getIdStudent());
                edStudentName.setText(st.getNameStudent());
                editTextDate.setText(st.getDOB());

                Class cls = (Class) spnClass.getSelectedItem();
                strIDClass = cls.getIdClass();


            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(StudentActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strIDClass = lsClass.get(spnClass.getSelectedItemPosition()).getIdClass();
                Toast.makeText(getApplicationContext(),strIDClass, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editTextDate.setText(dateFormat.format(myCalendar.getTime()));
    }
    public void resetFormStudent(View view) {
        edStudentID.setText("");
        edStudentName.setText("");
        editTextDate.setText("");
    }
    public void addStudent(View view){
        stDao = new StudentDAO(StudentActivity.this);
        Student st = new Student();
        st.setIdStudent(edStudentID.getText().toString());
        st.setNameStudent(edStudentName.getText().toString());
        st.setDOB(editTextDate.getText().toString());
        st.setIdClass(strIDClass);
        try{
            if(stDao.add(st)>0){
                adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, dao.getAll());
                lvStudent.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_SHORT).show();
                resetFormStudent(view);
            } else {
                Toast.makeText(getApplicationContext(),"Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex){
            Log.e("Error", ex.toString());

        }

    }

    public void UpdateStudent(View view) {
        stDao = new StudentDAO(StudentActivity.this);
        Student st = new Student();
        st.setIdStudent(edStudentID.getText().toString());
        st.setNameStudent(edStudentName.getText().toString());
        st.setDOB(editTextDate.getText().toString());
        st.setIdClass(strIDClass);
        int rs = dao.Update(st);
        if (rs >= 0) {
            Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, dao.getAll());
            lvStudent.setAdapter(adapter);
            resetFormStudent(view);
        } else {
            Toast.makeText(getApplicationContext(),"Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void DelStudent(View view) {
        int rs = dao.Delete(edStudentID.getText().toString());
        if (rs >= 0) {
            Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, dao.getAll());
            lvStudent.setAdapter(adapter);
            resetFormStudent(view);
        } else {
            Toast.makeText(getApplicationContext(),"Không tìm thấy mã lớp", Toast.LENGTH_SHORT).show();
        }
    }
    public void getClassRooms(){
        clsDao = new ClassDAO(StudentActivity.this);
        lsClass = clsDao.getAll();
        ArrayAdapter<Class> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clsDao.getAll());
        spnClass.setAdapter(adapter);
    }

}