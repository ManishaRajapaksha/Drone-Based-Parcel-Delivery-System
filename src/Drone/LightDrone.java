package Drone;

import Common.Location;

public class LightDrone extends Drone {

    // Constructor using base location as String input
    public LightDrone(int id, double maxWeight, double speed, String baseLoc) {
        super(id,maxWeight, new Location(baseLoc), speed);
        super.battery = new Battery(CellType.S4, 5000, 100);
    }

    // Constructor using Location object directly
    public LightDrone(int id, double maxWeight, double speed, Location baseLoc) {
        super(id,maxWeight, baseLoc, speed);
        // super.speed = speed;
        super.battery = new Battery(CellType.S4, 5000, 100);
        // super.gps.setSpeed(speed);
    }

    // Simulates drone take-off process
    @Override
    public void takeOff() {
        setStatus("Taking Off");
        System.out.println("LightDrone " + getDroneID() + " is taking off.");
        updateBattery(5);
    }

    // Handles parcel delivery process to destination using GPS simulation
    @Override
    public void deliverParcel(String destination) {
        setStatus("Delivering");
        System.out.println("Delivering parcel to " + destination);

        // Move in GPS simulation
        gps.setDestination(new Location(destination));
        gps.simulateTravel();

        updateBattery(20);
        System.out.println("Parcel delivered successfully.");
    }

    // Returns drone back to its base location
    @Override
    public void returnToBase() {
        setStatus("Returning");
        gps.setDestination(super.baseLocation);
        gps.simulateTravel();

        updateBattery(10);
        setStatus("Idle");
        System.out.println("Drone returned to base.");
    }

    // Returns current speed of the drone
    public double getSpeed() {
        return speed;
    }
}
