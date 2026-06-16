package Customer;

import Delivery.Parcel;
import Common.Location;
import java.lang.reflect.AccessFlag;
import java.time.LocalDateTime;

public class DeliveryRequest {
    private static int nextId = 1;

    private String requestId;
    private Customer customer;
    public Parcel parcel;
    private Location pickupLocation;
    private Location deliveryLocation;
    private LocalDateTime requestTime;
    private String status;
    private int ParcelCount;

    public DeliveryRequest(Parcel parcel, Customer customer, Location deliveryLocation1) {
        this.requestId = "DR" + nextId++;
        this.customer = customer;
        this.parcel = parcel;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.requestTime = LocalDateTime.now();
        this.status = "Pending";
    }


    public String getRequestId() {
        return requestId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public String getStatus() {
        return status;
    }
    public Location getPickupLocation() {
        return pickupLocation;
    }
    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public String toString() {
        return "Request " + requestId + " | " + customer.getName() + " | " + status;
    }
}
