package com.example.duan1_nhom13.Model;

public class calam {
    private int maca;
    private String tenCa,gioBD,gioKT;

    public calam() {
    }

    public calam(int maca, String tenCa, String gioBD, String gioKT) {
        this.maca = maca;
        this.tenCa = tenCa;
        this.gioBD = gioBD;
        this.gioKT = gioKT;
    }

    public calam(String tenCa, String gioBD, String gioKT) {
        this.tenCa = tenCa;
        this.gioBD = gioBD;
        this.gioKT = gioKT;
    }

    public int getMaca() {
        return maca;
    }

    public void setMaca(int maca) {
        this.maca = maca;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public String getGioBD() {
        return gioBD;
    }

    public void setGioBD(String gioBD) {
        this.gioBD = gioBD;
    }

    public String getGioKT() {
        return gioKT;
    }

    public void setGioKT(String gioKT) {
        this.gioKT = gioKT;
    }
}
