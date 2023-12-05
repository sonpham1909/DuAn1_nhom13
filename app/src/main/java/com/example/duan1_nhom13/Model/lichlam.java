package com.example.duan1_nhom13.Model;

public class lichlam {
    private int malich;
    private String manv;
    private int maca;
    private String NBD;
    private String NKT;

    public lichlam() {
    }

    public lichlam(int malich, String manv, int maca, String NBD, String NKT) {
        this.malich = malich;
        this.manv = manv;
        this.maca = maca;
        this.NBD = NBD;
        this.NKT = NKT;
    }

    public lichlam(String manv, int maca, String NBD, String NKT) {
        this.manv = manv;
        this.maca = maca;
        this.NBD = NBD;
        this.NKT = NKT;
    }

    public int getMalich() {
        return malich;
    }

    public void setMalich(int malich) {
        this.malich = malich;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public int getMaca() {
        return maca;
    }

    public void setMaca(int maca) {
        this.maca = maca;
    }

    public String getNBD() {
        return NBD;
    }

    public void setNBD(String NBD) {
        this.NBD = NBD;
    }

    public String getNKT() {
        return NKT;
    }

    public void setNKT(String NKT) {
        this.NKT = NKT;
    }
}
