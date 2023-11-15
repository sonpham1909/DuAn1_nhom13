package com.example.duan1_nhom13.Model;

public class loaisach {
    private int maloai;
    private String tenloai;

    public loaisach() {
    }

    public loaisach(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public loaisach(String ten) {
        this.tenloai =ten;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
