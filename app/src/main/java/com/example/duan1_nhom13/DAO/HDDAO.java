package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.hoadon;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.Collection;

public class HDDAO {
    dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  HDDAO(Context context){
        dbhelper = new dbhelper(context);

    }

    public ArrayList<hoadon> getHD(){
        ArrayList<hoadon> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from hoadon",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new hoadon(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getFloat(6),cursor.getString(7)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themHD(hoadon hd){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

  values.put("maNV",hd.getMaNV());
  values.put("maKH",hd.getMaKH());
  values.put("maPhim",hd.getMaPhim());
  values.put("maSC",hd.getMaSC());
  values.put("soLuongVe",hd.getSlVe());
  values.put("tongTien",hd.getTongtien());
  values.put("ngayInHoaDon",hd.getNgayInHoaDon());

        long row = db.insert("hoadon",null,values);
        return (row>0);



    }
    public boolean suaPhim(hoadon hd){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("maNV",hd.getMaNV());
        values.put("maKH",hd.getMaKH());
        values.put("maPhim",hd.getMaPhim());
        values.put("maSC",hd.getMaSC());
        values.put("soLuongVe",hd.getSlVe());
        values.put("tongTien",hd.getTongtien());
        values.put("ngayInHoaDon",hd.getNgayInHoaDon());

        long row = db.update("hoadon",values,"maHD = ?",new String[]{String.valueOf(hd.getMaHD())});
        return (row>0);



    }

    public boolean del(int id){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("hoadon","maHD=?",new String[]{String.valueOf(id)});
        return (row>0);

    }



}
