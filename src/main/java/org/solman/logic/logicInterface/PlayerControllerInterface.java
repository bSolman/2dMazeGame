package org.solman.logic.logicInterface;

import org.solman.entity.Node;
import org.solman.entity.Player;

import java.awt.event.KeyEvent;
import java.util.List;

public interface PlayerControllerInterface {

    public void updatePlayerPos();

    public Node getPlayerPos();

    public void updateMove(KeyEvent e, boolean isPressed);

    public Player getPlayer();

    public List<Node> getRoadMap();
}
