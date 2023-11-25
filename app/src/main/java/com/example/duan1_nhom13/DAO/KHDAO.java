package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.khachhang;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.List;

public class KHDAO {
    private com.example.duan1_nhom13.database.dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  KHDAO(Context context){
        dbhelper = new dbhelper(context);

    }

    public ArrayList<khachhang> getKH(){
        ArrayList<khachhang> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from khachhang",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new khachhang(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }



    public boolean themKH(khachhang kh){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenKH",kh.getTenKH());
        values.put("soKH",kh.getSoKH());




        long row = db.insert("khachhang",null,values);
        return (row>0);



    }
    public boolean udKH(khachhang kh){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenKH",kh.getTenKH());
        values.put("soKH",kh.getSoKH());
        long row = db.update("khachhang",values,"maKH=?",new String[]{String.valueOf(kh.getMaKH())});

        return (row>0);



    }
    public boolean delete(int maKH){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("khachhang","maKH=?",new String[]{String.valueOf(maKH)});
        return (row>0);

    }
    public List<khachhang> GETLS() {
        String dl = "SELECT * FROM khachhang";
        List<khachhang> list = getdata(dl);
        return list;
    }

    public khachhang getId(String id) {
        String sql = "SELECT * FROM khachhang WHERE maKH=?";
        List<khachhang> list = getdata(sql, id);
        return list.get(0);
    }

    private List<khachhang> getdata(String dl, String... Arays) {
        List<khachhang> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maloaiIndex = cursor.getColumnIndex("maKH");
            int tenloaiIndex = cursor.getColumnIndex("tenKH");
            int ngayChieuIndex = cursor.getColumnIndex("soKH");


            if (maloaiIndex != -1 && tenloaiIndex != -1&&ngayChieuIndex!=1) {
                khachhang kh = new khachhang();
                kh.setMaKH(Integer.parseInt(cursor.getString(maloaiIndex)));
                kh.setTenKH(cursor.getString(tenloaiIndex));
               kh.setSoKH(cursor.getString(ngayChieuIndex));

                list.add(kh);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }
}
