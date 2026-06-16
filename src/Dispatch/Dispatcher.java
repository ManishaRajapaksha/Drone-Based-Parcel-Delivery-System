package Dispatch;

import Customer.DeliveryRequest;
import Drone.Drone;
import Common.Location;
import Drone.HeavyDrone;
import Drone.LightDrone;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    // public List<Drone> droneFleet = new ArrayList<>();
    private final WeatherSystem weatherSystem;
    private final ChargingStation chargingStation;
    private final Location dispatcherLocation;
    private int droneCount = 0;
    public Drone[] droneFleet;
    // ---------------- Constructor ----------------
    public Dispatcher(WeatherSystem weatherSystem, ChargingStation chargingStation, Location stationLocation, int droneCount) {
        this.weatherSystem = weatherSystem;
        this.chargingStation = chargingStation;
        this.dispatcherLocation = stationLocation;
        this.droneCount = droneCount;
        this.droneFleet = new Drone[droneCount];

    }
    public Drone getDrone(int index) {
        if (index >= 0 && index < droneFleet.length) {
            return droneFleet[index];
        }
        return null;
    }
    // ---------------- Register Drones ----------------
    public void registerDrone(Drone drone,int index) {
        droneFleet[index] = drone;
        System.out.println("[Dispatcher] Drone Registered: " + drone.getDroneID());
    }
    public void registerDrone(HeavyDrone drone,int index) {
        droneFleet[index] = drone;
        System.out.println("[Dispatcher] Drone Registered: " + drone.getDroneID());
    }
    public void registerDrone(LightDrone drone,int index) {
        droneFleet[index] = drone;
        System.out.println("[Dispatcher] Drone Registered: " + drone.getDroneID());
    }

    // ---------------- Main Assignment Logic ----------------
    public Drone assignDrone(DeliveryRequest request, double carryWeight) {

        System.out.println("\n[Dispatcher] Searching for available drone...");

        if (!weatherSystem.isSafeToFly()) {
            System.out.println("[Dispatcher] Weather unsafe! Cannot assign drone.");
            return null;
        }

        for (Drone drone : droneFleet) {
            if (drone == null) continue;
            if (drone.isAvailable() && drone.getBatteryLevel()  > 40 && drone.getMaxWeight() >= carryWeight) {

                System.out.println("[Dispatcher] Drone " + drone.getDroneID() +
                                   " assigned for delivery request " + request.getRequestId());

                // drone.assignRequest(request); // simple simulation
                return drone;
            }
        }

        System.out.println("[Dispatcher] No drone available!");
        return null;
    }

    // ---------------- Update All Drones ----------------
    public void updateDroneFleet() {
        for (Drone drone : droneFleet) {
            drone.updateDrone();   // battery drain + gps update

            // If battery is low, send to charging station
            if (drone.getBatteryLevel() < 20 && drone.isAvailable()) {
                chargingStation.chargeDrone(drone);
            }
        }
    }

    // ---------------- Print All Drones ----------------
    public void showFleetStatus() {
        System.out.println("\n------ DRONE FLEET STATUS ------");
        for (Drone drone : droneFleet) {
            System.out.println(drone);
        }
    }

    public Location getLocation() {
        return dispatcherLocation;
    }

    public void removeDrone(int index) {
        System.out.println("[Dispatcher] Drone Removed: " + droneFleet[index].getDroneID());
        droneFleet[index] = null;

    }


}
