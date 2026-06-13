/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author NahyanUser
 */
import model.Customer;
import model.DataStore;
import model.Rental;
import model.Vehicle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class RentalForm extends JFrame {
    private JTextField customerNameField;
    private JTextField phoneField;
    private JTextField daysField;
    private JComboBox<String> vehicleComboBox;
    private JTextArea rentalArea;

    public RentalForm() {
        setTitle("Rent Vehicle");
        setSize(800, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("Rental Booking");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 37, 41));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 12));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        customerNameField = new JTextField();
        phoneField = new JTextField();
        daysField = new JTextField();
        vehicleComboBox = new JComboBox<>();

        addLabelAndField(formPanel, "Customer Name:", customerNameField);
        addLabelAndField(formPanel, "Phone:", phoneField);
        addLabelAndField(formPanel, "Rental Days:", daysField);
        addLabelAndField(formPanel, "Available Vehicle:", vehicleComboBox);

        JButton rentButton = createButton("Create Rental", new Color(25, 118, 210));
        JButton refreshButton = createButton("Refresh Vehicles", new Color(46, 125, 50));
        JButton showButton = createButton("Show Rentals", new Color(96, 125, 139));

        rentButton.addActionListener(e -> createRental());
        refreshButton.addActionListener(e -> loadAvailableVehicles());
        showButton.addActionListener(e -> showRentals());

        formPanel.add(rentButton);
        formPanel.add(refreshButton);
        formPanel.add(showButton);
        formPanel.add(new JLabel(""));

        rentalArea = new JTextArea();
        rentalArea.setEditable(false);
        rentalArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        rentalArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(rentalArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Rental Records"));

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        loadAvailableVehicles();
    }

    private void addLabelAndField(JPanel panel, String labelText, java.awt.Component field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(60, 60, 60));
        panel.add(label);
        panel.add(field);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        return button;
    }

    private void loadAvailableVehicles() {
        vehicleComboBox.removeAllItems();

        for (Vehicle vehicle : DataStore.vehicles) {
            if (vehicle.isAvailable()) {
                vehicleComboBox.addItem(vehicle.getVehicleId() + " - " + vehicle.getDetails());
            }
        }

        if (vehicleComboBox.getItemCount() == 0) {
            vehicleComboBox.addItem("No vehicle available");
        }
    }

    private void createRental() {
        try {
            if (vehicleComboBox.getSelectedItem() == null ||
                    vehicleComboBox.getSelectedItem().toString().equals("No vehicle available")) {
                JOptionPane.showMessageDialog(this, "No available vehicle found.");
                return;
            }

            String name = customerNameField.getText();
            String phone = phoneField.getText();
            int days = Integer.parseInt(daysField.getText());

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill customer details.");
                return;
            }

            String selectedVehicleText = vehicleComboBox.getSelectedItem().toString();
            int vehicleId = Integer.parseInt(selectedVehicleText.split(" - ")[0]);

            Vehicle selectedVehicle = null;

            for (Vehicle vehicle : DataStore.vehicles) {
                if (vehicle.getVehicleId() == vehicleId) {
                    selectedVehicle = vehicle;
                    break;
                }
            }

            if (selectedVehicle == null) {
                JOptionPane.showMessageDialog(this, "Vehicle not found.");
                return;
            }

            Customer customer = new Customer(DataStore.customerIdCounter, name, phone);
            DataStore.customers.add(customer);
            DataStore.customerIdCounter++;

            Rental rental = new Rental(DataStore.rentalIdCounter, customer, selectedVehicle, days);
            DataStore.rentals.add(rental);
            DataStore.rentalIdCounter++;

            JOptionPane.showMessageDialog(this,
                    "Rental created successfully.\nTotal Bill: " + rental.getTotalBill());

            clearFields();
            loadAvailableVehicles();
            showRentals();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check days field.");
        }
    }

    private void showRentals() {
        rentalArea.setText("");

        if (DataStore.rentals.isEmpty()) {
            rentalArea.setText("No rentals created yet.");
            return;
        }

        for (Rental rental : DataStore.rentals) {
            rentalArea.append(rental.getDetails() + "\n");
        }
    }

    private void clearFields() {
        customerNameField.setText("");
        phoneField.setText("");
        daysField.setText("");
    }
}