package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.Model.suatchieu;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.List;

public class suatchieuDAO {
    private dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  suatchieuDAO(Context context){
        dbhelper = new dbhelper(context);

    }

    public ArrayList<suatchieu> getSC(){
        ArrayList<suatchieu> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from suatchieu",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new suatchieu(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public suatchieu getidSC(String id){
        suatchieu suatchieu = new suatchieu();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from suatchieu where maSC=?", new String[]{id});
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                new suatchieu(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            }while (cursor.moveToNext());
        }
        return suatchieu;
    }


    public boolean themPC(suatchieu sc){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maPC",sc.getMaPC());


        values.put("tenSC",sc.getTenSC());
        values.put("ngayChieu",sc.getNgaychieu());
        values.put("gioChieu",sc.getGiochieu());

        long row = db.insert("suatchieu",null,values);
        return (row>0);



    }
    public boolean udPC(suatchieu sc){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenSC",sc.getTenSC());
        values.put("ngayChieu",sc.getNgaychieu());
        values.put("gioChieu",sc.getGiochieu());
        values.put("maPC",sc.getMaPC());

        long row = db.update("suatchieu",values,"maSC=?",new String[]{String.valueOf(sc.getMaSC())});

        return (row>0);



    }
    public boolean delete(int maSC){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("suatchieu","maSC=?",new String[]{String.valueOf(maSC)});
        return (row>0);

    }
    public List<suatchieu> GETLS() {
        String dl = "SELECT * FROM suatchieu";
        List<suatchieu> list = getdata(dl);
        return list;
    }

    public suatchieu getId(String id) {
        String sql = "SELECT * FROM suatchieu WHERE maSC=?";
        List<suatchieu> list = getdata(sql, id);
        return list.get(0);
    }

    private List<suatchieu> getdata(String dl, String... Arays) {
        List<suatchieu> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maloaiIndex = cursor.getColumnIndex("maSC");
            int tenloaiIndex = cursor.getColumnIndex("tenSC");
            int ngayChieuIndex = cursor.getColumnIndex("ngayChieu");
            int gioChieuIndex = cursor.getColumnIndex("gioChieu");
            int maPCIndex = cursor.getColumnIndex("maPC");


            if (maloaiIndex != -1 && tenloaiIndex != -1&&ngayChieuIndex!=1&&gioChieuIndex!=-1&&maPCIndex!=1) {
                suatchieu suatchieu = new suatchieu();
                suatchieu.setMaSC(Integer.parseInt(cursor.getString(maloaiIndex)));
                suatchieu.setTenSC(cursor.getString(tenloaiIndex));
                suatchieu.setNgaychieu(cursor.getString(ngayChieuIndex));
                suatchieu.setGiochieu(cursor.getString(gioChieuIndex));
                suatchieu.setMaPC(Integer.parseInt(cursor.getString(maPCIndex)));

                list.add(suatchieu);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }
}
