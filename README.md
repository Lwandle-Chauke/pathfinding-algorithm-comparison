<div align="center">

# Pathfinding Algorithm Comparison

### Comparative implementation of DFS, BFS and A* Search in a constrained 2D environment using Java.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![AI](https://img.shields.io/badge/Artificial%20Intelligence-4285F4?style=for-the-badge)
![Algorithms](https://img.shields.io/badge/Algorithms-6A1B9A?style=for-the-badge)
![Graph Search](https://img.shields.io/badge/Graph%20Search-00897B?style=for-the-badge)

**Author:** **Lwandle Chauke**

</div>

---

# Overview

This project explores and compares three fundamental search algorithms used in Artificial Intelligence:

- Depth-First Search (DFS)
- Breadth-First Search (BFS)
- A* Search

The algorithms navigate a constrained **100 × 100 grid** containing a U-shaped obstacle ("Concave Trap") and randomly generated environmental obstacles.

The objective was to compare each algorithm's ability to locate the goal while evaluating:

- Path cost
- Search efficiency
- Nodes explored
- Execution time
- Path optimality
- Heuristic performance

---

# Project Highlights

- Classical AI search algorithms
- Performance comparison
- Pathfinding in constrained environments
- Manhattan Distance heuristic
- Seeded random obstacle generation
- Execution time analysis
- Comprehensive experimental report

---

# Algorithms Implemented

## Depth-First Search (DFS)

Characteristics:

- Uses a stack
- Explores one branch completely before backtracking
- Low memory usage
- Does not guarantee the shortest path

---

## Breadth-First Search (BFS)

Characteristics:

- Uses a queue
- Explores nodes level by level
- Guarantees the shortest path in an unweighted graph
- Higher memory requirements

---

## A* Search

Characteristics:

- Uses heuristic-guided search
- Combines actual path cost with estimated distance
- Efficient exploration
- Guarantees optimal paths when using an admissible heuristic

Heuristic:

```
h(n) = |x₁ − x₂| + |y₁ − y₂|
```

(Manhattan Distance)

---

# Environment

The simulation consists of a **100 × 100** grid.

### Start Position

```
(20, 50)
```

### Goal Position

```
(80, 50)
```

### Obstacles

The environment contains:

- Hard-coded U-shaped obstacle
- 15% randomly generated obstacles
- Seed-based environment generation for reproducibility

---

# Performance Metrics

Each algorithm was evaluated using:

- Path Cost
- Nodes Visited
- Execution Time
- Path Optimality
- Search Efficiency

---

# Tech Stack

- Java
- Object-Oriented Programming
- Graph Search Algorithms
- Artificial Intelligence
- Data Structures

---

# Repository Structure

```
pathfinding-algorithm-comparison/

├── src/
│
├── docs/
│   ├── Project-Report.pdf
│   ├── Experimental-Results.pdf
│   └── Performance-Analysis.pdf
│
├── README.md
├── .gitignore
└── LICENSE
```

---

# Running the Project

Clone the repository

```bash
git clone https://github.com/Lwandle-Chauke/pathfinding-algorithm-comparison.git
```

Compile

```bash
javac Main.java
```

Run

```bash
java Main
```

The application prompts for:

- Random seed
- Simulation parameters

before generating the environment.

---

# Experimental Evaluation

Each algorithm was compared using the following criteria.

| Metric | DFS | BFS | A* |
|---------|-----|-----|-----|
| Path Cost | ✓ | ✓ | ✓ |
| Nodes Visited | ✓ | ✓ | ✓ |
| Execution Time | ✓ | ✓ | ✓ |
| Optimal Path | No | Yes | Yes |

---

# Documentation

The repository includes detailed documentation covering:

- Experimental setup
- Algorithm implementation
- Performance evaluation
- Results
- Critical analysis
- Conclusions

---

# Key Concepts Demonstrated

- Artificial Intelligence
- Search Algorithms
- Graph Traversal
- Heuristic Search
- Algorithm Analysis
- Performance Benchmarking
- Time Complexity
- Space Complexity

---

# What I Learned

This project strengthened my understanding of:

- Classical AI search algorithms
- Heuristic-based pathfinding
- Performance evaluation
- Algorithm optimisation
- Java object-oriented programming
- Graph traversal
- Experimental analysis

It also reinforced how heuristics significantly improve search efficiency when navigating complex environments.

---

# Future Improvements

Potential future enhancements include:

- Dijkstra's Algorithm
- Greedy Best-First Search
- Bidirectional Search
- Weighted A*
- Interactive GUI visualisation
- Heatmap visualisations
- Multiple heuristic comparisons
- Dynamic obstacle generation

---

# About Me

I'm **Lwandle Chauke**, a Computer Science graduate with interests in:

- Artificial Intelligence
- Software Engineering
- Cybersecurity
- Algorithms
- DevSecOps

I enjoy solving complex computational problems and building efficient software solutions.

**GitHub**

https://github.com/Lwandle-Chauke

---

<div align="center">

If you found this project interesting, feel free to star the repository!

</div>
