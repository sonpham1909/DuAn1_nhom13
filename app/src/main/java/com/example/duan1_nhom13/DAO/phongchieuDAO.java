package com.example.duan1_nhom13.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.phonchieu;
import com.example.duan1_nhom13.database.dbhelper;

import java.util.ArrayList;
import java.util.List;

public class phongchieuDAO {

    private dbhelper dbhelper;
    SQLiteDatabase liteDatabase;

    public  phongchieuDAO(Context context){
        dbhelper = new dbhelper(context);

    }

    public ArrayList<phonchieu> getPC(){
        ArrayList<phonchieu> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from phongchieu",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new phonchieu(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<phonchieu> getsgPC(int id) {
        ArrayList<phonchieu> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT soGhe FROM phongchieu WHERE maPC = ?", new String[]{String.valueOf(id)});

        int columnIndex = cursor.getColumnIndex("soGhe");
        if (columnIndex == -1) {
            // Tên cột không tồn tại trong kết quả truy vấn
            cursor.close();
            sqLiteDatabase.close();
            return list;
        }

        if (cursor.moveToFirst()) {
            do {
                int soGhe = cursor.getInt(columnIndex);
                phonchieu phongchieu = new phonchieu(soGhe);
                list.add(phongchieu);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return list;
    }
    public phonchieu getidPC(int id){
       phonchieu phonchieu = new phonchieu();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from phongchieu where maPC=?", new String[]{String.valueOf(id)});
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                new phonchieu(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            }while (cursor.moveToNext());
        }
        return phonchieu;
    }


    public boolean themPC(phonchieu pc){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenPC",pc.getTenPC());
        values.put("soGhe",pc.getSoGhe());
        long row = db.insert("phongchieu",null,values);
        return (row>0);



    }
    public boolean udPC(phonchieu pc){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenPC",pc.getTenPC());
        values.put("soGhe",pc.getSoGhe());
        long row = db.update("phongchieu",values,"maPC=?",new String[]{String.valueOf(pc.getMaPC())});

        return (row>0);



    }
    public boolean udPCc(int id,int soghe){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("soGhe",soghe);
        long row = db.update("phongchieu",values,"maPC=?",new String[]{String.valueOf(id)});

        return (row>0);



    }
    public boolean delete(int maloai){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("phongchieu","maPC=?",new String[]{String.valueOf(maloai)});
        return (row>0);

    }
    public List<phonchieu> GETLS() {
        String dl = "SELECT * FROM phongchieu";
        List<phonchieu> list = getdata(dl);
        return list;
    }

    public phonchieu getId(String id) {
        String sql = "SELECT * FROM phongchieu WHERE maPC=?";
        List<phonchieu> list = getdata(sql, id);
        return list.get(0);
    }

    private List<phonchieu> getdata(String dl, String... Arays) {
        List<phonchieu> list = new ArrayList<>();
        SQLiteDatabase db =dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dl, Arays);
        while (cursor.moveToNext()) {
            int maloaiIndex = cursor.getColumnIndex("maPC");
            int tenloaiIndex = cursor.getColumnIndex("tenPC");
            int shoGheIndex = cursor.getColumnIndex("soGhe");


            if (maloaiIndex != -1 && tenloaiIndex != -1&&shoGheIndex!=1) {
               phonchieu phonchieu = new phonchieu();
                phonchieu.setMaPC(Integer.parseInt(cursor.getString(maloaiIndex)));
                phonchieu.setTenPC(cursor.getString(tenloaiIndex));
                phonchieu.setSoGhe(Integer.parseInt(cursor.getString(shoGheIndex)));

                list.add(phonchieu);
            } else {
                // Handle case when column names are not found
            }
        }
        return list;

    }

}
