package main.java.com.smartparking;


import java.main.com.smartparking.constants.SpotType;
import java.main.com.smartparking.model.Car;
import java.main.com.smartparking.model.Ticket;
import java.main.com.smartparking.model.Truck;
import java.main.com.smartparking.model.Vehicle;
import java.main.com.smartparking.repository.ParkingRepository;
import java.main.com.smartparking.service.ParkingLotManager;
import java.main.com.smartparking.strategy.parking.NaturalOrderParkingStrategy;
import java.main.com.smartparking.strategy.pricing.HourlyPricingStrategy;

public class Main {
    public static <ParkingLotManager> void main(String[] args) {
        System.out.println("=== INITIALIZING SMART PARKING LOT SYSTEM ===");

        // 1. Setup Dependencies
        ParkingRepository repository = new ParkingRepository();
        NaturalOrderParkingStrategy parkingStrategy = new NaturalOrderParkingStrategy();
        HourlyPricingStrategy pricingStrategy = new HourlyPricingStrategy();

        // 2. Get Singleton Instance
        ParkingLotManager parkingLot = ParkingLotManager.getInstance(repository, parkingStrategy, pricingStrategy);

        // 3. Initialize Parking Spots
        // Floor 1: 5 Compact spots
        parkingLot.addParkingSpots(5, SpotType.COMPACT, 1);
        // Floor 1: 2 Large spots
        parkingLot.addParkingSpots(2, SpotType.LARGE, 1);

        System.out.println("\n=== SIMULATION START ===\n");

        // Scenario 1: Park a Car
        Vehicle myCar = new Car("KA-01-HH-1234");
        Ticket carTicket = parkingLot.parkVehicle(myCar);

        // Scenario 2: Park a Truck
        Vehicle myTruck = new Truck("KA-01-HH-9999");
        Ticket truckTicket = parkingLot.parkVehicle(myTruck);

        // Scenario 3: Try to park another Truck (Only 2 large spots, assume 1 taken or logic fills first)
        Vehicle anotherTruck = new Truck("MH-02-ZZ-8888");
        parkingLot.parkVehicle(anotherTruck);

        // Scenario 4: Car Exits
        System.out.println("\n--- Car Exiting ---");
        parkingLot.exitVehicle(carTicket.getTicketId());
        parkingLot.processPayment(carTicket.getTicketId(), 4.0); // Paying $4

        // Check spot status internally
        System.out.println("\n=== SIMULATION END ===");
    }
}