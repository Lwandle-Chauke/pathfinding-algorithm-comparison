import java.util.List;

/**
 * Holds the results of one search run.
 * 
 * I used this class to keep all the metrics together – makes the main code
 * cleaner.
 */
public class Results {
    private int pathLength; // number of steps from start to goal (-1 if no path)
    private int nodesVisited; // how many nodes were expanded
    private long timeMs; // execution time in milliseconds
    private boolean optimal; // does the algorithm guarantee the shortest path?
    private List<Node> path; // the actual path (list of nodes) – optional but nice

    /**
     * Constructor – store all the info.
     */
    public Results(int pathLength, int nodesVisited, long timeMs, boolean optimal, List<Node> path) {
        this.pathLength = pathLength;
        this.nodesVisited = nodesVisited;
        this.timeMs = timeMs;
        this.optimal = optimal;
        this.path = path;
    }

    // Getters so other classes can read these values
    public int getPathLength() {
        return pathLength;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public long getTimeMs() {
        return timeMs;
    }

    public boolean isOptimal() {
        return optimal;
    }

    public List<Node> getPath() {
        return path;
    }

    // Helper to check if a path was found
    public boolean pathFound() {
        return pathLength >= 0;
    }
}