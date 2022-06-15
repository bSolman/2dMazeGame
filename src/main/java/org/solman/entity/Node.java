package org.solman.entity;

import java.util.HashMap;
import java.util.Map;

public class Node implements Comparable<Node>{
    private double posX;
    private double posY;
    private boolean isVisited = false;
    private final boolean IS_PASSABLE;
    private double manhattanDistance = 0;
    private double heuristic;
    private final int ID;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    private Node nodePredecessors;

    public Node(int posX, int posY, boolean IS_PASSABLE, int ID) {
        this.posX = posX;
        this.posY = posY;
        this.IS_PASSABLE = IS_PASSABLE;
        this.ID = ID;
    }

    public Node getNodePredecessors() {
        return nodePredecessors;
    }

    public void setNodePredecessors(Node nodePredecessors) {
        this.nodePredecessors = nodePredecessors;
    }

    public int getID() {
        return ID;
    }

    public Map<Node, Integer> getAdjacentNodes(){
        return adjacentNodes;
    }

    public void addAdjacentNode(Node node, int weight){
        adjacentNodes.put(node, weight);
    }

    public boolean getIS_PASSABLE() {
        return IS_PASSABLE;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public double getManhattanDistance() {
        return manhattanDistance;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void calculateManhattanDistance(Node target){
        if (target.getPosX() >= this.posX){
            manhattanDistance += target.posX - this.posX;
        }else {
            manhattanDistance += this.posX - target.posX;
        }
        if (target.getPosY() >= this.posY){
            manhattanDistance += target.posY - this.posY;
        }else {
            manhattanDistance += this.posY - target.posY;
        }
    }

    public void setManhattanDistance(double x){
        manhattanDistance = x;
    }

    public void setHeuristic(int travelCost) {
        this.heuristic = travelCost + manhattanDistance;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare((int) this.manhattanDistance, (int) o.manhattanDistance);
    }
}
