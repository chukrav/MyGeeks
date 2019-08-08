package TreesAndGraphsCC;

import java.util.HashSet;
import java.util.LinkedList;

public class RouteBetweenNodesLe {

    static enum SearchType {
        BreadthFirst,
        DepthFirst
    }

    static class Graph{

        LinkedList<GraphNode> nodes = new LinkedList<>();
        Graph(){}

        public int getCount(){
            return nodes.size();
        }

        public LinkedList<GraphNode> getNodes(){
            return nodes;
        }

        public GraphNode Find(Integer value){
            for (GraphNode node:nodes ) {
                if ( node.value == value){
                    return node;
                }
            }
            return null;
        }

        public boolean AddNode(Integer value){
            if (Find(value) != null){
                return false;
            } else {
                nodes.add(new GraphNode(value));
                return true;
            }
        }

        public boolean AddEdge(Integer value1, Integer value2){
            GraphNode node1 = Find(value1);
            GraphNode node2 = Find(value2);
            if (node1 == null || node2 == null) {
                return false;
            } else if (node1.neighbors.contains(node2)){
                return false;
            }
            node1.AddNeighbor(node2);
            node2.AddNeighbor(node1);
            return true;
        }
    }

    static class GraphNode{
        int value;
        HashSet<GraphNode> neighbors;
        //LinkedList<Integer> costs;

        public GraphNode(int val){
            value = val;
            neighbors = new HashSet<>();
            //costs = new LinkedList<>();
        }

        public int getValue() {
            return value;
        }

        public HashSet<GraphNode> getNeighbors() {
            return neighbors;
        }

        public boolean AddNeighbor(GraphNode node){
            if (neighbors.contains(node)){
                return false;
            } else {
                return neighbors.add(node);
            }

            //return true;
        }

        public boolean RemoveNeighbor(GraphNode node){
            if (neighbors.contains(node)){
                return neighbors.remove(node);
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = BuildGraph_ex7();
        Search(4,1,graph,SearchType.DepthFirst);


    }

    static Graph BuildGraph_ex7()
    {
        Graph graph = new Graph();

        graph.AddNode(1);
        graph.AddNode(4);
        graph.AddNode(5);
        graph.AddNode(7);
        graph.AddNode(10);
        graph.AddNode(11);
        graph.AddNode(12);
        graph.AddNode(42);

        graph.AddEdge(1, 5);
        graph.AddEdge(4, 11);
        graph.AddEdge(4, 42);
        graph.AddEdge(5, 11);
        graph.AddEdge(5, 12);
        graph.AddEdge(5, 42);
        graph.AddEdge(7, 10);
        graph.AddEdge(7, 11);
        graph.AddEdge(10, 11);
        graph.AddEdge(11, 42);
        graph.AddEdge(12, 42);

        return graph;
    }

    static boolean Search(int start, int finish, Graph graph, SearchType searchType){

        LinkedList<GraphNode> searchList = new LinkedList<>();
        HashSet<GraphNode> pathNodes = new HashSet<>();
        if (start == finish) return true;
        else if (graph.Find(start) == null || graph.Find(finish) == null)
            return false;
        else {
            GraphNode startNode = graph.Find(start);
            //GraphNode endNode = graph.Find(finish);

            searchList.addFirst(startNode);
            pathNodes.add(startNode);
            while (searchList.size() > 0){
                GraphNode currentNode =  searchList.removeFirst();
                for (GraphNode neighbor : currentNode.neighbors) {
                    if (neighbor.value == finish){
                        pathNodes.add(neighbor);
                        System.out.println("The nodes are connected!");
                        return true;
                    } else if (pathNodes.contains(neighbor)){
                        // found a cycle, so skip this neighbor
                        continue;
                    } else {
                        pathNodes.add(neighbor);
                        if (searchType == SearchType.DepthFirst){
                            searchList.addFirst(neighbor);
                        }else {
                            searchList.add(neighbor);
                        }
                    }
                }
            }
        }

        return false;
    }


}
