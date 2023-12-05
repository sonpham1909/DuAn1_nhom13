package com.example.duan1_nhom13.DAO;

import static java.sql.DriverManager.getConnection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom13.Model.Phim;
import com.example.duan1_nhom13.Model.hoadon;
import com.example.duan1_nhom13.Model.top;
import com.example.duan1_nhom13.database.dbhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HDDAO {
    dbhelper dbhelper;
    SQLiteDatabase liteDatabase;


    public  HDDAO(Context context){
        dbhelper = new dbhelper(context);
        phimDAO phimDAO = new phimDAO(context);

        liteDatabase = dbhelper.getReadableDatabase();

    }

    public ArrayList<hoadon> getHD(){
        ArrayList<hoadon> list = new ArrayList<>();


        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from hoadon",null);
        if(cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new hoadon(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getFloat(6),cursor.getString(7),cursor.getInt(8)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public int laygiatritheongay(String tungay, String dengay) {
        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(tongTien) FROM hoadon WHERE ngayInHoaDon >='" + tungay + "'AND ngayInHoaDon <='" + dengay + "'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laysoluotdatve1() {
        LocalDate currentDate = LocalDate.now();



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(soLuongVe) FROM hoadon WHERE ngayInHoaDon ='" +formattedDate + "'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laysoluotdatv() {
        LocalDate currentDate = LocalDate.now();



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(soLuongVe) FROM hoadon", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laysoluotdatv2() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(7);
        LocalDate ngayHienTai = LocalDate.now();

        // Lấy ngày 7 ngày trước
        LocalDate ngay7NgayTruoc = ngayHienTai.minusDays(7);




        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        String formattedDate2 = ngay7NgayTruoc.format(formatter);

        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(soLuongVe) FROM hoadon WHERE ngayInHoaDon >= '"+formattedDate2+"' and ngayInHoaDon <='"+formattedDate+"'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laysoluotdatv3() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(30);
        LocalDate ngayHienTai = LocalDate.now();

        // Lấy ngày 7 ngày trước
        LocalDate ngay7NgayTruoc = ngayHienTai.minusDays(30);




        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        String formattedDate2 = ngay7NgayTruoc.format(formatter);

        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(soLuongVe) FROM hoadon WHERE ngayInHoaDon >= '"+formattedDate2+"' and ngayInHoaDon <='"+formattedDate+"'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    @SuppressLint("Range")
    public List<top> getTop() {
        List<top> list = new ArrayList<>();
        String sql = "SELECT tenPhim, SUM(soLuongVe) AS soluong_datve " +
                "FROM phim " +
                "JOIN hoadon ON phim.maPhim = hoadon.maPhim " +
                "GROUP BY tenPhim " +
                "ORDER BY soluong_datve DESC " +
                "LIMIT 5";

        Cursor cursor = liteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            top top = new top();
            top.setTenPhim(cursor.getString(cursor.getColumnIndex("tenPhim")));
            top.setSove(cursor.getInt(cursor.getColumnIndex("soluong_datve")));
            list.add(top);
        }
        cursor.close();
        return list;
    }
    public int laygiatritheongay1() {
        LocalDate currentDate = LocalDate.now();



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(tongTien) FROM hoadon WHERE ngayInHoaDon ='" +formattedDate + "'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laygiatriall() {



        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(tongTien) FROM hoadon", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laygiatritheongay2() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(7);
        LocalDate ngayHienTai = LocalDate.now();

        // Lấy ngày 7 ngày trước
        LocalDate ngay7NgayTruoc = ngayHienTai.minusDays(7);




        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        String formattedDate2 = ngay7NgayTruoc.format(formatter);

        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(tongTien) FROM hoadon WHERE ngayInHoaDon >= '"+formattedDate2+"' and ngayInHoaDon <='"+formattedDate+"'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public int laygiatritheongay3() {
        LocalDate currentDate = LocalDate.now();

        LocalDate ngayHienTai = LocalDate.now();

        // Lấy ngày 7 ngày trước
        LocalDate ngay7NgayTruoc = ngayHienTai.minusDays(30);




        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = ngayHienTai.format(formatter);
        String formattedDate2 = ngay7NgayTruoc.format(formatter);

        Cursor cursor = liteDatabase.rawQuery("SELECT SUM(tongTien) FROM hoadon WHERE ngayInHoaDon between '"+formattedDate2+"' and '"+formattedDate+"'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }


    public double tinhTongDoanhThu(String ngayBatDau, String ngayKetThuc) throws ParseException {
        // Chuyển đổi ngày bắt đầu và ngày kết thúc thành dạng ngày

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();

// Truy vấn SQL để tính tổng doanh thu từ ngày này đến ngày khác
        String query = "SELECT SUM(tongTien) AS tong_doanh_thu FROM hoadon WHERE ngayInHoaDon BETWEEN '" + ngayBatDau + "' AND '" + ngayKetThuc + "'";

// Thực hiện truy vấn
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

// Kiểm tra và lấy kết quả
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") double tongDoanhThu = cursor.getDouble(cursor.getColumnIndex("tong_doanh_thu"));
            return tongDoanhThu;
            // Sử dụng kết quả thống kê ở đây
        }

// Đóng con trỏ và đóng cơ sở dữ liệu khi đã hoàn thành
        cursor.close();
        return 0.0;
    }
    public double tinhTongDoanhThuTu(int soNgayTruoc) {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Trừ đi số ngày để tính ngày bắt đầu
        LocalDate startDate = currentDate.minusDays(soNgayTruoc);

        // Chuyển đổi ngày thành dạng chuỗi
        String ngayBatDau = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String ngayKetThuc = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();

        // Truy vấn SQL để tính tổng doanh thu từ ngày này đến ngày khác
        String query = "SELECT SUM(tongTien) AS tong_doanh_thu FROM hoadon WHERE ngayInHoaDon BETWEEN ? AND ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{ngayBatDau, ngayKetThuc});

        // Kiểm tra và lấy kết quả
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") double tongDoanhThu = cursor.getDouble(cursor.getColumnIndex("tong_doanh_thu"));
            cursor.close();
            return tongDoanhThu;
        }

        cursor.close();
        return 0.0;
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
        values.put("thanhToan",hd.getThanhtoan());

        long row = db.insert("hoadon",null,values);
        return (row>0);



    }
    public boolean suaPhim(hoadon hd){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("thanhToan",hd.getThanhtoan());

        long row = db.update("hoadon",values,"maHD = ?",new String[]{String.valueOf(hd.getMaHD())});
        return (row>0);



    }

    public boolean del(int id){
        SQLiteDatabase db= dbhelper.getWritableDatabase();
        long row = db.delete("hoadon","maHD=?",new String[]{String.valueOf(id)});
        return (row>0);

    }



}
