package org.solman.entity;

import org.solman.entity.playerinterface.PlayerInterface;

import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class HumanPlayer extends Player implements PlayerInterface {

    private double dx = 0;
    private double dy = 0;
    private boolean[][] obstacles;

    public HumanPlayer(Node startPos, String NAME, int MOVEMENT_SPEED) {
        super(startPos, NAME, MOVEMENT_SPEED);
    }

    @Override
    public boolean hasWon(Node target) {
        return target.getPosY() == getPlayerPosition().getPosY() && target.getPosX() == getPlayerPosition().getPosX();
    }

    @Override
    public void setNewLocation() {
        double toBeRounded;
        double rounded;
        if (isValidInputX()) {
            if (dx > 0) {
                if (newPosXIsPassable((getPlayerPosition().getPosX() + 0.2) + 1)) {
                    toBeRounded = getPlayerPosition().getPosX() + 0.2;
                    rounded = new BigDecimal(toBeRounded).setScale(1, RoundingMode.HALF_UP).doubleValue();
                    getPlayerPosition().setPosX(rounded);
                } else {
                    getPlayerPosition().setPosX(Math.floor(getPlayerPosition().getPosX() + .99));
                }
            } else if (dx < 0) {
                if (newPosXIsPassable(getPlayerPosition().getPosX() - 0.2)) {
                    toBeRounded = getPlayerPosition().getPosX() -0.2;
                    rounded = new BigDecimal(toBeRounded).setScale(1, RoundingMode.HALF_UP).doubleValue();
                    getPlayerPosition().setPosX(rounded);
                } else {
                    getPlayerPosition().setPosX(Math.floor(getPlayerPosition().getPosX()));
                }
            }
        }
        else {
            checkBoundries();
        }
        if (isValidInputY()){
            if (dy > 0) {
                if (newPosYIsPassable((getPlayerPosition().getPosY() + 0.2) + 1)) {
                    toBeRounded = getPlayerPosition().getPosY() + 0.2;
                    rounded = new BigDecimal(toBeRounded).setScale(1, RoundingMode.HALF_UP).doubleValue();
                    getPlayerPosition().setPosY(rounded);
                } else {
                    getPlayerPosition().setPosY(Math.floor(getPlayerPosition().getPosY() + .99));
                }
            } else if (dy < 0) {
                double currentY = getPlayerPosition().getPosY();
                double newY = .2;
                double newVal = currentY - newY;
                System.out.println("Y: " + currentY);
                if (newPosYIsPassable(newVal)) {
                    toBeRounded = getPlayerPosition().getPosY() - 0.2;
                    rounded = new BigDecimal(toBeRounded).setScale(1, RoundingMode.HALF_UP).doubleValue();
                    getPlayerPosition().setPosY(rounded);
                } else {
                    getPlayerPosition().setPosY(Math.floor(getPlayerPosition().getPosY()));
                }
            }
        }
        else {
            checkBoundries();
        }
    }

    private boolean isValidInputY() {
        if ((getPlayerPosition().getPosY() + dy) * 10 >= obstacles.length - 10){
            return false;
        }
        return !(getPlayerPosition().getPosY() + dy < 0);
    }

    private boolean isValidInputX() {
        if(getPlayerPosition().getPosX() + dx < 0){
            return false;
        }
        return !((getPlayerPosition().getPosX() + dx) * 10 >= obstacles[0].length - 10);
    }

    private void checkBoundries() {
        if(getPlayerPosition().getPosX() - 0.2 < 0){
            getPlayerPosition().setPosX(0);
        }
        if ((getPlayerPosition().getPosX() + 0.2) * 10 >= obstacles[0].length - 10){
            getPlayerPosition().setPosX((obstacles[0].length - 10) / 10);
        }
        if (getPlayerPosition().getPosY() - 0.2 < 0){
            getPlayerPosition().setPosY(0);
        }
        if ((getPlayerPosition().getPosY() + 0.2) * 10 >= obstacles.length - 10){
            getPlayerPosition().setPosY((obstacles.length - 10) / 10);
        }
    }

    private boolean newPosXIsPassable(double newPos){
        double y = getPlayerPosition().getPosY() * 10;
        double x = newPos * 10;
        int posY = (int) y;
        int posX = (int) x;
        for (int i = posY; i < posY + 10; i++){
            boolean isTrue = obstacles[i][posX];
            if (!isTrue){
                return false;
            }
        }
        return true;
    }

    public boolean newPosYIsPassable(double newPos){
        double y = (newPos * 10);
        double x = (getPlayerPosition().getPosX() * 10);
        int posY = (int) y;
        int posX = (int) x;
        for (int i = posX; i < posX + 10; i++){
            boolean isTrue = obstacles[posY][i];
            if (!isTrue){
                return false;
            }
        }
        return true;
    }

    public void createObstaclesList(List<List<Node>> nodes){
        obstacles = new boolean[nodes.size() * 10][nodes.get(0).size() * 10];
        int x = nodes.get(0).size();
        for (List<Node> node : nodes) {
            for (int j = 0; j < x; j++) {
                if (node.get(j).getIS_PASSABLE()) {
                    addValuesToArray(node.get(j));
                }
            }
        }
    }

    private void addValuesToArray(Node node) {
        int offset = 10;
        for (int i = (int) node.getPosY() * 10; i < (node.getPosY() * 10) + offset; i++){
            for (int j = (int) node.getPosX() * 10; j < (node.getPosX() * 10) + offset; j++){
                obstacles[i][j] = node.getIS_PASSABLE();
            }
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -0.2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0.2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -0.2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = .2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
