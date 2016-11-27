package com.aiengine.world;

public class Car {
    public static final int MAX_FUEL_VOLUME = 50;
    public static final int FUEL_LOAD_SPEED = 2;
    private int fuelLevel;

    public Car() {
    }

    public void descreaseFuel() {
        if (fuelLevel > 0) {
            fuelLevel--;
        }
    }

    public void loadFuel(int loadedFuelVolume) {
        fuelLevel += loadedFuelVolume;
        if (fuelLevel > MAX_FUEL_VOLUME) {
            fuelLevel = MAX_FUEL_VOLUME;
        }
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

}
