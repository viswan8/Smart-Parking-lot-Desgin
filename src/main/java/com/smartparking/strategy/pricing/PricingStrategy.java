package main.java.com.smartparking.strategy.pricing;

import java.main.com.smartparking.model.Ticket;

public interface PricingStrategy {
    double calculateAmount(Ticket ticket);
}