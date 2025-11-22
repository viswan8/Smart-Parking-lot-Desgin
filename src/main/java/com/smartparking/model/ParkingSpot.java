package main.java.com.smartparking.model;


import main.java.com.smartparking.constants.SpotType;

import com.smartparking.constants.SpotType;

public class ParkingSpot {
    private int id;
    private int floor;
    private SpotType type;
    private boolean isFree;
    private Vehicle vehicle;

    public ParkingSpot(int id, int floor, SpotType type) {
        this.id = id;
        this.floor = floor;
        this.type = type;
        this.isFree = true;
    }

    public synchronized void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isFree = false;
    }

    public synchronized void removeVehicle() {
        this.vehicle = null;
        this.isFree = true;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getFloor() { return floor; }
    public SpotType getType() { return type; }
    public boolean isFree() { return isFree; }
    public Vehicle getVehicle() { return vehicle; }

    @Override
    public String toString() {
        return "ParkingSpot{id=" + id + ", floor=" + floor + ", type=" + type + "}";
    }
}