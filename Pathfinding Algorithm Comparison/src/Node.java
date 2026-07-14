/**
 * A Node represents a single cell (x,y) in the grid.
 * It also stores information used by the search algorithms.
 * 
 * I originally forgot to override hashCode() – that caused bugs when I tried to
 * use HashSets.
 */
public class Node {
    // Coordinates
    public int x;
    public int y;

    // The node we came from – used to reconstruct the path at the end
    public Node parent;

    // For A* we need:
    // g = cost from start to this node (each step costs 1)
    // h = heuristic (Manhattan distance to goal)
    // f = g + h
    public int g;
    public int h;
    public int f;

    /**
     * Constructor – just remember the coordinates.
     * parent is null initially, costs are 0.
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.g = 0;
        this.h = 0;
        this.f = 0;
    }

    /**
     * Computes the Manhattan distance from this node to a target.
     * This is our heuristic h(n).
     * 
     * @param targetX goal x
     * @param targetY goal y
     * @return |x - targetX| + |y - targetY|
     */
    public int manhattanTo(int targetX, int targetY) {
        return Math.abs(this.x - targetX) + Math.abs(this.y - targetY);
    }

    /**
     * Two nodes are equal if they have the same coordinates.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * When you override equals, you should also override hashCode.
     * I learned this the hard way.
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    /**
     * For easy printing.
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}