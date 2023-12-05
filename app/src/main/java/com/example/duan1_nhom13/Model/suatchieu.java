package com.example.duan1_nhom13.Model;

import android.widget.TextView;

public class suatchieu {
    private int maSC;
    private String tenSC,ngaychieu,giochieu;
    private int maPC;

    public suatchieu() {
    }

    public suatchieu(int maSC, String tenSC, String ngaychieu, String giochieu, int maPC) {
        this.maSC = maSC;
        this.tenSC = tenSC;
        this.ngaychieu = ngaychieu;
        this.giochieu = giochieu;
        this.maPC = maPC;
    }

    public suatchieu(String tenSC, String ngaychieu, String giochieu, int maPC) {
        this.tenSC = tenSC;
        this.ngaychieu = ngaychieu;
        this.giochieu = giochieu;
        this.maPC = maPC;
    }

    public int getMaSC() {
        return maSC;
    }

    public void setMaSC(int maSC) {
        this.maSC = maSC;
    }

    public String getTenSC() {
        return tenSC;
    }

    public void setTenSC(String tenSC) {
        this.tenSC = tenSC;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public String getGiochieu() {
        return giochieu;
    }

    public void setGiochieu(String giochieu) {
        this.giochieu = giochieu;
    }

    public int getMaPC() {
        return maPC;
    }

    public void setMaPC(int maPC) {
        this.maPC = maPC;
    }
}
