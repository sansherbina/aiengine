package com.aiengine.world;

import java.util.HashMap;
import java.util.Map;

public class Environment implements WorldInterface{
    private int[] GAS_STATION_POSITIONS = new int[]{2, 5};
    private int ENVIRONMENT_LENGTH = 6;
    private int INITIAL_CAR_POSITION = 0;
    private int length;
    private Map<Integer, GasStation> gasStations;
    private int carPosition;
    private Car car;

    public Environment(Car car) {
        this.car = car;
        this.length = ENVIRONMENT_LENGTH;
        this.carPosition = INITIAL_CAR_POSITION;
        this.gasStations = new HashMap<Integer, GasStation>();
        recreateGasStation(-1);
    }

    public double getContentment() {
        return ((double)car.getFuelLevel())/Car.MAX_FUEL_VOLUME;
    }

    public ActionResult doAction(Action action) {
        car.descreaseFuel();
        boolean result;
        switch (action) {
            case MOVE_RIGHT:
                result = moveCarRight();
                break;
            case MOVE_LEFT:
                result = moveCarLeft();
                break;
            case CHECK_GAS_STATION:
                result = checkGasStation();
                break;
            case LOAD_FUEL:
                result = loadFuel();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsuported action %s", action));
        }
        return result?ActionResult.SUCCESSFUL:ActionResult.FAIL;
    }

    public int getCurrentPosition() {
        return carPosition;
    }

    public int getPositionsQuantity() {
        return length;
    }

    private boolean loadFuel() {
        if (!checkGasStation()) {
            return false;
        }
        GasStation gasStation = gasStations.get(carPosition);
        int fuelVolume = gasStation.giveFuel(Car.FUEL_LOAD_SPEED);
        car.loadFuel(fuelVolume);

        if (gasStation.isEmpty()) {
            recreateGasStation(carPosition);
        }
        return true;
    }

    private void recreateGasStation(int gasStationPosition) {
        gasStations.remove(gasStationPosition);

        int newGastStationPosition = -1;
        for(int position:GAS_STATION_POSITIONS){
            if(position!=gasStationPosition){
                newGastStationPosition = position;
                break;
            }
        }
        if(newGastStationPosition == -1){
            throw new IllegalStateException("Can't find place for gas station");
        }

        gasStations.put(newGastStationPosition, new GasStation());
    }

    private boolean checkGasStation() {
        return gasStations.containsKey(carPosition);
    }

    private boolean moveCarRight() {
        if (carPosition < length - 1) {
            carPosition++;
            return true;
        }
        return false;
    }

    private boolean moveCarLeft() {
        if (carPosition > 0) {
            carPosition--;
            return true;
        }
        return false;
    }

}
