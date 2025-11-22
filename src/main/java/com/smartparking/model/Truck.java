package main.java.com.smartparking.model;

import main.java.com.smartparking.constants.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}