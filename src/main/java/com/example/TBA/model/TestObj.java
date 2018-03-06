package com.example.TBA.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_table")
public class TestObj {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public TestObj(Long id, String name){
        this.id = id;
        this.name = name;
    };

    public TestObj(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName(){
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Object: " + name;
    }
}
