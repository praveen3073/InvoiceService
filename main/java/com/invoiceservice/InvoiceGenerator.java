package com.invoiceservice;

public class InvoiceGenerator {
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KM = 10;

    public double calculateFare(double distance, int time) {
        double fare = distance * MINIMUM_COST_PER_KM +
        time * COST_PER_TIME;
        return fare>5 ? fare : 5;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
