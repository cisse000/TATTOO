package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="role_generator", sequenceName = "role_id_seq")
    private int id;
    @Column
    private String name;

    public Role() {
    }

    //le constructeur
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // les getter et setter
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