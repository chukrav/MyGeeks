package Game;

import java.util.ArrayList;

public class Graph {

    ArrayList<Node> nodes = new ArrayList<>();


    public Graph(){

    }

    public int count(){
        return nodes.size();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void clear(){
        // remove all the neighbors from each node
        // so nodes can be garbage collected
        for (Node node : nodes) {
            node.RemoveAllNeighbors();
        }
        // now remove all the nodes from the graph
        for (int i = 0; i < nodes.size(); ++i){
            nodes.remove(i);
        }

    }

    public boolean addNode(int value){
        if (find(value) != null)
            return false;
        else
            nodes.add(new Node(value));
        return true;
    }

    public boolean addEdge(int value1, int value2, int distance){
        Node node1 = find(value1);
        Node node2 = find(value2);
        if (node1 == null || node2 == null)
            return false;
        if (node1.neighbors.contains(node2))
            return false;
        node1.AddNeighbor(node1,distance);
        node2.AddNeighbor(node1, distance);
        return true;
    }

    public boolean removeNode(int value){
        
    }

    public Node find(int value){
        for (Node node:nodes) {
            if (node.getValue() == value)
                return node;
        }
        return null;
    }
}
