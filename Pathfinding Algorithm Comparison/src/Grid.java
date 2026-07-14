import java.util.Random;

/**
 * Grid represents the 100x100 world where Wall-E moves.
 * true = obstacle, false = free space.
 * 
 * I spent a while making sure the U‑trap is exactly as described.
 */
public class Grid {
    // The world is always 100 by 100 (assignment says so)
    public static final int SIZE = 100;

    // This 2D array stores whether each cell is an obstacle
    private boolean[][] obstacles;

    // Random number generator – we'll use a seed so the same seed gives the same
    // rocks
    private Random random;

    // Start and goal positions – fixed by the assignment
    public static final int START_X = 20;
    public static final int START_Y = 50;
    public static final int GOAL_X = 80;
    public static final int GOAL_Y = 50;

    /**
     * Constructor – builds the grid using the given seed.
     * 
     * @param seed any long number – the same seed will always produce the same
     *             random rocks
     */
    public Grid(long seed) {
        // Create the array and fill it with false (no obstacles yet)
        obstacles = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                obstacles[i][j] = false;
            }
        }

        // Set up the random generator with the seed
        random = new Random(seed);

        // Step 1: put the U‑trap exactly as described
        placeUTrap();

        // Step 2: add 15% random rocks to all cells that are still free
        addRandomNoise(0.15);

        // Step 3: double‑check that start and goal are free
        // (they might have accidentally been set by random noise)
        obstacles[START_X][START_Y] = false;
        obstacles[GOAL_X][GOAL_Y] = false;

        System.out.println("Grid created with seed: " + seed);
    }

    /**
     * Places the U‑shaped trap.
     * Back wall: vertical line at x=50 from y=30 to y=70.
     * Top arm: horizontal line from (30,30) to (50,30).
     * Bottom arm: horizontal line from (30,70) to (50,70).
     */
    private void placeUTrap() {
        // Back wall – all cells where x=50 and y between 30 and 70 become obstacles
        for (int y = 30; y <= 70; y++) {
            obstacles[50][y] = true;
        }

        // Top arm – from (30,30) to (50,30)
        for (int x = 30; x <= 50; x++) {
            obstacles[x][30] = true;
        }

        // Bottom arm – from (30,70) to (50,70)
        for (int x = 30; x <= 50; x++) {
            obstacles[x][70] = true;
        }

        System.out.println("U‑trap placed.");
    }

    /**
     * Adds random obstacles to the grid.
     * 
     * @param probability how many cells become rocks (0.15 = 15%)
     */
    private void addRandomNoise(double probability) {
        int rockCount = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                // Skip cells that are already obstacles (part of U‑trap)
                // Also skip start and goal – they must remain free
                if (obstacles[x][y] ||
                        (x == START_X && y == START_Y) ||
                        (x == GOAL_X && y == GOAL_Y)) {
                    continue;
                }

                // Generate a random number between 0.0 and 1.0.
                // If it's less than the probability, put a rock.
                if (random.nextDouble() < probability) {
                    obstacles[x][y] = true;
                    rockCount++;
                }
            }
        }
        System.out.println("Added " + rockCount + " random rocks (15% noise).");
    }

    /**
     * Checks whether a cell is an obstacle (or out of bounds).
     * 
     * @param x column
     * @param y row
     * @return true if you cannot walk there
     */
    public boolean isObstacle(int x, int y) {
        // If the cell is outside the grid, treat it as an obstacle (can't go there)
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return true;
        }
        return obstacles[x][y];
    }

    /**
     * Checks if a cell is inside the grid.
     * 
     * @param x column
     * @param y row
     * @return true if inside bounds
     */
    public boolean isValid(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    // Optional: a small method to print a section of the grid (useful for
    // debugging)
    public void printSection(int centerX, int centerY, int radius) {
        System.out.println("\nGrid around (" + centerX + "," + centerY + "):");
        System.out.println("0=free, 1=rock, S=start, G=goal");
        for (int y = centerY - radius; y <= centerY + radius; y++) {
            for (int x = centerX - radius; x <= centerX + radius; x++) {
                if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
                    System.out.print("# "); // outside world
                } else if (x == START_X && y == START_Y) {
                    System.out.print("S ");
                } else if (x == GOAL_X && y == GOAL_Y) {
                    System.out.print("G ");
                } else if (obstacles[x][y]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
}