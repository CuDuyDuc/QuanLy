package com.example.quanly.model;

public class ThongKe {
    private String doanhthu, hoadon, dichvu;

    public ThongKe(String doanhthu, String hoadon, String dichvu) {
        this.doanhthu = doanhthu;
        this.hoadon = hoadon;
        this.dichvu = dichvu;
    }

    public String getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(String doanhthu) {
        this.doanhthu = doanhthu;
    }

    public String getHoadon() {
        return hoadon;
    }

    public void setHoadon(String hoadon) {
        this.hoadon = hoadon;
    }

    public String getDichvu() {
        return dichvu;
    }

    public void setDichvu(String dichvu) {
        this.dichvu = dichvu;
    }

    @Override
    public String toString() {
        return "ThongKe{" +
                "doanhthu='" + doanhthu + '\'' +
                ", hoadon='" + hoadon + '\'' +
                ", dichvu='" + dichvu + '\'' +
                '}';
    }
}
