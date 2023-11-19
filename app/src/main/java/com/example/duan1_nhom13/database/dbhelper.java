package com.example.duan1_nhom13.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {

    public dbhelper(@Nullable Context context) {
        super(context, "QLRP", null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_qly = "Create table user(" +
                "user text primary key," +
                "tenuser text," +
                "role integer," +
                "pass text)";
        db.execSQL(tb_qly);
        db.execSQL("insert into user values('admin','Nguyen Van Phao',1,'admin'),('son123','Phạm Văn Sơn',2,'son123')");
        String tb_loaiphim = "create table loaiphim(" +
                "maloai integer primary key autoincrement," +
                "tenloai text)";
        db.execSQL(tb_loaiphim);
        db.execSQL("insert into loaiphim values(1,'Ngôn tình'),(2,'Trinh thám')");
        db.execSQL("create table phim(" +
                "maPhim integer primary key autoincrement," +
                "image blob," +
                "tenPhim text," +
                "giave real," +
                "thoiluong int," +
                "maloai integer  references loaiphim(maloai))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       if(oldVersion!=newVersion){
           db.execSQL("DROP TABLE IF EXISTS  user");
           db.execSQL("DROP TABLE IF EXISTS  loaiphim");
           db.execSQL("DROP TABLE IF EXISTS  phim");
           onCreate(db);

       }

    }
}
