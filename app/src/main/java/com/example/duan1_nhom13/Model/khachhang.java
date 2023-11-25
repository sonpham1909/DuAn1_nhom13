package com.example.duan1_nhom13.Model;

public class khachhang {
    private int maKH;
    private String tenKH;
    private String soKH;

    public khachhang() {
    }

    public khachhang(int maKH, String tenKH, String soKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soKH = soKH;
    }

    public khachhang(String tenKH, String soKH) {
        this.tenKH = tenKH;
        this.soKH = soKH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoKH() {
        return soKH;
    }

    public void setSoKH(String soKH) {
        this.soKH = soKH;
    }
}
