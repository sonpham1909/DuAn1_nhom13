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

public class loaisachDAO {
    private dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  loaisachDAO(Context context){
        dbhelper = new dbhelper(context);
        liteDatabase=dbhelper.getWritableDatabase();
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
    public loaisach  getidLoaiPhim(String id){
        loaisach loaisach = new loaisach();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from loaiphim where maloai=?", new String[]{id});
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
              new loaisach(cursor.getInt(0),cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return loaisach;
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
    public List<loaisach> GETLS() {
        String dl = "SELECT * FROM loaiphim";
        List<loaisach> list = getdata(dl);
        return list;
    }

    public loaisach getId(String id) {
        String sql = "SELECT * FROM loaiphim WHERE maloai=?";
        List<loaisach> list = getdata(sql, id);
        return list.get(0);
    }

    private List<loaisach> getdata(String dl, String... Arays) {
        List<loaisach> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maloaiIndex = cursor.getColumnIndex("maloai");
            int tenloaiIndex = cursor.getColumnIndex("tenloai");

            if (maloaiIndex != -1 && tenloaiIndex != -1) {
                loaisach loaiSach = new loaisach();
                loaiSach.setMaloai(Integer.parseInt(cursor.getString(maloaiIndex)));
                loaiSach.setTenloai(cursor.getString(tenloaiIndex));

                list.add(loaiSach);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }

}
