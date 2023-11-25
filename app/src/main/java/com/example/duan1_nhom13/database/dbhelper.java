package com.example.duan1_nhom13.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {

    public dbhelper(@Nullable Context context) {
        super(context, "QLRP", null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_qly = "Create table user(" +
                "user text primary key," +
                "tenuser text," +
                "role integer," +
                "pass text," +
                "sodienthoai text)";
        db.execSQL(tb_qly);
        db.execSQL("insert into user values('admin','Nguyen Van Phao',1,'admin','0936965250'),('son123','Phạm Văn Sơn',2,'son123','035897802')");
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
        db.execSQL("create table phongchieu(" +
                " maPC integer primary key autoincrement," +
                "tenPC text," +
                "soGhe integer)");
        db.execSQL("insert into phongchieu values(1,'PC01',30),(2,'PC02',42)");
        db.execSQL("create table suatchieu(" +
                "maSC integer primary key autoincrement," +
                "tenSC text," +
                "ngayChieu text,"+
                "gioChieu text," +
                "maPC integer references phongchieu(maPC) )");
        db.execSQL("insert into suatchieu values(1,'SC01','20/12/2023','15:30',1)");
        db.execSQL("create table khachhang(" +
                "maKH integer primary key autoincrement," +
                "tenKH text," +
                "soKH text)");
        db.execSQL("insert into khachhang values(1,'Jonh Marttin','0567893404'),(2,'Laura Alpha','0933457892')");
        db.execSQL("create table  hoadon(" +
                "maHD integer primary key autoincrement," +
                "maNV text references user(user)," +
                "maKH integer references khachhang(maKH)," +
                "maPhim integer references phim(maPhim)," +
                "maSC integer references suatchieu(maSC)," +
                "soLuongVe integer," +
                "tongTien real," +
                "ngayInHoaDon text" +
                ")");
        db.execSQL("insert into hoadon values(1,'admin',1,1,1,4,30000,'19-09-2023',1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       if(oldVersion!=newVersion){
           db.execSQL("DROP TABLE IF EXISTS  user");
           db.execSQL("DROP TABLE IF EXISTS  loaiphim");
           db.execSQL("DROP TABLE IF EXISTS  phim");
           db.execSQL("DROP TABLE IF EXISTS  phongchieu");
           db.execSQL("DROP TABLE IF EXISTS  suatchieu");
           db.execSQL("DROP TABLE IF EXISTS  khachhang");
           db.execSQL("DROP TABLE IF EXISTS  hoadon");
           onCreate(db);

       }

    }
}
