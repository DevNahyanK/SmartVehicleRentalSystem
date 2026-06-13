/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author NahyanUser
 */
import model.Bike;
import model.Car;
import model.DataStore;
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

public class VehicleForm extends JFrame {
    private JComboBox<String> typeComboBox;
    private JTextField brandField;
    private JTextField modelField;
    private JTextField rentField;
    private JTextField extraField;
    private JTextArea vehicleArea;

    public VehicleForm() {
        setTitle("Manage Vehicles");
        setSize(750, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("Vehicle Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 37, 41));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 12));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        typeComboBox = new JComboBox<>(new String[]{"Car", "Bike"});
        brandField = new JTextField();
        modelField = new JTextField();
        rentField = new JTextField();
        extraField = new JTextField();

        addLabelAndField(formPanel, "Vehicle Type:", typeComboBox);
        addLabelAndField(formPanel, "Brand:", brandField);
        addLabelAndField(formPanel, "Model:", modelField);
        addLabelAndField(formPanel, "Rent Per Day:", rentField);
        addLabelAndField(formPanel, "Seats / Helmet yes-no:", extraField);

        JButton addButton = createButton("Add Vehicle", new Color(25, 118, 210));
        JButton refreshButton = createButton("Show Vehicles", new Color(46, 125, 50));

        addButton.addActionListener(e -> addVehicle());
        refreshButton.addActionListener(e -> showVehicles());

        formPanel.add(addButton);
        formPanel.add(refreshButton);

        vehicleArea = new JTextArea();
        vehicleArea.setEditable(false);
        vehicleArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        vehicleArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(vehicleArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Vehicle Records"));

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
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

    private void addVehicle() {
        try {
            String type = typeComboBox.getSelectedItem().toString();
            String brand = brandField.getText();
            String model = modelField.getText();
            double rent = Double.parseDouble(rentField.getText());
            String extra = extraField.getText();

            if (brand.isEmpty() || model.isEmpty() || extra.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            Vehicle vehicle;

            if (type.equals("Car")) {
                int seats = Integer.parseInt(extra);
                vehicle = new Car(DataStore.vehicleIdCounter, brand, model, rent, seats);
            } else {
                boolean hasHelmet = extra.equalsIgnoreCase("yes");
                vehicle = new Bike(DataStore.vehicleIdCounter, brand, model, rent, hasHelmet);
            }

            DataStore.vehicles.add(vehicle);
            DataStore.vehicleIdCounter++;

            JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
            clearFields();
            showVehicles();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check rent and extra field.");
        }
    }

    private void showVehicles() {
        vehicleArea.setText("");

        if (DataStore.vehicles.isEmpty()) {
            vehicleArea.setText("No vehicles added yet.");
            return;
        }

        for (Vehicle vehicle : DataStore.vehicles) {
            vehicleArea.append(vehicle.getDetails() + "\n");
        }
    }

    private void clearFields() {
        brandField.setText("");
        modelField.setText("");
        rentField.setText("");
        extraField.setText("");
    }
}
