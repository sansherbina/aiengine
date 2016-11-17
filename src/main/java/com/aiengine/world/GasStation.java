package com.aiengine.world;

public class GasStation {
    public int INITIAL_FUEL_VOLUME = 4;
    private int fuelVolume;

    public GasStation() {
        this.fuelVolume = INITIAL_FUEL_VOLUME;
    }

    public int giveFuel(int fuelSpeed){
        if(fuelVolume>=fuelSpeed){
            fuelVolume-=fuelSpeed;
            return fuelSpeed;
        }
        int restFueldVolume = fuelVolume;
        fuelVolume = 0;
        return restFueldVolume;
    }

    public boolean isEmpty(){
        return fuelVolume == 0;
    }
}
