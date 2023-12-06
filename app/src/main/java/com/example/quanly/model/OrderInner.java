package com.example.quanly.model;

public class OrderInner {
    private int  quantitty,  price;
    private String img, name, description, address;

    public OrderInner(int quantitty, int price, String img, String name, String description, String address) {
        this.quantitty = quantitty;
        this.price = price;
        this.img = img;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public int getQuantitty() {
        return quantitty;
    }

    public void setQuantitty(int quantitty) {
        this.quantitty = quantitty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderInner{" +
                "quantitty=" + quantitty +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
