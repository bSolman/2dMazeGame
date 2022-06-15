package org.solman.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.solman.logic.util.CreateMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(HumanPlayerResolver.class)
@DisplayName("Test HumanPlayer class")
class HumanPlayerTest {

    HumanPlayer hp = new HumanPlayer(new Node(2,4,true, 0), "Bosse", 2);
    CreateMap cp = new CreateMap(1);

    @ParameterizedTest
    @CsvFileSource(resources = "/movementInput.csv")
    @DisplayName("MovementTestX")
    void setNewLocationTestX(double expected){
        hp.setNewLocation();
        assertEquals(expected, hp.getPlayerPosition().getPosX());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/movementInput.csv")
    @DisplayName("MovementTestY")
    void setNewLocationTestY(double expected){
        hp.setNewLocation();
        assertEquals(expected, hp.getPlayerPosition().getPosY());
    }

    @Test
    void newPosYIsPassableTest() {
        hp.createObstaclesList(cp.getNodes());
        assertEquals(true, hp.newPosYIsPassable(0.2));

    }

    @Test
    void setNewLocation(){
        hp.setDy(-0.2);
        hp.getPlayerPosition().setPosX(9);
        hp.getPlayerPosition().setPosY(8);
        hp.createObstaclesList(cp.getNodes());
        hp.setNewLocation();
    }
}