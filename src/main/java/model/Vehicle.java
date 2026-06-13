/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NahyanUser
 */

public abstract class Vehicle {
    private int vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean available;

    public Vehicle(int vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.available = true;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getVehicleType();

    public String getDetails() {
        return vehicleId + " - " + getVehicleType() + " - " + brand + " " + model
                + " - Rent: " + rentPerDay + "/day"
                + " - " + (available ? "Available" : "Rented");
    }
}