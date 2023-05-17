package com.example.springbootreferencesystemrestapi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Computers")
@Data
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "InventoryNumber")
    private String inventoryNumber;

    public Computer(){

    }
    public Computer(String name,String description, String inventoryNumber){
        this.name = name;
        this.description = description;
        this.inventoryNumber = inventoryNumber;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getInventoryNumber(){
        return inventoryNumber;
    }
}
