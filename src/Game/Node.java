package Game;

import java.util.ArrayList;

public class Node {
    int value;
    BallColor ballColor;
    ArrayList<Node> neighbors;
    ArrayList<Integer> distances;

    public Node(int value){
        this.value = value;
        neighbors = new ArrayList<>();
        distances = new ArrayList<>();
        ballColor = BallColor.empty;

    }

    public int getValue() {
        return value;
    }

    public BallColor getBallColor() {
        return ballColor;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getDistances() {
        return distances;
    }

    public void setBallColor(BallColor ballColor) {
        this.ballColor = ballColor;
    }


    public int getEdgeCost(Node node){
        if (neighbors.contains(node)){
            int idx =  neighbors.indexOf(node);
            return distances.get(idx);
        }
        return -1;
    }

    public boolean AddNeighbor(Node node, Integer distance){
        if (neighbors.contains(node)){
            return false;
        } else {
            neighbors.add(node);
            distances.add(distance);

        }
        return true;
    }

    public boolean RemoveNeighbor(Node node){
        if (!neighbors.contains(node)){
            return false;
        } else {
            int idx = neighbors.indexOf(node);
            neighbors.remove(idx);
            distances.remove(idx);
        }
        return true;
    }

    public boolean RemoveAllNeighbors(){
        neighbors.clear();
        distances.clear();
        return true;
    }

    public String toString(){
        StringBuilder line = new StringBuilder();
        line.append("V: "+ value + )

        return null;
    }
}
