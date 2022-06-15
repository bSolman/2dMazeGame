package org.solman.logic.controllers;

import org.solman.entity.HumanPlayer;
import org.solman.entity.Node;
import org.solman.entity.Player;
import org.solman.logic.logicInterface.PlayerControllerInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class PlayerController implements PlayerControllerInterface {
    private HumanPlayer humanPlayer;
    public PlayerController(Node startPos, String name, int speed){
        humanPlayer = new HumanPlayer(startPos, name, speed);
    }


    @Override
    public void updatePlayerPos() {
        humanPlayer.setNewLocation();
    }

    @Override
    public Node getPlayerPos() {
        return humanPlayer.getPlayerPosition();
    }

    @Override
    public void updateMove(KeyEvent e, boolean isPressed) {
        if (isPressed){
            humanPlayer.keyPressed(e);
        }
        else{
            humanPlayer.keyReleased(e);
        }
    }


    @Override
    public Player getPlayer() {
        return humanPlayer;
    }

    @Override
    public List<Node> getRoadMap() {
        return null;
    }
}
