package org.solman.entity;

public abstract class Player {

    private final String NAME;
    private boolean hasWon = false;
    private final int MOVEMENT_SPEED;
    private Node position;

    public Player(Node start, String NAME, int MOVEMENT_SPEED) {
        this.position = start;
        this.NAME = NAME;
        this.MOVEMENT_SPEED = MOVEMENT_SPEED;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public Node getPlayerPosition() {
        return position;
    }

    public void setPosition(Node position) {
        this.position = position;
    }

    public String getName() {
        return NAME;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public int getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }


}
