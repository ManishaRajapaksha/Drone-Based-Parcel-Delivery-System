package Dispatch;

public class WeatherSystem {
    private String currentWeather;

    public WeatherSystem() {
        currentWeather = "Sunny";
    }

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
