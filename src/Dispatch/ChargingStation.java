package Dispatch;

import Common.Location;
import Drone.Drone;

public class ChargingStation {
    private final String stationId;
    private final int capacity;       // max drones that can charge
    private int currentDrones;  // currently charging drones
    private static int StationCount = 1;
    private Location location;

    public ChargingStation(int capacity) {
        this.stationId = "ST" + StationCount++;
        this.capacity = capacity;
        this.currentDrones = 0;
    }

    public ChargingStation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isFull() {
        return currentDrones >= capacity;
    }

    public boolean chargeDrone(Drone drone) {
        if (isFull()) {
            System.out.println("Station full! Drone cannot charge now.");
            return false;
        }

        System.out.println("Drone " + drone.getDroneID() + " is charging...");
        drone.rechargeBattery();  // uses __Battery internally
        currentDrones++;
        return true;
    }

    public void droneLeft() {
        if (currentDrones > 0) {
            currentDrones--;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStationId() {
        return stationId;
    }
}
