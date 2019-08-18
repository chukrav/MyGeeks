package TreesAndGraphsCC;

/*  4.7 Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
projects, where thesecond projectis dependent on the first project). All of a project's dependencies
must be built before the project is. Find a build order that will allow the projects to be built. If there
is no valid build order, return an error.
EXAMPLE
Input:
projects: a, b, c, d, e, f
dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
Output: f, e, a, b, d, c

     pp 261   */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class BuildOrder {

    /* Find a correct build order. */
    Project[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph= buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    /* Build the graph, adding the edge (a, b) if b is dependent on a. Assumes a pair
    * is listed in "build order". The pair (a, b) in dependencies indicates that b
    * depends on a and a must be built before b. */
    Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph= new Graph();
        for (String project : projects) {
            graph.getOrCreateNode(project);
        }

        for (String[] dependency : dependencies) {
            String first= dependency[0];
            String second= dependency[1];
            graph.addEdge(first, second);
        }

        return graph;
    }

    /* Return a list of the projects a correct build order.*/
    Project[] orderProjects(ArrayList<Project> projects) {
        Project[] order = new Project[projects.size()];

         /* Add "roots" to the build order first.*/
        int endOfList= addNonDependent(order, projects, 0);

        int toBeProcessed= 0;
        while (toBeProcessed < order.length) {
            Project current= order[toBeProcessed];

             /* We have a circular dependency since there are no remaining projects with
             * zero dependencies. */
            if (current == null) {
                return null;
            }
                 /* Remove myself as a dependency. */
            ArrayList<Project> children = current.getChildren();
            for (Project child : children) {
                child.decrementDependencies();
            }
             
              /* Add children that have no one depending on them. */
            endOfList= addNonDependent(order, children, endOfList);
            toBeProcessed++;
        }

        return order;
    }
    /* A helper function to insert projects with zero dependencies into the order
* array, starting at index offset. */
    int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
        for (Project project: projects) {
            if (project.getNumberDependencies() == 0) {
                order[offset] = project;
                offset++;
            }
        }
        return offset;
    }

    public class Graph {
        private ArrayList<Project> nodes= new ArrayList<Project>();
        private HashMap<String, Project> map = new HashMap<String, Project>();

        public Project getOrCreateNode(String name) {
            if (!map.containsKey(name)) {
                Project node = new Project(name);
                nodes.add(node);
                map.put(name, node);
            }

            return map.get(name);
        }

        public void addEdge(String startName, String endName) {
            Project start = getOrCreateNode(startName);
            Project end= getOrCreateNode(endName);
            start.addNeighbor(end);
        }

        public ArrayList<Project> getNodes() { return nodes; }
    }


    // test was not implemented.
    public static void main(String[] args) {

    }

    /*We also need a state to indicate "I've already processed/built this node" so we don't re-build the node. Our
    state therefore can have three options: COMPLETED, PARTIAL, and BLANK.
    The code below implements this algorithm.
    Like the earlier algorithm, this solution is O (P+D) time, where P is the number of projects and D is the
    number of dependency pairs.
    By the way, this problem is called topological sort: linearly ordering the vertices in a graph such
    that for every edge (a, b), a appears before b in the linear order.
*/

    Stack<Project> findBuildOrder2(String[] projects, String[][] dependencies) {
        Graph graph= buildGraph(projects, dependencies);
        return orderProjects2(graph.getNodes());
    }

    Stack<Project> orderProjects2(ArrayList<Project> projects){
        Stack<Project> stack = new Stack<Project>();
        for (Project project: projects){
            if (project.getState() == Project.State.BLANK) {
                if (!doDFS(project, stack)){
                    return null;
                }
            }
        }
        return stack;
    }

    boolean doDFS(Project project, Stack<Project> stack){
        if (project.getState() == Project.State.PARTIAL){
            return false; // Cycle
        }

        if (project.getState() == Project.State.BLANK){
            project.setState(Project.State.PARTIAL);
            ArrayList<Project> children = project.getChildren();
            for (Project child : children){
                if (!doDFS(child, stack)){
                    return false;
                }
            }
            project.setState(Project.State.COMPLETE);
            stack.push(project);
        }
        return true;
    }

    /* Same as before*/
    //Graph buildGraph(String[] projects, String[][] dependencies){...}
    //public class Graph{}

    /* Essentially equivalent to earlier solution, with state info added and
    * dependency count removed.*/


    public static class Project {
        private ArrayList<Project> children = new ArrayList<Project>();
        private HashMap<String, Project> map= new HashMap<String, Project>();
        private String name;
        private int dependencies = 0;

        public Project(String n) { name = n; }
        public enum State{COMPLETE, PARTIAL, BLANK};
        private State state = State.BLANK;
        public State getState(){ return state; }
        public void setState(State st){ state = st; }

        public void addNeighbor(Project node) {
            if (!map.containsKey(node.getName())) {
                children.add(node);
                map.put(node.getName(), node);
                node.incrementDependencies();
            }
        }

        public void incrementDependencies() { dependencies++; }
        public void decrementDependencies() { dependencies--; }

        public String getName() { return name; }
        public ArrayList<Project> getChildren() { return children; }
        public int getNumberDependencies() { return dependencies; }
    }



}
