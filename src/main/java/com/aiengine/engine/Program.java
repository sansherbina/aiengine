package com.aiengine.engine;

import com.aiengine.world.Action;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final double INITIAL_PROGRAM_EFFECTIVNESS = 0.5;
    private int position;
    private List<Action> actions;
    private int succesfullExecutionsCount;
    private int executionsCount;

    public Program(int position) {
        this.position = position;
        this.actions = new ArrayList<Action>();
    }

    public Action getAction(int number) {
        if (actions.size() <= number) {
            return null;
        }
        return actions.get(number);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public double getProgramEffectivnes() {
        if (executionsCount == 0) {
            return INITIAL_PROGRAM_EFFECTIVNESS;
        }
        return ((double) succesfullExecutionsCount) / executionsCount;
    }

    public void increaseSuccesfullExecutionsCount() {
        succesfullExecutionsCount++;
    }

    public void increaseExecutionsCount() {
        executionsCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        if (position != program.position) return false;
        if (actions.size() != program.actions.size()) {
            return false;
        }
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) != program.actions.get(i)) {
                return false;
            }
        }
        return actions.equals(program.actions);

    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + actions.hashCode();
        return result;
    }
}
