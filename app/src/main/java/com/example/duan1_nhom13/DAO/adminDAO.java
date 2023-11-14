package com.example.duan1_nhom13.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;

public class adminDAO {
    private dbhelper dbhelper;

    public  adminDAO(Context context){
        dbhelper = new dbhelper(context);
    }

    // kiểm tra thông tin đăng nhập
    public boolean Kiemtradangnhap(String user, String pass){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from user where user = ? and pass =?",new String[]{user,pass});
       return cursor.getCount()>0;


    }
    public user getUser(String dk){
        user us = new user();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select tenuser,role from user where user=?",new String[]{dk});
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                us.setTen(cursor.getString(0));
                us.setRole(cursor.getInt(1));
            }while (cursor.moveToNext());
        }
        return us;
    }







}
