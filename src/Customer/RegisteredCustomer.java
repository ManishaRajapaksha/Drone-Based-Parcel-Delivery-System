//package Customer;
//import Common.Location;
//
//public class RegisteredCustomer extends Customer {
//
//    private int loyaltyPoints;
//
//    public RegisteredCustomer(String customerId, String name, String address, String contactNumber,
//                              Location location) {
//        super(name, address, contactNumber, location);
//        this.loyaltyPoints = 0;
//    }
//
//    public void addPoints(int points) {
//        this.loyaltyPoints += points;
//    }
//
//    public int getLoyaltyPoints() {
//        return loyaltyPoints;
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() + " | Loyalty: " + loyaltyPoints;
//    }
//}
