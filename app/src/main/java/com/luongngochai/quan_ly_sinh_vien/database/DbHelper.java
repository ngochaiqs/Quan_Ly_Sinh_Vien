package com.luongngochai.quan_ly_sinh_vien.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbStudentManager";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_classroom = "CREATE TABLE ClassRooms (idClass TEXT PRIMARY KEY, nameClass TEXT)";
        String sql_student = "CREATE TABLE Students (idStudent TEXT PRIMARY KEY, nameStudent TEXT, DOB TEXT, idClass TEXT)";
        sqLiteDatabase.execSQL(sql_student);
        sqLiteDatabase.execSQL(sql_classroom);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists ClassRooms");
        db.execSQL("Drop table if exists Students");

        onCreate(db);

    }
}
