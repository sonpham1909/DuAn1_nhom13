package com.example.duan1_nhom13.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class dbhelper extends SQLiteOpenHelper {

    public dbhelper(@Nullable Context context) {
        super(context, "QLRP", null, 25);
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
                "ngayInHoaDon date," +
                "thanhToan integer)");
        db.execSQL("insert into hoadon values(1,'admin',1,1,1,4,30000,'2023-09-19',1),(7,'admin',1,1,1,4,30000333,'2023-09-11',1)");
        db.execSQL("Create table calam(" +
                "maca integer primary key autoincrement," +
                "tenca text," +
                "giobatdau text," +
                "gioketthuc text)");
        db.execSQL("insert into calam values(1,'Ca sáng','7:00','12:00'),(2,'Ca chiều','14:00','19:00'),(3,'Ca tối','19:00','23:59')");
        db.execSQL("Create table lichlam(" +
                "malich integer primary key autoincrement," +
                "maNV text references user(user)," +
                "maca integer references calam(maca)," +
                "ngayBD text," +
                "ngayKT text)");
        db.execSQL("insert into lichlam values(2,'admin',1,'30/11/2023','30/11/2023'),(3,'son123',2,'31/11-2023','31/11-2023')");

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
           db.execSQL("DROP TABLE IF EXISTS  calam");
           db.execSQL("DROP TABLE IF EXISTS  lichlam");
           onCreate(db);

       }

    }
}
