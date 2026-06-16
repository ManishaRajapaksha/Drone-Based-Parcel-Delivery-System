package Drone;

import Common.Location;

public abstract class Drone {

    private final String droneID;
    private final double maxWeight;
    private String status; // Idle, Delivering, Charging

    protected Battery battery;
    public GPSmodule gps;
    protected Location baseLocation;
    protected double speed;

//    protected static int droenCount;

    public Drone(int id, double maxWeight, Location baseLocation, double speed) {
        this.droneID = "DR" + id;
        this.maxWeight = maxWeight;
        this.status = "Idle";
        this.speed = speed;
        this.gps = new GPSmodule(baseLocation,speed);
        this.baseLocation = baseLocation;
    }

    public String getDroneID() { return droneID; }
    public int getIdNumber() {
        return Integer.parseInt(droneID.substring(2));
    }
    public double getMaxWeight() { return maxWeight; }
    public String getStatus() { return status; }
    public double getSpeed() { return speed; }
    public Battery getBattery() { return battery; }
    public GPSmodule getGpsModule() { return gps; }
    
    public void setStatus(String status) { this.status = status; }
    public abstract void takeOff();
    public abstract void deliverParcel(String destination);
    public abstract void returnToBase();


    public void simulateTravel() {
        gps.simulateTravel();
    }
    public void setDestination(Location destination) {
        gps.setDestination(destination);
    }


    public void updateBattery(int amount) {
        battery.drain(amount);
    }
    public void rechargeBattery() {
        battery.recharge();
    }
    public int getBatteryLevel() {
        return battery.getChargeLevel();
    }


    public boolean isAvailable() {
        return status.equals("Idle");
    }


    public void updateDrone() {
        updateBattery(1);
        gps.updateLocation();
    }

    public Location getLocation() {
        return gps.getCurrentLocation();
    }

    @Override
    public String toString() {
        return "Drone ID: " + droneID + ", Max Weight: " + maxWeight + "kg, Status: " + status +
               ", Battery Level: " + battery.getChargeLevel() + "%, Location: " + gps.getCurrentLocation();
    }

}
