package main.java.com.smartparking.strategy.parking;

import main.java.com.smartparking.constants.VehicleType;
import main.java.com.smartparking.model.ParkingSpot;
import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findSpot(VehicleType vehicleType, List<ParkingSpot> spots);
}
