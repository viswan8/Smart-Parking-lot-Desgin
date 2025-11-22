package main.java.com.smartparking.model;


import main.java.com.smartparking.constants.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticket {
    private String ticketId;
    private String licensePlate;
    private ParkingSpot assignedSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;
    private PaymentStatus paymentStatus;

    public Ticket(String ticketId, String licensePlate, ParkingSpot assignedSpot) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.assignedSpot = assignedSpot;
        this.entryTime = LocalDateTime.now();
        this.paymentStatus = PaymentStatus.PENDING;
    }
}