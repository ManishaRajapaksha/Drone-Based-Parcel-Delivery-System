import Customer.Customer;
import Customer.CustomerManager;

import Customer.DeliveryRequest;
import Delivery.Parcel;

import Dispatch.Dispatcher;

import Drone.Drone;
import Drone.HeavyDrone;
import Drone.LightDrone;

import Common.Location;
import Dispatch.WeatherSystem;
import Dispatch.ChargingStation;

import java.util.ArrayList;
import java.util.Scanner;

public class CmdTest {

    public static void main() {

        Scanner sc = new Scanner(System.in);

        CustomerManager customerManager = new CustomerManager();
        WeatherSystem weatherSystem = new WeatherSystem();
        ChargingStation chargingStation = new ChargingStation(3);
        Location dispatcherLoc = new Location(6.9271, 79.8612); // Colombo
        Dispatcher dispatcher = new Dispatcher(weatherSystem, chargingStation, dispatcherLoc, 5);

        // Register drones
        dispatcher.registerDrone(new HeavyDrone(1, 30, 10, dispatcherLoc), 0);
        dispatcher.registerDrone(new HeavyDrone(2, 35, 12, dispatcherLoc), 1);
        dispatcher.registerDrone(new LightDrone(3, 20, 15, dispatcherLoc), 2);
        dispatcher.registerDrone(new LightDrone(4, 25, 18, dispatcherLoc), 3);
        dispatcher.registerDrone(new LightDrone(5, 15, 12, dispatcherLoc), 4);

        ArrayList<DeliveryRequest> requests = new ArrayList<>();

        while (true) {
            System.out.println("\n===== DRONE DELIVERY SYSTEM =====");
            System.out.println("1. Register Customer");
            System.out.println("2. Create Parcel & Delivery Request");
            System.out.println("3. Show Delivery Requests");
            System.out.println("4. Assign Drone to Request");
            System.out.println("5. Start Delivery");
            System.out.println("6. Delete Delivery Request");
            System.out.println("7. Show Drone Fleet");
            System.out.println("8. Remove Drone");
            System.out.println("9. Update Weather");
            System.out.println("10. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: { // Register customer
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter address: ");
                    String addr = sc.nextLine();

                    System.out.print("Enter contact: ");
                    String contact = sc.nextLine();

                    System.out.print("Enter latitude: ");
                    double lat = sc.nextDouble();

                    System.out.print("Enter longitude: ");
                    double lon = sc.nextDouble();
                    sc.nextLine();

                    Customer c = new Customer(name, addr, contact, new Location(lat, lon));
                    customerManager.registerCustomer(c);
                    System.out.println("Customer Registered: " + c);
                    break;
                }

                case 2: { // Create parcel & request
                    System.out.print("Enter Customer ID: ");
                    String cid = sc.nextLine();
                    Customer cust = customerManager.getCustomer(cid);
                    if (cust == null) {
                        System.out.println("Customer not found!");
                        break;
                    }

                    System.out.print("Parcel weight: ");
                    double w = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Delivery latitude: ");
                    double lat = sc.nextDouble();
                    System.out.print("Delivery longitude: ");
                    double lon = sc.nextDouble();
                    sc.nextLine();

                    Parcel p = new Parcel(w, "Destination");
                    DeliveryRequest req = cust.requestDelivery(p);
                    requests.add(req);

                    System.out.println("Delivery Request Created: " + req.getRequestId());
                    break;
                }

                case 3: { // Show requests
                    if (requests.isEmpty()) {
                        System.out.println("No requests yet.");
                        break;
                    }
                    System.out.println("\n--- DELIVERY REQUESTS ---");
                    for (DeliveryRequest r : requests) {
                        String custName = r.getCustomer() != null ? r.getCustomer().getName() : "Unknown";
                        System.out.println(r.getRequestId() + " | Customer: " + custName +
                                " | Parcel Weight: " + r.parcel.getWeight() +
                                " | Status: " + r.getStatus());
                    }
                    break;
                }

                case 4: { // Assign drone
                    System.out.print("Enter Delivery Request ID: ");
                    String rid = sc.nextLine();

                    DeliveryRequest req = null;
                    for (DeliveryRequest r : requests) {
                        if (r.getRequestId().equals(rid)) {
                            req = r;
                            break;
                        }
                    }
                    if (req == null) {
                        System.out.println("Request not found!");
                        break;
                    }

                    Drone assigned = dispatcher.assignDrone(req, req.parcel.getWeight());
                    if (assigned != null) {
                        req.updateStatus("Drone Assigned: " + assigned.getDroneID());
                        System.out.println("Drone " + assigned.getDroneID() + " assigned to " + rid);
                    }
                    break;
                }

                case 5: { // Start delivery
                    System.out.print("Enter Delivery Request ID: ");
                    String rid = sc.nextLine();

                    DeliveryRequest req = null;
                    for (DeliveryRequest r : requests) {
                        if (r.getRequestId().equals(rid)) {
                            req = r;
                            break;
                        }
                    }
                    if (req == null) {
                        System.out.println("Request not found!");
                        break;
                    }

                    Drone drone = dispatcher.assignDrone(req, req.parcel.getWeight());
                    if (drone != null) {
                        drone.takeOff();
                        drone.deliverParcel(req.getDeliveryLocation().toString());
                        drone.returnToBase();
                        req.updateStatus("Delivered");
                        req.parcel.updateParcelStatus(Parcel.Status.DELIVERED);
                    } else {
                        System.out.println("No available drone for delivery!");
                    }
                    break;
                }

                case 6: { // Delete request
                    System.out.print("Enter Delivery Request ID to delete: ");
                    String rid = sc.nextLine();
                    requests.removeIf(r -> r.getRequestId().equals(rid));
                    System.out.println("Deleted request " + rid);
                    break;
                }

                case 7: // Show fleet
                    System.out.println("\n--- DRONE FLEET STATUS ---");
                    for (int i = 0; i < 5; i++) {
                        Drone d = dispatcher.getDrone(i);
                        if (d != null) {
                            System.out.println(d.getDroneID() + " | Type: " + d.getClass().getSimpleName() +
                                    " | Status: " + d.getStatus() + " | Battery: " + d.getBatteryLevel() + "%");
                        }
                    }
                    break;

                case 8: { // Remove drone
                    System.out.print("Enter Drone index (0-4) to remove: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    Drone d = dispatcher.getDrone(idx);
                    if (d != null) {
                        dispatcher.removeDrone(idx);
                    } else {
                        System.out.println("No drone found at this index.");
                    }
                    break;
                }

                case 9:
                    weatherSystem.updateWeather();
                    System.out.println("Weather now: " + weatherSystem.getCurrentWeather());
                    break;

                case 10:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
