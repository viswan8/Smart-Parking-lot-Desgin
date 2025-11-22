package main.java.com.smartparking.model;


import main.java.com.smartparking.constants.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}
