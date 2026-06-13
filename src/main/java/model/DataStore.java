/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NahyanUser
 */

import java.util.ArrayList;

public class DataStore {
    public static ArrayList<Vehicle> vehicles = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Rental> rentals = new ArrayList<>();

    public static int vehicleIdCounter = 1;
    public static int customerIdCounter = 1;
    public static int rentalIdCounter = 1;
}