package com.example.duan1_nhom13.Model;

public class hoadon {
    private int maHD;
    private String maNV;
    private int maKH,maPhim,maSC,slVe;
    private float tongtien;
    private String ngayInHoaDon;
    private int thanhtoan;

    public hoadon() {
    }

    public hoadon(int maHD, String maNV, int maKH, int maPhim, int maSC, int slVe, float tongtien, String ngayInHoaDon) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maPhim = maPhim;
        this.maSC = maSC;
        this.slVe = slVe;
        this.tongtien = tongtien;
        this.ngayInHoaDon = ngayInHoaDon;
    }

    public hoadon(String maNV, int maKH, int maPhim, int maSC, int slVe, float tongtien, String ngayInHoaDon) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.maPhim = maPhim;
        this.maSC = maSC;
        this.slVe = slVe;
        this.tongtien = tongtien;
        this.ngayInHoaDon = ngayInHoaDon;
    }

    public hoadon(int maHD, String maNV, int maKH, int maPhim, int maSC, int slVe, float tongtien, String ngayInHoaDon, int thanhtoan) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maPhim = maPhim;
        this.maSC = maSC;
        this.slVe = slVe;
        this.tongtien = tongtien;
        this.ngayInHoaDon = ngayInHoaDon;
        this.thanhtoan = thanhtoan;
    }

    public hoadon(String maNV, int maKH, int maPhim, int maSC, int slVe, float tongtien, String ngayInHoaDon, int thanhtoan) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.maPhim = maPhim;
        this.maSC = maSC;
        this.slVe = slVe;
        this.tongtien = tongtien;
        this.ngayInHoaDon = ngayInHoaDon;
        this.thanhtoan = thanhtoan;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public int getMaSC() {
        return maSC;
    }

    public void setMaSC(int maSC) {
        this.maSC = maSC;
    }

    public int getSlVe() {
        return slVe;
    }

    public void setSlVe(int slVe) {
        this.slVe = slVe;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgayInHoaDon() {
        return ngayInHoaDon;
    }

    public void setNgayInHoaDon(String ngayInHoaDon) {
        this.ngayInHoaDon = ngayInHoaDon;
    }

    public int getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(int thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
}
