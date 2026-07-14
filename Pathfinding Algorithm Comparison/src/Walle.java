import java.util.Scanner;

/**
 * Main class – named after our robot Wall‑E.
 * It asks the user for a seed, builds the world, and runs the three searches.
 * 
 * @author Your Name (u12345678)
 * @version 1.0 (last edited 2025/03/07)
 */
public class Walle {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("     Wall‑E Pathfinder (COS314)");
        System.out.println("=========================================");

        // Ask for seed – I hope the user doesn't type a letter...
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a seed number (any integer): ");
        long seed = scanner.nextLong();
        System.out.println();

        // Create the world with that seed
        Grid world = new Grid(seed);

        // Print a little piece of the world so we can see the U‑trap
        world.printSection(50, 50, 10);

        // Create the algorithm runner
        Algorithm algo = new Algorithm();

        // Run DFS
        System.out.println("\n--- Running DFS ---");
        Results dfsResult = algo.dfs(world, Grid.START_X, Grid.START_Y, Grid.GOAL_X, Grid.GOAL_Y);

        // Run BFS
        System.out.println("\n--- Running BFS ---");
        Results bfsResult = algo.bfs(world, Grid.START_X, Grid.START_Y, Grid.GOAL_X, Grid.GOAL_Y);

        // Run A*
        System.out.println("\n--- Running A* ---");
        Results astarResult = algo.aStar(world, Grid.START_X, Grid.START_Y, Grid.GOAL_X, Grid.GOAL_Y);

        // Print the comparison table
        printTable(dfsResult, bfsResult, astarResult);

        scanner.close(); // forgot this earlier, now added
    }

    /**
     * Prints the results in the required table format.
     * I tried to make the columns line up nicely – hope it works.
     */
    private static void printTable(Results dfs, Results bfs, Results astar) {
        System.out.println("\n=========================================");
        System.out.println("Comparison of Search Algorithms");
        System.out.println("Grid: 100x100 with U‑trap and 15% noise");
        System.out.println("Start: (20,50)  Goal: (80,50)");
        System.out.println("=========================================");

        // Table header
        System.out.printf("%-20s | %-12s | %-12s | %-12s%n",
                "Metric", "DFS", "BFS", "A*");
        System.out.println("---------------------|--------------|--------------|--------------");

        // Path cost row
        String dfsPath = dfs.pathFound() ? String.valueOf(dfs.getPathLength()) : "No Path";
        String bfsPath = bfs.pathFound() ? String.valueOf(bfs.getPathLength()) : "No Path";
        String astarPath = astar.pathFound() ? String.valueOf(astar.getPathLength()) : "No Path";
        System.out.printf("%-20s | %-12s | %-12s | %-12s%n",
                "Path Cost (steps)", dfsPath, bfsPath, astarPath);

        // Nodes visited row
        System.out.printf("%-20s | %-12d | %-12d | %-12d%n",
                "Nodes Visited",
                dfs.getNodesVisited(),
                bfs.getNodesVisited(),
                astar.getNodesVisited());

        // Execution time row
        System.out.printf("%-20s | %-12d | %-12d | %-12d%n",
                "Time (ms)",
                dfs.getTimeMs(),
                bfs.getTimeMs(),
                astar.getTimeMs());

        // Optimality row
        System.out.printf("%-20s | %-12s | %-12s | %-12s%n",
                "Optimal?",
                dfs.isOptimal() ? "Yes" : "No",
                bfs.isOptimal() ? "Yes" : "No",
                astar.isOptimal() ? "Yes" : "No");

        System.out.println("=========================================");
    }
}