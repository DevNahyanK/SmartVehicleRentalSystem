    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NahyanUser
 */

public class Bike extends Vehicle {
    private boolean hasHelmet;

    public Bike(int vehicleId, String brand, String model, double rentPerDay, boolean hasHelmet) {
        super(vehicleId, brand, model, rentPerDay);
        this.hasHelmet = hasHelmet;
    }

    public boolean hasHelmet() {
        return hasHelmet;
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " - Helmet: " + (hasHelmet ? "Yes" : "No");
    }
}