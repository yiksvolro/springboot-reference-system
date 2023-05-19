package com.example.springbootreferencesystemrestapi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Components")
@Data
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Type", nullable = false)
    private String type;
    @Column(name = "InventoryNumber")
    private String inventoryNumber;
    @Column(name = "Brand")
    private String brand;
    @Column(name = "IsFree")
    private boolean isFree;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "computer_id")
    Computer computer;
    public Component(){

    }
    public Component(int id, String type, String inventoryNumber, String brand, boolean isFree, Computer computer){
        this.id = id;
        this.type = type;
        this.inventoryNumber = inventoryNumber;
        this.brand = brand;
        this.isFree = isFree;
        this.computer = computer;
    }
    public int getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public String getInventoryNumber(){
        return inventoryNumber;
    }
    public String getBrand(){
        return brand;
    }
    public boolean getIsFree(){
        return isFree;
    }
    public void setComputer(Computer computer){
        this.computer = computer;
        this.isFree = (computer == null);
    }
}
