package com.luongngochai.quan_ly_sinh_vien.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.luongngochai.quan_ly_sinh_vien.database.DbHelper;
import com.luongngochai.quan_ly_sinh_vien.models.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private final SQLiteDatabase db;
    private final String TABLE_NAME = "ClassRooms";
    public static List<Class> lsClass = new ArrayList<>();

    public ClassDAO(Context context) {
//        dummyData();
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int add(Class cls) {
//        lsClass.add(cls);
        ContentValues values = new ContentValues();
        values.put("idClass", cls.getIdClass());
        values.put("nameClass", cls.getNameClass());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1){
                return -1;
            }
        } catch (Exception ex) {
            Log.e("ClassManagerDAO ERROR: ", "//==" +ex.toString());
        }
        return 1;
    }

    public List<Class> getAll() {
        List<Class> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Class cls = new Class();
            cls.setIdClass(c.getString(0));
            cls.setNameClass(c.getString(1));
            ls.add(cls);
            c.moveToNext();
        }
        return ls;
    }

    public int Update(Class cls) {
        ContentValues values = new ContentValues();
        values.put("idClass", cls.getIdClass());
        values.put("nameClass", cls.getNameClass());
        try {
            if (db.update(TABLE_NAME,values, "idClass=?", new String[]{cls.getIdClass()}) == -1){
                return -1;
            }
        } catch (Exception ex) {
            Log.e("ClassManagerDAO ERROR: ", "//==" +ex.toString());
        }
        return 1;
    }
    public int Delete(String id) {
        int result = db.delete(TABLE_NAME, "idClass=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }
    public int findById(String id) {
        for (int i = 0; i < getAll().size(); i++) {
            Class cls = lsClass.get(i);
            if (cls.getIdClass().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void dummyData(){
        lsClass.add(new Class("IT16301", "Lập Trình Mobile"));
        lsClass.add(new Class("IT16302", "Ứng Dụng Phần Mềm"));
        lsClass.add(new Class("PT16301", "Thiết kế Web"));

    }
}
