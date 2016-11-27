package com.aiengine.world;

public interface WorldInterface {
    double getContentment();

    ActionResult doAction(Action action);

    int getCurrentPosition();

    int getPositionsQuantity();

}
