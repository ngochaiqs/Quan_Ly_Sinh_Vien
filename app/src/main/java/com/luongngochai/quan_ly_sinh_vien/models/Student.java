package com.luongngochai.quan_ly_sinh_vien.models;


import androidx.annotation.NonNull;

import java.util.Date;

public class Student {
    String idStudent;
    String idClass;
    String nameStudent;
    String DOB;

    public Student() {
    }
    public Student(String idStudent, String idClass, String nameStudent, String DOB) {
        this.idStudent = idStudent;
        this.idClass = idClass;
        this.nameStudent = nameStudent;
        this.DOB = DOB;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @NonNull
    @Override
    public String toString() {
        return getIdClass()+ " | "+ getIdStudent()+ " | "+ getNameStudent()+ " | "+ getDOB();
    }
}
