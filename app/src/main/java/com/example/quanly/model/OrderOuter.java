package com.example.quanly.model;

public class OrderOuter {
//    $username , $iddonhang, $date, $totalprice
    private String usernmae, date, iddonhang,  totalprice;

    public OrderOuter(String usernmae, String date, String iddonhang, String totalprice) {
        this.usernmae = usernmae;
        this.date = date;
        this.iddonhang = iddonhang;
        this.totalprice = totalprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }

    public String getIddonhang() {
        return iddonhang;
    }

    public void setIddonhang(String iddonhang) {
        this.iddonhang = iddonhang;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "OrderOuter{" +
                "usernmae='" + usernmae + '\'' +
                ", iddonhang='" + iddonhang + '\'' +
                ", totalprice='" + totalprice + '\'' +
                '}';
    }
}
