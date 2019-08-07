package TreesAndGraphsCC;

import java.util.LinkedList;

/*4.1 Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
        route between two nodes.
        SOLUTION
    ------------------------------
    This problem can be solved by just simple graph traversal, such as depth-first search or breadth-first search.
    We start with one of the two nodes and, during traversal, check if the other node is found. We should mark
    any node found in the course of the algorithm as "already visited" to avoid cycles and repetition of the
    nodes.

           pp 252 */
public class RouteBetweenNodes {

    //The code below provides an iterative implementation of breadth-first search.
    enum State { Unvisited, Visited, Visiting; }

    boolean search(Graph g, Node start, Node end) {
        if (start == end) return true;

        // operates as Queue
        LinkedList<Node> q = new LinkedList<Node>();

        for (Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while (!q.isEmpty()) {
            u = q.removeFirst(); // i.e., dequeue()
            if (u != null) {
                for (Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }

    class Node {
        private Node adjacent[];
        public int adjacentCount;
        private String vertex; // Node
        public State state;
        public Node(String vertex, int adjacentLength) {
            this.vertex = vertex;
            adjacentCount = 0;
            adjacent = new Node[adjacentLength];
        }

        public void addAdjacent(Node x) {
            if (adjacentCount < adjacent.length) {
                this.adjacent[adjacentCount] = x;
                adjacentCount++;
            } else {
                System.out.print("No more adjacent can be added");
            }
        }
        public Node[] getAdjacent() {
            return adjacent;
        }
        public String getVertex() {
            return vertex;
        }
    }

    public class Graph {
        public int MAX_VERTICES = 6;
        private Node vertices[];
        public int count;
        public Graph() {
            vertices = new Node[MAX_VERTICES];
            count = 0;
        }

        public Graph(int numVertices) {
            MAX_VERTICES = numVertices;
            vertices = new Node[MAX_VERTICES];
            count = 0;
        }

        public void addNode(Node x) {
            if (count < vertices.length) {
                vertices[count] = x;
                count++;
            } else {
                System.out.print("Graph full");
            }
        }

        public Node[] getNodes() {
            return vertices;
        }
    }
}
