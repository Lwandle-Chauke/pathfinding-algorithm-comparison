import java.util.*;

/**
 * This class holds the three search algorithms.
 * Each method returns a Results object with the metrics.
 * 
 * I tried to use recursion for DFS first but got stack overflow on the big
 * grid,
 * so I switched to an explicit stack.
 */
public class Algorithm {

    // Direction vectors: up, down, left, right
    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };

    /**
     * Depth‑First Search (DFS)
     * Uses a stack (LIFO). Goes deep first, then backtracks.
     */
    public Results dfs(Grid grid, int startX, int startY, int goalX, int goalY) {
        // visited[x][y] = true if we have already processed that cell
        boolean[][] visited = new boolean[Grid.SIZE][Grid.SIZE];

        // parent[x][y] remembers which node we came from – used to rebuild the path
        Node[][] parent = new Node[Grid.SIZE][Grid.SIZE];

        // Stack for DFS – using Stack class (old but simple)
        Stack<Node> stack = new Stack<>();

        // Create the start node
        Node start = new Node(startX, startY);
        stack.push(start);
        visited[startX][startY] = true;

        int nodesVisited = 0;
        long startTime = System.currentTimeMillis();

        while (!stack.isEmpty()) {
            Node current = stack.pop(); // take the most recent node
            nodesVisited++;

            // Check if we reached the goal
            if (current.x == goalX && current.y == goalY) {
                long endTime = System.currentTimeMillis();
                List<Node> path = reconstructPath(parent, current, startX, startY);
                // DFS does NOT guarantee optimality, so optimal = false
                return new Results(path.size() - 1, nodesVisited, endTime - startTime, false, path);
            }

            // Try all four neighbours
            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                // If neighbour is inside grid, not an obstacle, and not visited
                if (grid.isValid(nx, ny) && !grid.isObstacle(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true; // mark now to avoid pushing twice
                    Node neighbour = new Node(nx, ny);
                    neighbour.parent = current; // remember how we got here
                    parent[nx][ny] = current; // store parent for path reconstruction
                    stack.push(neighbour); // push onto stack
                }
            }
        }

        // No path found
        long endTime = System.currentTimeMillis();
        return new Results(-1, nodesVisited, endTime - startTime, false, null);
    }

    /**
     * Breadth‑First Search (BFS)
     * Uses a queue (FIFO). Explores all nodes at the same depth before going
     * deeper.
     */
    public Results bfs(Grid grid, int startX, int startY, int goalX, int goalY) {
        boolean[][] visited = new boolean[Grid.SIZE][Grid.SIZE];
        Node[][] parent = new Node[Grid.SIZE][Grid.SIZE];

        // Queue for BFS – LinkedList works as a queue
        Queue<Node> queue = new LinkedList<>();

        Node start = new Node(startX, startY);
        queue.add(start);
        visited[startX][startY] = true;

        int nodesVisited = 0;
        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // take the oldest node
            nodesVisited++;

            if (current.x == goalX && current.y == goalY) {
                long endTime = System.currentTimeMillis();
                List<Node> path = reconstructPath(parent, current, startX, startY);
                // BFS on an unweighted grid IS optimal
                return new Results(path.size() - 1, nodesVisited, endTime - startTime, true, path);
            }

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (grid.isValid(nx, ny) && !grid.isObstacle(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    Node neighbour = new Node(nx, ny);
                    neighbour.parent = current;
                    parent[nx][ny] = current;
                    queue.add(neighbour);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        return new Results(-1, nodesVisited, endTime - startTime, true, null);
    }

    /**
     * A* Search
     * Uses a priority queue ordered by f = g + h.
     * h = Manhattan distance to goal.
     */
    public Results aStar(Grid grid, int startX, int startY, int goalX, int goalY) {
        // visited[x][y] = true when we have expanded that node
        boolean[][] visited = new boolean[Grid.SIZE][Grid.SIZE];
        Node[][] parent = new Node[Grid.SIZE][Grid.SIZE];

        // gCost[x][y] stores the best g value found so far for that cell
        int[][] gCost = new int[Grid.SIZE][Grid.SIZE];
        for (int i = 0; i < Grid.SIZE; i++) {
            Arrays.fill(gCost[i], Integer.MAX_VALUE);
        }

        // Priority queue that orders nodes by their f value (lowest first)
        // Using an explicit Comparator instead of Comparator.comparingInt
        PriorityQueue<Node> openSet = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.f, n2.f);
            }
        });

        // Create start node
        Node start = new Node(startX, startY);
        start.g = 0;
        start.h = start.manhattanTo(goalX, goalY);
        start.f = start.g + start.h;

        openSet.add(start);
        gCost[startX][startY] = 0;

        int nodesVisited = 0;
        long startTime = System.currentTimeMillis();

        while (!openSet.isEmpty()) {
            Node current = openSet.poll(); // get node with smallest f

            // If we already expanded this node (with a better g), skip it
            if (visited[current.x][current.y]) {
                continue;
            }

            visited[current.x][current.y] = true;
            nodesVisited++;

            if (current.x == goalX && current.y == goalY) {
                long endTime = System.currentTimeMillis();
                List<Node> path = reconstructPath(parent, current, startX, startY);
                // A* with admissible heuristic is optimal
                return new Results(path.size() - 1, nodesVisited, endTime - startTime, true, path);
            }

            // Examine neighbours
            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (!grid.isValid(nx, ny) || grid.isObstacle(nx, ny)) {
                    continue;
                }

                // tentative g value if we go current -> neighbour
                int tentativeG = current.g + 1;

                // If this path is better than any previous one
                if (tentativeG < gCost[nx][ny]) {
                    // Update parent and costs
                    parent[nx][ny] = current;
                    gCost[nx][ny] = tentativeG;

                    // Create neighbour node (or reuse, but simpler to create new)
                    Node neighbour = new Node(nx, ny);
                    neighbour.parent = current;
                    neighbour.g = tentativeG;
                    neighbour.h = neighbour.manhattanTo(goalX, goalY);
                    neighbour.f = neighbour.g + neighbour.h;

                    // Add to open set – the priority queue will sort it
                    openSet.add(neighbour);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        return new Results(-1, nodesVisited, endTime - startTime, true, null);
    }

    /**
     * Reconstructs the path from start to goal by following parent pointers.
     * 
     * @param parent 2D array of parent nodes
     * @param goal   the goal node (already found)
     * @param startX start x
     * @param startY start y
     * @return list of nodes from start to goal
     */
    private List<Node> reconstructPath(Node[][] parent, Node goal, int startX, int startY) {
        List<Node> path = new ArrayList<>();
        Node current = goal;

        // Walk backwards from goal to start
        while (current != null && !(current.x == startX && current.y == startY)) {
            path.add(current);
            current = parent[current.x][current.y];
        }
        // Add the start node
        if (current != null) {
            path.add(current);
        }

        // Reverse so it goes from start to goal
        Collections.reverse(path);
        return path;
    }
}