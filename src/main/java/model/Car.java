/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NahyanUser
 */

public class Car extends Vehicle {
    private int numberOfSeats;

    public Car(int vehicleId, String brand, String model, double rentPerDay, int numberOfSeats) {
        super(vehicleId, brand, model, rentPerDay);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " - Seats: " + numberOfSeats;
    }
}
