package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.lichlam;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;

public class LL_DAO {
    private com.example.duan1_nhom13.database.dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  LL_DAO(Context context){
        dbhelper = new dbhelper(context);
        liteDatabase=dbhelper.getWritableDatabase();
    }


    public ArrayList<lichlam> getLichlam(){
        ArrayList<lichlam> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from lichlam",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new lichlam(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean them(lichlam ll){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("maNV",ll.getManv());
        values.put("maca",ll.getMaca());
        values.put("ngayBD",ll.getNBD());
        values.put("ngayKT",ll.getNKT());

        long row = db.insert("lichlam",null,values);
        return (row>0);
    }
    public boolean delete(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("lichlam","malich=?",new String[]{String.valueOf(id)});
        return (row>0);

    }

    public boolean update(lichlam ll){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("maNV",ll.getManv());
        values.put("maca",ll.getMaca());
        values.put("ngayBD",ll.getNBD());
        values.put("ngayKT",ll.getNKT());

        long row = db.update("lichlam",values,"malich=?",new String[]{String.valueOf(ll.getMalich())});
        return (row>0);
    }
    public ArrayList<lichlam> getId(String id) {
        String sql = "SELECT * FROM lichlam WHERE maNV=?";
        ArrayList<lichlam> list = getdata(sql, id);
        return list;
    }

    private ArrayList<lichlam> getdata(String dl, String... Arays) {
        ArrayList<lichlam> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maloaiIndex = cursor.getColumnIndex("malich");
            int tenloaiIndex = cursor.getColumnIndex("maca");
            int maca = cursor.getColumnIndex("maNV");

            int gioBD = cursor.getColumnIndex("ngayBD");
            int gioKT = cursor.getColumnIndex("ngayKT");

            if (maloaiIndex != -1 && tenloaiIndex != -1&&gioBD!=-1&&gioKT!=-1&&maca!=-1) {
                lichlam loaiSach = new lichlam();
                loaiSach.setMaca(Integer.parseInt(cursor.getString(tenloaiIndex)));
                loaiSach.setManv(cursor.getString(maca));
                loaiSach.setMalich(Integer.parseInt(cursor.getString(maloaiIndex)));


                loaiSach.setNBD(cursor.getString(gioBD));
                loaiSach.setNKT(cursor.getString(gioKT));

                list.add(loaiSach);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }


}
