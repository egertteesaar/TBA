package com.example.TBA.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock_table")
public class StockObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "media_url")
    private String media_url;

    public StockObj(Long id, String name, String type, String media_url){
        this.id = id;
        this.name = name;
        this.type = type;
        this.media_url = media_url;
    };

    public StockObj(){};

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

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StockObj{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", media_url='" + media_url + '\'' +
                '}';
    }
}
