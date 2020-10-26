package com.invoiceservice;

public class InvoiceGenerator {
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KM = 10;
    private static final double MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KM = 15;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;

    public double calculateFare(double distance, int time) {
        double fare = distance * MINIMUM_COST_PER_KM +
                time * COST_PER_TIME;
        return Math.max(fare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return calculateFare(RideRepository.userRideMap.get(userId));
    }

    public void addRides(Ride[] rides, String userId) {
        RideRepository.userRideMap.put(userId, rides);
    }

    public double calculatePremiumFare(double distance, int time) {
        double fare = distance * PREMIUM_MINIMUM_COST_PER_KM +
                time * PREMIUM_COST_PER_TIME;
        return Math.max(fare, PREMIUM_MINIMUM_FARE);
    }
}
