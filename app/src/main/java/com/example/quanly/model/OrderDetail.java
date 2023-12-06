package com.example.quanly.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private String name;
    private int  idOrder, quantity,  price;
    private String status;
    private int idStore, idService;
    private String img;

    public OrderDetail(String name, int idOrder, int quantity, int price, String status, int idStore, int idService, String img) {
        this.name = name;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.idStore = idStore;
        this.idService = idService;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }







    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SerVice{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
