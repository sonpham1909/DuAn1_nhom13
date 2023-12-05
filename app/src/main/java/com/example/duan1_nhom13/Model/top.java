package com.example.duan1_nhom13.Model;

public class top {
    private String tenPhim;
    private int sove;

    public top() {
    }

    public top(String tenPhim, int sove) {
        this.tenPhim = tenPhim;
        this.sove = sove;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public int getSove() {
        return sove;
    }

    public void setSove(int sove) {
        this.sove = sove;
    }
}
