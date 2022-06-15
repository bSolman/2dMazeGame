package org.solman.entity;

import org.solman.entity.playerinterface.PlayerInterface;

import java.util.List;

public class ComputerPlayer extends Player implements PlayerInterface  {

    private final List<Node> CPU_ROAD_MAP;
    private Node currentNode;
    private Node targetNode;

    public ComputerPlayer(Node start, String NAME, int MOVEMENT_SPEED, List<Node> cpu_roadmap) {
        super(start, NAME, MOVEMENT_SPEED);
        this.CPU_ROAD_MAP = cpu_roadmap;
        updateNodes();
    }

    public List<Node> getCPU_ROAD_MAP(){
        return CPU_ROAD_MAP;
    }

    private void updateNodes(){
        int index = CPU_ROAD_MAP.size() - 1;
        currentNode = CPU_ROAD_MAP.get(index);
        if (CPU_ROAD_MAP.size() > 1){
            targetNode = CPU_ROAD_MAP.get(index - 1);
            CPU_ROAD_MAP.remove(index);
        }

    }

    @Override
    public boolean hasWon(Node target) {
        return target.getPosX() == getPlayerPosition().getPosX() && target.getPosY() == getPlayerPosition().getPosY();
    }

    @Override
    public void setNewLocation() {
        //todo Some construction with direction from array to full position.
        if (currentNode.getPosX() > targetNode.getPosX()){
            getPlayerPosition().setPosX(getPlayerPosition().getPosX() - (getMOVEMENT_SPEED() * .1));
            if (getPlayerPosition().getPosX() <= targetNode.getPosX()){
                getPlayerPosition().setPosX(targetNode.getPosX());
                updateNodes();
            }
        }
        else if (currentNode.getPosX() < targetNode.getPosX()){
            getPlayerPosition().setPosX(getPlayerPosition().getPosX() + (getMOVEMENT_SPEED() * .1));
            if (getPlayerPosition().getPosX() >= targetNode.getPosX()){
                getPlayerPosition().setPosX(targetNode.getPosX());
                updateNodes();
            }
        }
        else if (currentNode.getPosY() > targetNode.getPosY()){
            getPlayerPosition().setPosY(getPlayerPosition().getPosY() - (getMOVEMENT_SPEED() * .1));
            if (getPlayerPosition().getPosY() <= targetNode.getPosY()){
                getPlayerPosition().setPosY(targetNode.getPosY());
                updateNodes();
            }
        }
        else if (currentNode.getPosY() < targetNode.getPosY()){
            getPlayerPosition().setPosY(getPlayerPosition().getPosY() + (getMOVEMENT_SPEED() * .1));
            if (getPlayerPosition().getPosY() >= targetNode.getPosY()){
                getPlayerPosition().setPosY(targetNode.getPosY());
                updateNodes();
            }
        }
    }
}
