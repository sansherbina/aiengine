package com.aiengine.world;

public class Car {
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
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

}
