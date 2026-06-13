/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NahyanUser
 */

public class Rental {
    private int rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalBill;

    public Rental(int rentalId, Customer customer, Vehicle vehicle, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalBill = calculateBill();
        this.vehicle.setAvailable(false);
    }

    public int getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public double calculateBill() {
        return vehicle.getRentPerDay() * days;
    }

    public void returnVehicle() {
        vehicle.setAvailable(true);
    }

    public String getDetails() {
        return "Rental ID: " + rentalId
                + " | Customer: " + customer.getName()
                + " | Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel()
                + " | Days: " + days
                + " | Bill: " + totalBill;
    }
}
