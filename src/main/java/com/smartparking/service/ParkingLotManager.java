package main.java.com.smartparking.service;

import java.main.com.smartparking.model.*;
import java.main.com.smartparking.repository.ParkingRepository;
import java.main.com.smartparking.constants.PaymentStatus;
import java.main.com.smartparking.strategy.parking.ParkingStrategy;
import java.main.com.smartparking.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingLotManager {

    private static ParkingLotManager instance;

    private final ParkingRepository repository;
    private final ParkingStrategy parkingStrategy;
    private final PricingStrategy pricingStrategy;

    private ParkingLotManager(ParkingRepository repository,
                              ParkingStrategy parkingStrategy,
                              PricingStrategy pricingStrategy) {
        this.repository = repository;
        this.parkingStrategy = parkingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public static ParkingLotManager getInstance(ParkingRepository repo,
                                                ParkingStrategy parkStrat,
                                                PricingStrategy priceStrat) {
        if (instance == null) {
            instance = new ParkingLotManager(repo, parkStrat, priceStrat);
        }
        return instance;
    }

    public void addParkingSpots(int count, com.smartparking.constants.SpotType type, int floor) {
        for (int i = 1; i <= count; i++) {
            repository.addSpot(new ParkingSpot(i + (floor * 100), floor, type));
        }
        System.out.println("Added " + count + " " + type + " spots on floor " + floor);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = parkingStrategy.findSpot(vehicle.getType(), repository.getAllSpots());

        if (spot == null) {
            throw new RuntimeException("NO PARKING SPOT AVAILABLE FOR " + vehicle.getType());
        }

        spot.assignVehicle(vehicle);

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle.getLicensePlate(), spot);
        repository.saveTicket(ticket);

        System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked at Spot " + spot.getId());
        return ticket;
    }

    public Ticket exitVehicle(String ticketId) {
        Ticket ticket = repository.getTicket(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid Ticket ID");
        }

        // Simulate exit time being later
        ticket.setExitTime(LocalDateTime.now().plusHours(2)); // Mocking 2 hours stay

        double amount = pricingStrategy.calculateAmount(ticket);
        ticket.setAmount(amount);

        System.out.println("Vehicle " + ticket.getLicensePlate() + " exiting. Fee: $" + amount);
        return ticket;
    }

    public void processPayment(String ticketId, double amountPaid) {
        Ticket ticket = repository.getTicket(ticketId);
        if (amountPaid >= ticket.getAmount()) {
            ticket.setPaymentStatus(PaymentStatus.COMPLETED);
            ticket.getAssignedSpot().removeVehicle();
            System.out.println("Payment Successful. Gate Open for " + ticket.getLicensePlate());
        } else {
            System.out.println("Insufficient Payment.");
        }
    }
}
