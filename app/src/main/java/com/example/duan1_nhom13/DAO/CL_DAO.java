package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.calam;
import com.example.duan1_nhom13.Model.loaisach;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.List;

public class CL_DAO {
    private dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  CL_DAO(Context context){
        dbhelper = new dbhelper(context);
        liteDatabase=dbhelper.getWritableDatabase();
    }


    public ArrayList<calam> getCalam(){
        ArrayList<calam> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from calam",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new calam(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean them(calam cl){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenca",cl.getTenCa());
        values.put("giobatdau",cl.getGioBD());
        values.put("gioketthuc",cl.getGioKT());
        long row = db.insert("calam",null,values);
        return (row>0);
    }
    public boolean update(calam cl){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenca",cl.getTenCa());
        values.put("giobatdau",cl.getGioBD());
        values.put("gioketthuc",cl.getGioKT());
        long row = db.update("calam",values,"maca=?",new String[]{String.valueOf(cl.getMaca())});
        return (row>0);
    }
    public boolean delete(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("calam","maca=?",new String[]{String.valueOf(id)});
        return (row>0);
    }
    public calam getId(String id) {
        String sql = "SELECT * FROM calam WHERE maca=?";
        List<calam> list = getdata(sql, id);
        return list.get(0);
    }

    private List<calam> getdata(String dl, String... Arays) {
        List<calam> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maloaiIndex = cursor.getColumnIndex("maca");
            int tenloaiIndex = cursor.getColumnIndex("tenca");
            int gioBD = cursor.getColumnIndex("giobatdau");
            int gioKT = cursor.getColumnIndex("gioketthuc");

            if (maloaiIndex != -1 && tenloaiIndex != -1&&gioBD!=-1&&gioKT!=-1) {
                calam loaiSach = new calam();
                loaiSach.setMaca(Integer.parseInt(cursor.getString(maloaiIndex)));
                loaiSach.setTenCa(cursor.getString(tenloaiIndex));

                loaiSach.setGioBD(cursor.getString(gioBD));
                loaiSach.setGioKT(cursor.getString(gioKT));

                list.add(loaiSach);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }

}
