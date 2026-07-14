1. OVERVIEW
------------
This program implements and compares three fundamental search algorithms:
- Depth-First Search (DFS)
- Breadth-First Search (BFS)
- A* Search (using Manhattan distance heuristic)

The environment is a 100x100 grid containing:
- A hard‑coded U‑shaped obstacle (the "trap")
- 15% randomly placed obstacles (seeded for reproducibility)
- Start position: (20,50)
- Goal position: (80,50)

The program measures:
- Path cost (number of steps from start to goal)
- Nodes visited during the search
- Execution time (milliseconds)
- Whether the algorithm guarantees optimality

2. PREREQUISITES
-----------------
- Java Development Kit (JDK) 11 or later (JDK 25.0.2 used for development)
- The `java` and `jar` commands must be available in your terminal.
  If they are not, either add the JDK `bin` folder to your system PATH,
  or use the full paths as shown in the compilation instructions below.

3. FILE STRUCTURE
------------------
The submission consists of the following files and folders:

COS314_Assignment1_u23574977/
├── src/
│   ├── Walle.java               # Main entry point
│   ├── Grid.java                 # Builds the world (U‑trap + random noise)
│   ├── Node.java                  # Represents a grid cell
│   ├── Algorithm.java             # Contains DFS, BFS, A* implementations
│   ├── Results.java                # Holds search results (path cost, nodes, time)
│   └── (compiled .class files)    # Generated after compilation
├── Walle.jar                      # Executable JAR (ready to run)
├── manifest.txt                    # Manifest file for JAR creation 
└── README.txt                      # This file

4. COMPILATION INSTRUCTIONS
----------------------------
If you wish to recompile the source code (optional, since the JAR is provided):

1. Open a terminal.
2. Navigate to the `src` folder:
   cd path\to\COS314_Assignment1_u23574977\src
3. Compile all Java files:
   javac *.java
   This will create `.class` files in the same folder.

5. RUNNING THE PROGRAM
-----------------------
Two methods are available:

Method A: Run the provided JAR directly 
----------------------------------------
1. Open a terminal in the root folder (where `Walle.jar` is located).
2. Execute:
   java -jar Walle.jar
   (If `java` is not in your PATH, use the full path, e.g.:
   "C:\Program Files\Java\jdk-25.0.2\bin\java" -jar Walle.jar)

Method B: Run from compiled class files
----------------------------------------
1. Navigate to the `src` folder.
2. Run:
   java Walle
   (Again, use full path to `java` if needed.)

6. INPUT
---------
When the program starts, it prompts:

   Enter a seed number (any integer):

Type any whole number (e.g., 21, 12345, 0) and press Enter.
The seed determines the placement of the 15% random obstacles,
allowing reproducible experiments.

7. OUTPUT
----------
The program first prints a small section of the grid around the U‑trap
to visually confirm obstacle placement. Then it runs the three search
algorithms and displays a comparison table similar to this:

=========================================
Comparison of Search Algorithms
Grid: 100x100 with U‑trap and 15% noise
Start: (20,50)  Goal: (80,50)
=========================================
Metric               | DFS          | BFS          | A*
---------------------|--------------|--------------|--------------
Path Cost (steps)    | 746          | 104          | 104
Nodes Visited        | 2892         | 7315         | 1800
Time (ms)            | 4            | 8            | 3
Optimal?             | No           | Yes          | Yes
=========================================

Interpretation of columns:
- Path Cost: number of steps in the found path (-1 if no path exists).
- Nodes Visited: how many grid cells were expanded during the search.
- Time (ms): execution time in milliseconds (may vary slightly between runs).
- Optimal?: "Yes" if the algorithm guarantees the shortest path on this type of grid.

8. TROUBLESHOOTING
--------------------
Problem: "java" or "jar" is not recognized.
Solution: Either:
   - Add the JDK `bin` folder to your system PATH (see Section 9), or
   - Use the full path to the executable, e.g.:
        "C:\Program Files\Java\jdk-25.0.2\bin\java" -jar Walle.jar
        "C:\Program Files\Java\jdk-25.0.2\bin\jar" cfm Walle.jar manifest.txt *.class

Problem: JAR creation fails with "invalid header field".
Solution: Ensure the manifest file `manifest.txt` contains exactly:
        Main-Class: Walle
   (with a blank line at the end). Recreate it using Notepad or the command:
        echo Main-Class: Walle > manifest.txt
   then open the file and press Enter to add a blank line, then save.

Problem: Program runs but shows "No Path" for some algorithms.
Solution: With certain seed values, the random obstacles may completely block
   the path between start and goal. This is a valid scenario – the table
   will show "No Path" and the path cost as -1.

9. SETTING JAVA PATH (Windows)
--------------------------------
If you want `java` and `jar` to work in any terminal without typing full paths:

1. Press Windows key → type "environment variables" → open "Edit the system environment variables".
2. Click "Environment Variables…".
3. Under "System variables", select "Path" and click "Edit…".
4. Click "New" and add the path to your JDK `bin` folder, e.g.:
   C:\Program Files\Java\jdk-25.0.2\bin
5. Click OK on all dialogs.
6. Close and reopen your terminal.

10. CONTACT
------------
If you encounter any issues, please contact me at u23574977@tuks.co.za or via ClickUP.

================================================================================
