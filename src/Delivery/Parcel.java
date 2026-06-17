package Delivery;

public class Parcel {


    public enum Status {
        PENDING,
        //IN_TRANSIT,
        DELIVERED
    }

    private String parcelId;
    private double weight;
    private String destinationLocation;
    private Status status;
    private static int ParcelCount;

    public Parcel(double weight, String destinationLocation) {
        this.parcelId = "P" + ParcelCount++;
        this.weight = weight;
        this.destinationLocation = destinationLocation;
        this.status = Status.PENDING; // Default status
    }

    public Parcel(String parcelId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public Parcel(double weight) {
        this.weight = weight;
    }

    public String getParcelId() {
        return parcelId;
    }
    public double getWeight() {
        return weight;
    }
    public String getDestinationLocation() {
        return destinationLocation;
    }
    public Status getStatus() {
        return status;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
    public void setStatus(Status status) {
        this.status = status;
    }


    public void updateParcelStatus(Status newStatus) {
        this.status = newStatus;
        System.out.println("Parcel " + parcelId + " status updated to: " + status);
    }

//    public void displayParcelDetails() {
//        System.out.println("Parcel ID: " + parcelId);
//        System.out.println("Weight: " + weight + " kg");
//        System.out.println("Destination: " + destinationLocation);
//        System.out.println("Status: " + status);
//    }
}
