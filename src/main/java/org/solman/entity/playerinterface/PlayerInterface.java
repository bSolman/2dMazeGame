package org.solman.entity.playerinterface;

import org.solman.entity.Node;

import java.util.List;

public interface PlayerInterface {
    boolean hasWon(Node target);
    String getName();
    void setNewLocation();
}
