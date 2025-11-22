package main.java.com.smartparking.strategy.pricing;

import main.java.com.smartparking.model.Ticket;
import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy {

    private static final double BASE_RATE = 2.0;

    @Override
    public double calculateAmount(Ticket ticket) {
        // For simulation, if exit time is same as entry, we assume 1 hour min
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        long hours = duration.toHours();
        if (hours == 0) hours = 1;

        return hours * BASE_RATE;
    }
}