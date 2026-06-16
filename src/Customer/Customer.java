package Customer;

import Delivery.Parcel;
import Common.Location;

public class Customer {

    private final String customerId;
    private String name;
    private String address;
    private String contactNumber;
    private Location location;

    private static int CustomerCount;

    public Customer(String name, String address, String contactNumber, Location location) {
        this.customerId = "C" + CustomerCount++;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.location = location;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void updateAddress(String newAddress) {
        this.address = newAddress;
    }
    public void updateContact(String newContact) {
        this.contactNumber = newContact;
    }
    public void updateLocation(Location newLocation) {
        this.location = newLocation;
    }


    public DeliveryRequest requestDelivery(Parcel parcel) {
        return new DeliveryRequest(parcel, this);
    }

    public String toString() {
        return "Customer ID: " + customerId +
                "\nName: " + name +
                "\nAddress: " + address +
                "\nContact: " + contactNumber +
                "\nLocation: " + location;
    }
}
