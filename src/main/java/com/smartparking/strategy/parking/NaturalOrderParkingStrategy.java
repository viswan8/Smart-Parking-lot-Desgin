package main.java.com.smartparking.strategy.parking;

import main.java.com.smartparking.constants.VehicleType;
import main.java.com.smartparking.model.ParkingSpot;

import java.main.com.smartparking.constants.SpotType;
import java.main.com.smartparking.constants.VehicleType;
import java.main.com.smartparking.model.ParkingSpot;

import java.util.List;

public class NaturalOrderParkingStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(VehicleType vehicleType, List<ParkingSpot> spots) {
        // Simple Logic: Return first available spot that matches requirement
        for (ParkingSpot spot : spots) {
            if (spot.isFree() && isFit(spot, vehicleType)) {
                return spot;
            }
        }
        return null;
    }

    private boolean isFit(ParkingSpot spot, VehicleType vehicleType) {
        if (vehicleType == VehicleType.TRUCK) {
            return spot.getType() == SpotType.LARGE;
        }
        // Cars can fit in Compact or Large
        if (vehicleType == VehicleType.CAR) {
            return spot.getType() == SpotType.COMPACT || spot.getType() == SpotType.LARGE;
        }
        return false;
    }
}