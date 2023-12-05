package com.example.duan1_nhom13.Model;

public class phonchieu {
    private int maPC;
    private String tenPC;
    private int soGhe;

    public phonchieu(String tenPC, int soGhe) {
        this.tenPC = tenPC;
        this.soGhe = soGhe;
    }

    public phonchieu() {
    }

    public phonchieu(int soGhe) {
        this.soGhe = soGhe;
    }

    public phonchieu(int maPC, String tenPC, int soGhe) {
        this.maPC = maPC;
        this.tenPC = tenPC;
        this.soGhe = soGhe;
    }

    public int getMaPC() {
        return maPC;
    }

    public void setMaPC(int maPC) {
        this.maPC = maPC;
    }

    public String getTenPC() {
        return tenPC;
    }

    public void setTenPC(String tenPC) {
        this.tenPC = tenPC;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
}
