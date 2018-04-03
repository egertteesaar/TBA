package com.example.TBA.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock_table")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;
    @Column(name = "image")
    private String image;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private Long price;

    public Item(String brand, String name, String description, String type, String image, String color, Long price) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.type = type;
        this.image = image;
        this.color = color;
        this.price = price;
    }

    public Item(){};

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
