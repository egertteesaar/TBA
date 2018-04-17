package com.example.TBA.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "customer_info")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="ip_address")
    private String ip_address;

    @Column(name="browser")
    private String browser;

    @Column(name = "created_date")
    private Timestamp date;

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getBrowser() {

        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
