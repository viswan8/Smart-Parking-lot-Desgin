package main.java.com.smartparking.repository;


import main.java.com.smartparking.model.ParkingSpot;
import main.java.com.smartparking.model.Ticket;
import java.util.*;

public class ParkingRepository {
    // Simulating DB Tables
    private final List<ParkingSpot> parkingSpots = new ArrayList<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    public void addSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public List<ParkingSpot> getAllSpots() {
        return parkingSpots;
    }

    public void saveTicket(Ticket ticket) {
        activeTickets.put(ticket.getTicketId(), ticket);
    }

    public Ticket getTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }
}