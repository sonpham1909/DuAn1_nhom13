package com.example.duan1_nhom13.Model;

public class user {
    private String user;
    private String ten;
    private String pass;
    private int role;

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public user(String user, String ten, int role, String pass, String sodienthoai) {
        this.user = user;
        this.ten = ten;
        this.role = role;
        this.pass = pass;

        this.sodienthoai = sodienthoai;
    }

    private String sodienthoai;

    public user() {
    }

    public user(String user, String ten, String pass, int role) {
        this.user = user;
        this.ten = ten;
        this.pass = pass;
        this.role = role;
    }

    public user(String ten, int role) {
        this.ten = ten;
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
