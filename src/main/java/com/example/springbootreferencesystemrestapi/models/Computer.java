package com.example.springbootreferencesystemrestapi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Component> components;

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
    public void addComponent(Component component) {
        components.add(component);
    }
}
