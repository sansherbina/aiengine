package com.aiengine.engine;

import java.util.*;

public class ProgramManager {
    private Map<Integer, Set<Program>> programs;

    public ProgramManager(int positionsQuantity){
        this.programs = new HashMap<Integer, Set<Program>>();
        for(int i=0;i<positionsQuantity;i++){
            programs.put(i, new HashSet<>());
        }
    }

    public void addProgram(int position, Program program){
        programs.get(position).add(program);
    }

    public Program getProgram(int position){
        if(programs.get(position).isEmpty()){
            return null;
        }
        Program programWithMaxEffectivness = null;
        double maxProgramEffectivnes = 0;
        for(Program program:programs.get(position)){
            if(maxProgramEffectivnes<program.getProgramEffectivnes()){
                maxProgramEffectivnes = program.getProgramEffectivnes();
                programWithMaxEffectivness = program;
            }
        }
        return programWithMaxEffectivness;
    }

    protected Map<Integer, Set<Program>> getPrograms() {
        return programs;
    }
}
