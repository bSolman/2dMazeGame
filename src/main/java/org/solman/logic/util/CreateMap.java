package org.solman.logic.util;

import org.solman.entity.Node;

import java.io.IOException;
import java.util.*;

public class CreateMap {
    private ManageFileInput manageFileInput;
    List<List<Node>> nodeList = new ArrayList<>();
    private int fileChoice;
    public CreateMap(int fileChoice)  {
        this.fileChoice = fileChoice;
        manageFileInput = new ManageFileInput();
        try {
            manageFileInput.saveCsvToList(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        createNodeList(manageFileInput.getFileAsMap());
    }

    public Node getStartNode(){
        String[] startNode = manageFileInput.getStartCoordinates().split(",");
        return new Node(parseInteger(startNode[0]), parseInteger(startNode[1]), true, 0);
    }

    public Node getEndNode() {
        String[] endNode = manageFileInput.getTargetCoordinate().split(",");
        return new Node(parseInteger(endNode[0]), parseInteger(endNode[1]), true, 0);
    }

    private int parseInteger(String s){
        return Integer.parseInt(s);
    }

    public List<List<Node>> getNodes() {
        return nodeList;
    }

    public List<String> getMapAsString(){
        return manageFileInput.getFileAsMap();
    }

    void createNodeList(List<String> map) {
        int counter = 1;
        for (int i = 0; i < map.size(); i++){
            List<Node> nodes = new ArrayList<>();
            for (int j = 0; j < map.get(i).length(); j++){
                if (map.get(i).charAt(j) == 'x'){
                    nodes.add(new Node(j, i, false, counter));
                }else {
                    nodes.add(new Node(j, i, true, counter));
                }
                counter++;
            }
            nodeList.add(nodes);
        }
    }

    public List<Node> getCpuShortestPath(Node start, Node end, List<List<Node>> n) {
        List<List<Node>> nodes = n;
        Set<Node> checkedNodes = new HashSet<>();
        Queue<Node> uncheckedNodes = new PriorityQueue<>();
        uncheckedNodes.add(nodes.get((int) start.getPosY()).get((int) start.getPosX()));

        while (!uncheckedNodes.isEmpty()){
            Node currentNode = uncheckedNodes.poll();
            if (currentNode.getPosY() == end.getPosY() && currentNode.getPosX() == end.getPosX()){
                List<Node> retList = new ArrayList<>();
                return shortestPathToList(currentNode, nodes, start, retList);
            }
            List<Node> neighbours = checkNeighbours(currentNode, nodes);
            uncheckedNodes.addAll(neighbours);
            currentNode.setVisited(true);
            checkedNodes.add(currentNode);
        }
        return null;
    }

    private List<Node> shortestPathToList(Node currentNode, List<List<Node>> nodes, Node target, List<Node> shortestPathList) {
        Node newNode = currentNode;
        shortestPathList.add(currentNode);

        if (newNode.getPosY() == target.getPosY() && newNode.getPosX() == target.getPosX()){
            return shortestPathList;
        }
        newNode = nodes.get((int) currentNode.getPosY()).get((int) currentNode.getPosX()).getNodePredecessors();
        return shortestPathToList(newNode, nodes, target, shortestPathList);
    }

    List<Node> checkNeighbours(Node currentNode, List<List<Node>> nodes) {
        int x = nodes.get(0).size() - 1;
        int y = nodes.size() - 1;
        List<Node> neighbours = new ArrayList<>();

        // add Node to left
        if (currentNode.getPosX() > 0){
            if (nodes.get((int) currentNode.getPosY()).get((int) (currentNode.getPosX() - 1)).getIS_PASSABLE() && !nodes.get((int) currentNode.getPosY()).get((int) (currentNode.getPosX() - 1)).isVisited()){
                neighbours.add(nodes.get((int) currentNode.getPosY()).get((int) (currentNode.getPosX() - 1)));
            }
        }
        // add Node to right
        if (currentNode.getPosX() < x){
            if (nodes.get((int) currentNode.getPosY()).get((int) (currentNode.getPosX() + 1)).getIS_PASSABLE() && !nodes.get((int) currentNode.getPosY()).get((int) (currentNode.getPosX() + 1)).isVisited()){
                neighbours.add(nodes.get((int) currentNode.getPosY()).get((int) (currentNode.getPosX() + 1)));
            }
        }
        // add above Node
        if (currentNode.getPosY() > 0){
            if (nodes.get((int) (currentNode.getPosY() - 1)).get((int) currentNode.getPosX()).getIS_PASSABLE() && !nodes.get((int) (currentNode.getPosY() - 1)).get((int) currentNode.getPosX()).isVisited()){
                neighbours.add(nodes.get((int) (currentNode.getPosY() - 1)).get((int) currentNode.getPosX()));
            }
        }
        // add below Node
        if (currentNode.getPosY() < y){
            if (nodes.get((int) (currentNode.getPosY() + 1)).get((int) currentNode.getPosX()).getIS_PASSABLE() && !nodes.get((int) (currentNode.getPosY() + 1)).get((int) currentNode.getPosX()).isVisited()){
                neighbours.add(nodes.get((int) (currentNode.getPosY() + 1)).get((int) currentNode.getPosX()));
            }
        }
        neighbours.stream().forEach(n -> n.setManhattanDistance(1 + currentNode.getManhattanDistance()));
        neighbours.stream().forEach(n -> n.setNodePredecessors(currentNode));
        return neighbours;
    }

}
