package com.luongngochai.quan_ly_sinh_vien.models;

import androidx.annotation.NonNull;

public class Class {
    String idClass;
    String nameClass;

    public Class() {
    }

    public Class(String idClass, String nameClass) {
        this.idClass = idClass;
        this.nameClass = nameClass;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    @NonNull
    @Override
    public String toString() {
        return getIdClass()+ " | "+ getNameClass();
    }
}
