package com.example.duan1_nhom13.Model;

public class Phim {
    private int maPhim;
    private byte[] anh;
    private String ten;
    private Float gia;
    private int thoiluong;
    private int maloai;


    public Phim() {
    }

    public Phim(int maPhim, byte[] anh, String ten, Float gia, int thoiluong, int maloai) {
        this.maPhim = maPhim;
        this.anh = anh;
        this.ten = ten;
        this.gia = gia;
        this.thoiluong = thoiluong;
        this.maloai = maloai;
    }

    public Phim(byte[] anh, String ten, Float gia, int thoiluong, int maloai) {
        this.anh = anh;
        this.ten = ten;
        this.gia = gia;
        this.thoiluong = thoiluong;
        this.maloai = maloai;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public int getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(int thoiluong) {
        this.thoiluong = thoiluong;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
