package org.solman.logic.controllers;

import org.solman.entity.ComputerPlayer;
import org.solman.entity.Node;
import org.solman.entity.Player;
import org.solman.logic.logicInterface.PlayerControllerInterface;

import java.awt.event.KeyEvent;
import java.util.List;


public class ComputerController implements PlayerControllerInterface {
    private ComputerPlayer cpu;

    public ComputerController() {
    }

    public List<Node> getRoadMap() {
        return cpu.getCPU_ROAD_MAP();
    }

    @Override
    public void updatePlayerPos() {
        cpu.setNewLocation();
    }

    public void createPlayer(Node start, String name, int speed, List<Node> roadMap) {
        cpu = new ComputerPlayer(start, name, speed, roadMap);
    }

    @Override
    public Node getPlayerPos() {
        return cpu.getPlayerPosition();
    }

    @Override
    public void updateMove(KeyEvent e, boolean isPressed) {

    }

    @Override
    public Player getPlayer() {
        return cpu;
    }

}
