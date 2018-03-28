package com.example.TBA.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock_table")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "image")
    private String image;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private Long price;

    public Stock(String brand, String name, String type, String image, Long price) {
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.image = image;
        this.price = price;
    }

    public Stock(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName(){
        return name;

    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
