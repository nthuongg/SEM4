package org.example.exam.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int id;

    @Column(name = "vehicle_name")
    private String name;

    @Column(name = "vehicle_model")
    private String model;

    @Column(name = "year_of_manufacture")
    private int yearOfManufacture;

    @Column(name = "vehicle_color")
    private String color;

    public Vehicle() {}

    public Vehicle(int id, String name, String model, int yearOfManufacture, String color) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}