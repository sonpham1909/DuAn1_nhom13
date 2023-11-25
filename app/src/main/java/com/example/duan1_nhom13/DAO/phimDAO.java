package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.List;

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
    public Phim getId(String id) {
        String sql = "SELECT * FROM phim WHERE maPhim=?";
        List<Phim> list = getdata(sql, id);
        return list.get(0);
    }

    private List<Phim> getdata(String dl, String... Arays) {
        List<Phim> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maPhim = cursor.getColumnIndex("maPhim");

            int tenPhim = cursor.getColumnIndex("tenPhim");
            int giave = cursor.getColumnIndex("giave");
            int thoiluong = cursor.getColumnIndex("thoiluong");



            if (maPhim != -1 && tenPhim != -1&&giave!=1&&thoiluong!=1) {
                Phim phim= new Phim();
                phim.setMaPhim(Integer.parseInt(cursor.getString(maPhim)));
                phim.setTen(cursor.getString(tenPhim));
               phim.setGia(Float.parseFloat(cursor.getString(giave)));


                list.add(phim);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }
}
