package Dispatch;

public class WeatherSystem {
    // Stores the current weather condition
    private String currentWeather;

    // Constructor - sets default weather to Sunny
    public WeatherSystem() {
        currentWeather = "Sunny";
    }

    // Randomly updates the weather condition
    public void updateWeather() {
        String[] weatherTypes = {"Sunny", "Rainy", "Windy"};
        int index = (int)(Math.random() * weatherTypes.length);
        currentWeather = weatherTypes[index];
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public boolean isSafeToFly() {
        return !currentWeather.equals("Windy");
    }
}
