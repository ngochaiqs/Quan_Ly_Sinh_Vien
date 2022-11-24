package com.luongngochai.quan_ly_sinh_vien.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.luongngochai.quan_ly_sinh_vien.database.DbHelper;
import com.luongngochai.quan_ly_sinh_vien.models.Student;

public class StudentDAO {
    private SQLiteDatabase db;
    private DbHelper helper;
    private static final String TABLE_NAME = "Students";

    public StudentDAO(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }
    public int add(Student st){
        ContentValues values = new ContentValues();
        values.put("idStudent", st.getIdStudent());
        values.put("nameStudent", st.getNameStudent());
        values.put("DOB", st.getDOB());
        values.put("idClass", st.getIdClass());
        try{
            if(db.insert(TABLE_NAME, null, values)== -1){
                return -1;
            }
        } catch (Exception ex){
            Log.e("StudentDAO Error", ex.toString());
        }
        return 1;
    }
    public List<Student> getAll() {
        List<Student> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Student st = new Student();
            st.setIdStudent(c.getString(0));
            st.setNameStudent(c.getString(1));
            st.setDOB(c.getString(2));
            st.setIdClass(c.getString(3));
            ls.add(st);
            Log.d("//=====", st.toString());
            c.moveToNext();
        }
        c.close();
        return ls;
    }
    public int Update(Student st) {
        ContentValues values = new ContentValues();
        values.put("idStudent", st.getIdStudent());
        values.put("nameStudent", st.getNameStudent());
        values.put("DOB", st.getDOB());
        values.put("idClass", st.getIdClass());
        int result = db.update(TABLE_NAME, values, "idStudent=?", new String[]{st.getIdStudent()});
        try {
            if (result==0){
                return -1;
            }
        } catch (Exception ex) {
            Log.e("StudentDAO Error", ex.toString());
        }
        return 1;
    }
    public int Delete(String id) {
        int result = db.delete(TABLE_NAME, "idStudent=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }
}
