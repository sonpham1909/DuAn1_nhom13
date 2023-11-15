package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.user;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;

public class loaisachDAO {
    private dbhelper dbhelper;

    public  loaisachDAO(Context context){
        dbhelper = new dbhelper(context);
    }


    public ArrayList<loaisach>  getLoaiPhim(){
        ArrayList<loaisach> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from loaiphim",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new loaisach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public boolean themloaisach(loaisach ls){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenloai",ls.getTenloai());
        long row = db.insert("loaiphim",null,values);
        return (row>0);



    }
    public boolean udloaisach(loaisach ls){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenloai",ls.getTenloai());
        long row = db.update("loaiphim",values,"maloai=?",new String[]{String.valueOf(ls.getMaloai())});

        return (row>0);



    }
    public boolean deleteloaiphim(int maloai){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("loaiphim","maloai=?",new String[]{String.valueOf(maloai)});
        return (row>0);

    }

}
