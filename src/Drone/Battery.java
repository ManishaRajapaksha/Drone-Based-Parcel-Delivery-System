package Drone;


enum CellType {
    S3(3),  // 3 cells → 11.1V
    S4(4),  // 4 cells → 14.8V
    S5(5),  // 5 cells → 18.5V
    S6(6);  // 6 cells → 22.2V

    private final int cellCount;

    CellType(int count) {
        this.cellCount = count;
    }

    public int getCellCount() {
        return cellCount;
    }

    public double getNominalVoltage() {
        return cellCount * 3.7;
    }

    public double getMaxVoltage() {
        return cellCount * 4.2;
    }
}


public class Battery {

    private int chargeLevel; // 0–100%
    private CellType cellType;
    private int capacity;
    private double maxVoltage;
    private int maxmAh;

    public Battery(CellType cellType, int capacity, int initialChargePercent) {

        this.cellType = cellType;
        this.capacity = capacity;

        this.maxVoltage = cellType.getMaxVoltage();
        this.maxmAh = capacity;

        if (initialChargePercent < 0) initialChargePercent = 0;
        if (initialChargePercent > 100) initialChargePercent = 100;

        this.chargeLevel = initialChargePercent;
    }

    public Battery() {
    }

    // ---------------- GETTERS ----------------

    public int getChargeLevel() {
        return chargeLevel;
    }

    public double getCurrentVoltage() {
        return (chargeLevel / 100.0) * maxVoltage;
    }

    public int getRemainingmAh() {
        return (int)((chargeLevel / 100.0) * maxmAh);
    }

    public CellType getCellType() {
        return cellType;
    }

    public int getCapacity() {
        return capacity;
    }

    // ---------------- OPERATIONS ----------------

    public void drain(int amount) {
        chargeLevel -= amount;
        if (chargeLevel < 0) chargeLevel = 0;

        System.out.println("Battery drained. Current level: " + chargeLevel + "%");
    }

    public void recharge() {
        chargeLevel = 100;
        System.out.println("Battery fully recharged.");
    }

    public boolean isLow() {
        return chargeLevel < 20;
    }

    // ---------------- EXTRA INFO ----------------

    public String getBatteryInfo() {
        return "Battery Info:\n" +
                "Type: " + cellType + " (" + maxVoltage + " V)\n" +
                "Capacity: " + capacity + " mAh\n" +
                "Charge Level: " + chargeLevel + "%\n" +
                "Current Voltage: " + String.format("%.2f", getCurrentVoltage()) + " V\n" +
                "Remaining mAh: " + getRemainingmAh() + " mAh\n";
    }
}
