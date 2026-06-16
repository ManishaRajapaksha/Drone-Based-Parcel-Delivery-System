package Common;

public class Location {

    private final double latitude;
    private final double longitude;
    private String city;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String city) {
        if ("Colombo".equals(city)) {
            this.latitude = 6.9271;
            this.longitude = 79.8612;
        } else if ("Kandy".equals(city)) {
            this.latitude = 7.2906;
            this.longitude = 80.6337;
        } else if ("Galle".equals(city)) {
            this.latitude = 6.0535;
            this.longitude = 80.2210;
        } else if ("Jaffna".equals(city)) {
            this.latitude = 9.6615;
            this.longitude = 80.0255;
        } else if ("Matara".equals(city)) {
            this.latitude = 5.9549;
            this.longitude = 80.5540;
        } else if ("Negombo".equals(city)) {
            this.latitude = 7.2008;
            this.longitude = 79.8737;
        } else if ("Kurunegala".equals(city)) {
            this.latitude = 7.4818;
            this.longitude = 80.3609;
        } else if ("Anuradhapura".equals(city)) {
            this.latitude = 8.3114;
            this.longitude = 80.3609;
        } else {
            this.latitude = 0.0;
            this.longitude = 0.0;
        }
        this.city = city;
    }

    @Override
    public String toString() {
        return "Lat: " + latitude + ", Lon: " + longitude + (city != null ? " (" + city + ")" : "");
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
}
