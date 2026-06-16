package Drone;

import Common.Location;

public class HeavyDrone extends Drone {
    
    public HeavyDrone(int id, double maxWeight, double speed, String baseLoc) {
        super(id,maxWeight, new Location(baseLoc), speed);
        // Heavy drones use high-capacity battery configuration
        super.battery = new Battery(CellType.S6, 10000, 100); // Heavy drones have larger batteries
    }
    public HeavyDrone(int id, double maxWeight, double speed, Location baseLoc) {
        super(id,maxWeight, baseLoc, speed );
        // Heavy drones use high-capacity battery configuration
        super.battery = new Battery(CellType.S6, 10000, 100); // Heavy drones have larger batteries
    }

    // Simulates drone take-off process
    @Override
    public void takeOff() {
        setStatus("Taking Off");
        System.out.println("HeavyDrone " + getDroneID() + " is taking off.");
        updateBattery(5);
    }

    // Handles parcel delivery process to a given destination
    @Override
    public void deliverParcel(String destination) {
        setStatus("Delivering");
        System.out.println("Delivering parcel to " + destination);

        // Move in GPS simulation
        gps.setDestination( new Location(destination));
        gps.simulateTravel();

        updateBattery(20);
        System.out.println("Parcel delivered successfully.");
    }

    @Override
    public void returnToBase() {
        setStatus("Returning");
        gps.setDestination(super.baseLocation);
        gps.simulateTravel();

        updateBattery(10);
        setStatus("Idle");
        System.out.println("Drone returned to base.");
    }
    public double getSpeed() {
        return speed;
    }

    // @Override
    // public void deliverParcel(String destination) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
}
