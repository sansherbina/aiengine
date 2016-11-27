package com.aiengine.engine;


import com.aiengine.world.Action;
import com.aiengine.world.ActionResult;

public class ActionHistory {
    private Action action;
    private int position;
    private ActionResult actionResult;
    private double contentmentLevelChange;

    public ActionHistory(Action action, int position, ActionResult actionResult, double contentmentLevelChange) {
        this.action = action;
        this.position = position;
        this.actionResult = actionResult;
        this.contentmentLevelChange = contentmentLevelChange;
    }

    public boolean isIncreasedContentment() {
        return contentmentLevelChange > 0;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public void setActionResult(ActionResult actionResult) {
        this.actionResult = actionResult;
    }

    public double getContentmentLevelChange() {
        return contentmentLevelChange;
    }

    public void setContentmentLevelChange(double contentmentLevelChange) {
        this.contentmentLevelChange = contentmentLevelChange;
    }

    public String toString() {
        return String.format("Action %s at position %d with result %s contentment change %f", action, position, actionResult, contentmentLevelChange);
    }
}
