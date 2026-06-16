package Drone;

import Common.Location;
import java.time.Duration;
import java.time.LocalDateTime;

public class GPSmodule {

    private Location currentLocation;
    private Location destination;

    private double droneSpeedKmh;   // speed in km/h

    private LocalDateTime lastUpdateTime;
    private LocalDateTime expectedArrivalTime;

    // -------------------- CONSTRUCTORS --------------------

    public GPSmodule(Location startLocation, double speedKmh) {
        this.currentLocation = startLocation;
        this.destination = startLocation;
        this.droneSpeedKmh = speedKmh;

        this.lastUpdateTime = LocalDateTime.now();
        this.expectedArrivalTime = LocalDateTime.now();
    }

//    public GPSmodule(Location startLocation) {
//        this(startLocation, 10.0); // default speed 10 km/h
//    }

    public GPSmodule() {}
    

    // -------------------- GETTERS --------------------

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getDestination() {
        return destination;
    }

//    public double getDroneSpeed() {
//        return this.droneSpeedKmh;
//    }
//
//    public LocalDateTime getExpectedArrivalTime() {
//        return expectedArrivalTime;
//    }
//
//    public LocalDateTime getLastUpdateTime() {
//        return lastUpdateTime;
//    }

    // -------------------- SETTERS --------------------

    public void setDestination(Location destination) {
        this.destination = destination;
        calculateETA();
    }

    public void setSpeed(double speedKmh) {
        this.droneSpeedKmh = speedKmh;
        calculateETA();
    }

    // -------------------- DISTANCE CALCULATION --------------------

    // Haversine distance
    public double calculateDistance(Location a, Location b) {

        final double R = 6371.0;

        double dLat = Math.toRadians(b.getLatitude() - a.getLatitude());
        double dLon = Math.toRadians(b.getLongitude() - a.getLongitude());

        double lat1 = Math.toRadians(a.getLatitude());
        double lat2 = Math.toRadians(b.getLatitude());

        double A = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);

        double C = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1 - A));

        return R * C;
    }

    public double getDistanceToDestination() {
        return calculateDistance(this.currentLocation, this.destination);
    }

    // -------------------- ETA CALCULATION --------------------

    private void calculateETA() {
        double distanceKm = getDistanceToDestination();

        if (this.droneSpeedKmh <= 0) {
            expectedArrivalTime = LocalDateTime.now();
            return;
        }

        double hours = distanceKm / this.droneSpeedKmh;
        long minutes = Math.round(hours * 60);

        expectedArrivalTime = LocalDateTime.now().plusMinutes(minutes);
    }

    public Duration getRemainingTime() {
        return Duration.between(LocalDateTime.now(), expectedArrivalTime);
    }

    // -------------------- SIMULATION --------------------

    public boolean simulateTravel() {
        System.out.println("----- GPS TRAVEL SIMULATION -----");
        System.out.println("Travel started at: " + lastUpdateTime);
        System.out.println("From: " + currentLocation);
        System.out.println("To:   " + destination);
        System.out.println("Speed: " + this.droneSpeedKmh + " km/h");
        System.out.println("Expect arrival: " + expectedArrivalTime);
        System.out.println("Remaining time: " + getRemainingTime().toMinutes() + " minutes");

        if (getRemainingTime().toMillis() <= 0) {
            currentLocation = destination;
            lastUpdateTime = LocalDateTime.now();
            System.out.println("Arrived at: " + lastUpdateTime);
            return true;
        } else {
            System.out.println("Drone is still en route...");
            return false;
        }
    }

    // -------------------- FORMATTED OUTPUT --------------------

    public String getGPSInfo() {
        Duration left = getRemainingTime();
        long minutes = left.toMinutes();
        if (minutes < 0) minutes = 0;

        return "\n\nCurrent Location: " + currentLocation + "\n" +
               "Destination: " + destination + "\n" +
               "Distance Remaining: " + String.format("%.2f", getDistanceToDestination()) + " km\n" +
               "Speed: " + this.droneSpeedKmh + " km/h\n" +
               "Last Update: " + lastUpdateTime + "\n" +
               "Expected Arrival: " + expectedArrivalTime + "\n" +
               "Time Remaining: " + minutes + " minutes\n\n\n";
    }


    public void updateLocation() {
        currentLocation = destination;
    }
}
