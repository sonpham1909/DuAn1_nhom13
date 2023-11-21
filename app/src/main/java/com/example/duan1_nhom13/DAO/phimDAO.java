package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;

public class phimDAO {
    private  dbhelper dbhelper;

    public  phimDAO(Context context){
        dbhelper = new dbhelper(context);
    }


    public ArrayList<Phim> getPhim(){
        ArrayList<Phim> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from phim",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new Phim(cursor.getInt(0),cursor.getBlob(1),cursor.getString(2),cursor.getFloat(3),cursor.getInt(4),cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public boolean themPhim(Phim phim){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("image",phim.getAnh());
        values.put("tenPhim",phim.getTen());
        values.put("giave",phim.getGia());
        values.put("thoiluong",phim.getThoiluong());
        values.put("maloai",phim.getMaloai());
        long row = db.insert("phim",null,values);
        return (row>0);



    }
    public boolean suaPhim(Phim phim){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("image",phim.getAnh());
        values.put("tenPhim",phim.getTen());
        values.put("giave",phim.getGia());
        values.put("thoiluong",phim.getThoiluong());
        values.put("maloai",phim.getMaloai());
        long row = db.update("phim",values,"maPhim = ?",new String[]{String.valueOf(phim.getMaPhim())});
        return (row>0);



    }

    public boolean del(int id){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("phim","maPhim=?",new String[]{String.valueOf(id)});
        return (row>0);

    }
}
