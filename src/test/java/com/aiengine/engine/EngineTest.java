package com.aiengine.engine;

import com.aiengine.world.Action;
import com.aiengine.world.ActionResult;
import com.aiengine.world.Car;
import com.aiengine.world.Environment;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EngineTest {
    private Engine engine;
    private Car car;
    private Environment environment;

    @Before
    public void init() {
        car = new Car();
        environment = new Environment(car);
        engine = new Engine(environment);
    }

    @Test
    public void createProgramsTest_shortActionList() {
        List<ActionHistory> actions = new ArrayList<>();
        ActionHistory history1 = new ActionHistory(Action.CHECK_GAS_STATION, 0, ActionResult.SUCCESSFUL, 0.1);
        ActionHistory history2 = new ActionHistory(Action.MOVE_LEFT, 1, ActionResult.SUCCESSFUL, 0.1);
        actions.add(history1);
        actions.add(history2);
        engine.setActionsHistory(actions);
        for (int i = 0; i < environment.getPositionsQuantity(); i++) {
            assertThat(engine.getProgramManager().getProgram(i), is(nullValue()));
        }
    }

    @Test
    public void createProgramsTest_firstProgram() {
        // Given
        List<ActionHistory> actions = new ArrayList<>();
        ActionHistory history1 = new ActionHistory(Action.MOVE_RIGHT, 0, ActionResult.SUCCESSFUL, 0);
        ActionHistory history2 = new ActionHistory(Action.MOVE_RIGHT, 1, ActionResult.SUCCESSFUL, 0);
        ActionHistory history3 = new ActionHistory(Action.LOAD_FUEL, 2, ActionResult.SUCCESSFUL, 0.1);
        ActionHistory history4 = new ActionHistory(Action.LOAD_FUEL, 3, ActionResult.SUCCESSFUL, 0.1);
        ActionHistory history5 = new ActionHistory(Action.MOVE_RIGHT, 4, ActionResult.SUCCESSFUL, 0);
        actions.add(history1);
        actions.add(history2);
        actions.add(history3);
        actions.add(history4);
        actions.add(history5);
        engine.setActionsHistory(actions);
        // When
        engine.createPrograms();

        // Then
        Program program0 = engine.getProgramManager().getPrograms().get(0).iterator().next();
        assertThat(program0, IsNot.not(Null.NULL));
        assertThat(program0.getAction(0), is(Action.MOVE_RIGHT));
        assertThat(program0.getAction(1), is(Action.MOVE_RIGHT));
        assertThat(program0.getAction(2), is(Action.LOAD_FUEL));

        Program program1 = engine.getProgramManager().getPrograms().get(1).iterator().next();
        assertThat(program1, IsNot.not(Null.NULL));
        assertThat(program1.getAction(0), is(Action.MOVE_RIGHT));
        assertThat(program1.getAction(1), is(Action.LOAD_FUEL));

        Program program2 = engine.getProgramManager().getPrograms().get(2).iterator().next();
        assertThat(program2, IsNot.not(Null.NULL));
        assertThat(program2.getAction(0), is(Action.LOAD_FUEL));
    }

    @Test
    public void createProgramsTest_programAfterProgram() {
        // Given
        List<ActionHistory> actions = new ArrayList<>();
        ActionHistory history0 = new ActionHistory(Action.LOAD_FUEL, 0, ActionResult.SUCCESSFUL, 0.1);
        ActionHistory history1 = new ActionHistory(Action.MOVE_RIGHT, 0, ActionResult.SUCCESSFUL, 0);
        ActionHistory history2 = new ActionHistory(Action.MOVE_RIGHT, 1, ActionResult.SUCCESSFUL, 0);
        ActionHistory history3 = new ActionHistory(Action.LOAD_FUEL, 2, ActionResult.SUCCESSFUL, 0.1);
        ActionHistory history4 = new ActionHistory(Action.LOAD_FUEL, 3, ActionResult.SUCCESSFUL, 0.1);
        ActionHistory history5 = new ActionHistory(Action.MOVE_RIGHT, 4, ActionResult.SUCCESSFUL, 0);
        actions.add(history0);
        actions.add(history1);
        actions.add(history2);
        actions.add(history3);
        actions.add(history4);
        actions.add(history5);
        engine.setActionsHistory(actions);
        // When
        engine.createPrograms();

        // Then
        Program program0 = engine.getProgramManager().getPrograms().get(0).iterator().next();
        assertThat(program0, IsNot.not(Null.NULL));
        assertThat(program0.getAction(0), is(Action.MOVE_RIGHT));
        assertThat(program0.getAction(1), is(Action.MOVE_RIGHT));
        assertThat(program0.getAction(2), is(Action.LOAD_FUEL));

        Program program1 = engine.getProgramManager().getPrograms().get(1).iterator().next();
        assertThat(program1, IsNot.not(Null.NULL));
        assertThat(program1.getAction(0), is(Action.MOVE_RIGHT));
        assertThat(program1.getAction(1), is(Action.LOAD_FUEL));

        Program program2 = engine.getProgramManager().getPrograms().get(2).iterator().next();
        assertThat(program2, IsNot.not(Null.NULL));
        assertThat(program2.getAction(0), is(Action.LOAD_FUEL));
    }
}
