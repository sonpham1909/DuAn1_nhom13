package com.example.duan1_nhom13.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<user>  getALl(){
        ArrayList<user> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from user",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new user(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean them(user us){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user",us.getUser());
        values.put("tenuser",us.getTen());
        values.put("pass",us.getPass());
        values.put("role",us.getRole());
        values.put("sodienthoai",us.getSodienthoai());
        long row = db.insert("user",null,values);
        return (row>0);


    }
    public boolean sua(user us){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user",us.getUser());
        values.put("tenuser",us.getTen());
        values.put("pass",us.getPass());
        values.put("role",us.getRole());
        values.put("sodienthoai",us.getSodienthoai());
        long row = db.update("user",values,"user=?",new String[]{us.getUser()});
        return (row>0);


    }
    public boolean xoa(String user){
        SQLiteDatabase db= dbhelper.getWritableDatabase();

        long row = db.delete("user","user=?",new String[]{user});
        return (row>0);


    }
    @SuppressLint("Range")
    public List<user> getDAta(String sql, String...SelectAGr){
        List<user> list = new ArrayList<user>();
        SQLiteDatabase db= dbhelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, SelectAGr);
        while(c.moveToNext()){
            user tt = new user();
            tt.setUser(c.getString(c.getColumnIndex("user")));
            tt.setTen(c.getString(c.getColumnIndex("tenuser")));
            tt.setPass(c.getString(c.getColumnIndex("pass")));
            tt.setSodienthoai(c.getString(c.getColumnIndex("sodienthoai")));
            tt.setRole(c.getInt(c.getColumnIndex("role")));
            list.add(tt);

        }

        return list;

    }

    public List<user> getAll(){

        String sql = "Select*from user";
        return getDAta(sql);
    }

    public user getID(String id){
        String sql = "Select*from user where user=?";
        List<user> list = getDAta(sql,id);
        return list.get(0);
    }







}
