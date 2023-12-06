package com.sdpm.team3.model;
import jakarta.persistence.*;
@Entity
public class Skills {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;


    @Column
    private String name;

//getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
