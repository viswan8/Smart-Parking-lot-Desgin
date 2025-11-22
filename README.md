# 🚗 Smart Parking Lot System - Low Level Design (LLD)

A robust, scalable, and object-oriented backend design for a multi-story Smart Parking Lot system. This project demonstrates **Core Java** competency, focusing on **Design Patterns**, **SOLID Principles**, and clean architecture without relying on heavy frameworks like Spring.

---

## 📖 Project Overview
This system manages the lifecycle of a vehicle entering and exiting a parking structure. It simulates real-world logic including:
1.  **Spot Allocation:** Automatically finding the best available spot based on vehicle size (e.g., Trucks need Large spots).
2.  **Floor Management:** Managing multiple floors with different spot types (Compact, Large, Motorcycle).
3.  **Fee Calculation:** Dynamic pricing strategies based on parking duration.
4.  **Payment Flow:** Handling ticket validation and payment processing.

---

## 🏗 Architecture & Design Patterns Used
This project is designed to be extensible and maintainable. Key patterns include:

### 1. Singleton Pattern
*   **Where:** `ParkingLotManager` class.
*   **Why:** To ensure a single central point of control for the parking lot state (spots, tickets, revenue) across the application.

### 2. Strategy Pattern
*   **Where:** 
    *   `ParkingStrategy`: Allows swapping algorithms for finding spots (e.g., *Natural Ordering*, *Nearest to Elevator*, or *First Available*).
    *   `PricingStrategy`: Allows changing fee logic (e.g., *Hourly*, *Flat Rate*, or *Dynamic Pricing*) without modifying the core vehicle logic.
*   **Why:** Adheres to the **Open/Closed Principle**. We can extend strategies without modifying existing code.

### 3. Repository Pattern
*   **Where:** `ParkingRepository`.
*   **Why:** Decouples the business logic from the data storage. Currently uses in-memory `HashMap` and `ArrayList` for simulation, but can be easily swapped for a real SQL Database without breaking the service layer.

### 4. Factory / Polymorphism
*   **Where:** `Vehicle` hierarchy (`Car`, `Truck`, etc.).
*   **Why:** The system treats all vehicles uniformly via the base class but handles their specific constraints (like spot requirements) via polymorphic behavior.

---

## 📂 Project Structure

```text
src/main/java/com/smartparking
├── Main.java                     // Entry point (Simulation runner)
├── constants                     // Enums (VehicleType, SpotType, PaymentStatus)
├── model                         // Domain Entites (Vehicle, ParkingSpot, Ticket)
├── repository                    // Data Access Layer (In-Memory Simulation)
├── service                       // Business Logic Layer (ParkingLotManager)
└── strategy                      // Behavioral Strategies
    ├── parking                   // Algorithms to find spots
    └── pricing                   // Algorithms to calculate fees
