package com.aiengine;

import com.aiengine.engine.Engine;
import com.aiengine.world.Action;
import com.aiengine.world.ActionResult;
import com.aiengine.world.Car;
import com.aiengine.world.Environment;

public class LeaveExecutor {
    private static final Action[] ACTION_CYCLE = new Action[] {
            Action.MOVE_RIGHT,
            Action.MOVE_RIGHT,
            Action.CHECK_GAS_STATION,
            Action.LOAD_FUEL,
            Action.LOAD_FUEL,
            Action.MOVE_RIGHT,
            Action.MOVE_RIGHT,
            Action.MOVE_RIGHT,
            Action.CHECK_GAS_STATION,
            Action.LOAD_FUEL,
            Action.LOAD_FUEL,
            Action.MOVE_LEFT,
            Action.MOVE_LEFT,
            Action.MOVE_LEFT,
            Action.CHECK_GAS_STATION,
            Action.LOAD_FUEL,
            Action.LOAD_FUEL,
            Action.MOVE_LEFT,
            Action.MOVE_LEFT,
            Action.MOVE_LEFT
    };

    public static void main(String[] args){
        Car car = new Car();
        Environment environment = new Environment(car);
        Engine engine = new Engine(environment);
        for(int i=0;i<100000;i++){
            if(i>10000){
                i=i;
            }
            engine.doAction();
        }

        System.out.println("Contentment increase actions count "+engine.getContentmentIncreaseActionCount());
    }

    private void executeActionsFromList(){
        Car car = new Car();
        Environment environment = new Environment(car);
        for(Action currentAction:ACTION_CYCLE){
            ActionResult actionResult = environment.doAction(currentAction);
            System.out.println(String.format("Action %s result %s", currentAction, actionResult));
        }
    }

}
