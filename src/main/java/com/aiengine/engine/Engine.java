package com.aiengine.engine;


import com.aiengine.world.Action;
import com.aiengine.world.ActionResult;
import com.aiengine.world.WorldInterface;

import java.util.*;

public class Engine {
    private WorldInterface world;
    private List<ActionHistory> actionsHistory;
    private ProgramManager programManager;
    private Program currentlyExecutedProgram;
    private int actionNumberInCurrentlyExecutedProgram;
    private int contentmentIncreaseActionCount;

    public Engine(WorldInterface world) {
        this.world = world;
        this.actionsHistory = new ArrayList<ActionHistory>();
        this.programManager = new ProgramManager(world.getPositionsQuantity());
    }

    public void doAction() {
        Action action = findAction();
        int position = world.getCurrentPosition();
        double prevContentmentLevel = world.getContentment();

        ActionResult actionResult = world.doAction(action);
        double currentContentmentLevel = world.getContentment();

        ActionHistory actionHistory = new ActionHistory(action, position, actionResult, currentContentmentLevel - prevContentmentLevel);
        actionsHistory.add(actionHistory);
        if (currentContentmentLevel > prevContentmentLevel) {
            contentmentIncreaseActionCount++;
            System.out.println(actionsHistory.size() + " " + actionHistory.toString());
        }
    }

    protected Action findAction() {
        if (isPrevActionIncreasedContentment()) {
            return getPrevAction().getAction();
        }

        createPrograms();

        Program programForCurrentPosition = programManager.getProgram(world.getCurrentPosition());
        changeCurrentProgram(programForCurrentPosition);

        if(currentlyExecutedProgram!=null){
            Action action = currentlyExecutedProgram.getAction(actionNumberInCurrentlyExecutedProgram);
            if(action!=null){
                actionNumberInCurrentlyExecutedProgram++;
                return action;
            }
        }

        int randomAction = (int) (Math.random() * Action.values().length);
        return Action.values()[randomAction];
    }

    protected void changeCurrentProgram(Program program){
        if(program!=null &&
                (currentlyExecutedProgram == null || program.getProgramEffectivnes()>currentlyExecutedProgram.getProgramEffectivnes())){
            if(currentlyExecutedProgram!=null) {
                currentlyExecutedProgram.increaseSuccesfullExecutionsCount();
            }
            currentlyExecutedProgram = program;
            actionNumberInCurrentlyExecutedProgram = 0;
            program.increaseExecutionsCount();
        }
    }

    protected void createPrograms(){
        if(actionsHistory.size()<3){
            return;
        }
        ActionHistory currentAction = actionsHistory.get(actionsHistory.size()-1);
        ActionHistory prevAction = actionsHistory.get(actionsHistory.size()-2);
        if(!(!currentAction.isIncreasedContentment() &&
                prevAction.isIncreasedContentment())) {
            return;
        }
        int firstContentmentIncreaseAction = actionsHistory.size()-2;
        while (firstContentmentIncreaseAction>0){
            if(actionsHistory.get(firstContentmentIncreaseAction-1).isIncreasedContentment()) {
                firstContentmentIncreaseAction--;
            }else{
                break;
            }
        }

        int prevProgramEnd = 0;
        for(int i=firstContentmentIncreaseAction-1;i>=0;i--){
            if(actionsHistory.get(i).isIncreasedContentment()){
                prevProgramEnd = i;
                break;
            }
        }

        List<Action> actionsInProgram = new ArrayList<Action>();
        Set<Integer> positionsWhereProgramsAlreadyCreated = new HashSet<>();
        for(int i=firstContentmentIncreaseAction;i>=prevProgramEnd;i--){
            actionsInProgram.add(0, actionsHistory.get(i).getAction());
            int programStartPosition = actionsHistory.get(i).getPosition();
            if(!positionsWhereProgramsAlreadyCreated.contains(programStartPosition)) {
                final Program program = new Program(programStartPosition);
                actionsInProgram.stream().forEach(e -> program.addAction(e));
                programManager.addProgram(programStartPosition, program);
                positionsWhereProgramsAlreadyCreated.add(programStartPosition);
            }
        }
    }

    private ActionHistory getPrevAction() {
        if (!actionsHistory.isEmpty()) {
            return actionsHistory.get(actionsHistory.size() - 1);
        } else {
            return null;
        }
    }

    private boolean isPrevActionIncreasedContentment() {
        ActionHistory prevAction = getPrevAction();
        return prevAction != null && prevAction.isIncreasedContentment();
    }

    public int getContentmentIncreaseActionCount() {
        return contentmentIncreaseActionCount;
    }

    protected void setActionsHistory(List<ActionHistory> actionsHistory) {
        this.actionsHistory = actionsHistory;
    }

    protected ProgramManager getProgramManager() {
        return programManager;
    }
}
