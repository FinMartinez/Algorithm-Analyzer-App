/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: HyperRingGraph.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.1
 * 
 * Description:
 * This class defines the structure and behavior of a HyperRing graph — a type of 
 * graph where each node connects to others based on power-of-two offsets (1, 2, 4, ...),
 * wrapping within bounds using bitwise operations. The program allows:
 * - Construction of the HyperRing graph given a user-defined number of nodes.
 * - Display of the graph as both an adjacency list and an adjacency matrix.
 * - Execution of Depth-First Search (DFS) and Breadth-First Search (BFS) from a user-defined start node.
 * 
 * Features:
 * - Interactive console-based interface for graph creation and traversal.
 * - Efficient bitwise logic for neighbor computation in graph construction.
 * - Validated user input for start node selection and traversal type.
 * - Option to perform multiple graph operations in one session.
 * 
 * Notes:
 * - This file functions independently from AI/ML modules and other graph algorithms like 
 *   Dijkstra’s or MST — those are handled in separate files/classes within the project.
 * - To fully utilize AI-based recommendations or additional graph algorithms, ensure 
 *   relevant classes (e.g., GraphLoader, AIAnalyzer, DijkstraTestCases, etc.) are implemented 
 *   and integrated into the main application.
 */

import java.util.*;

public class HyperRingGraph {
    private int nodes;
    private Map<Integer, Set<Integer>> adjacencyList;

    public HyperRingGraph(int nodes) {
        this.nodes = nodes;
        adjacencyList = new HashMap<>();
        buildGraph();
    }

    private void buildGraph(){
        for (int i = 0; i < nodes; i++) {
            adjacencyList.put(i, new HashSet<>());
            for (int j = 0; (1 << j) < nodes ; j++) {
                int neighbor = (i + (1 << j)) & nodes;
                if (neighbor >= 0 && neighbor < nodes) {
                    adjacencyList.get(i).add(neighbor);
                    adjacencyList.computeIfAbsent(neighbor, k -> new HashSet<>()).add(i);
                }
            }
        }
    }

    public void displayAdjencenyMatrix(){
        int [][] matrix = new int[nodes][nodes];
        
        for (int i=0; i<nodes; i++) {
            for(int neighbor : adjacencyList.get(i)) {
                matrix[i][neighbor] = 1;
            }
        }
        
        System.out.println("Adjacency Matrix:");
        System.out.println();
        for (int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("****************************************************");
    }

    public void displayGraph() {
        System.out.println("HyperRing Graph Connections:");
        System.out.println();
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int startNode)  {
        if (startNode < 0 || startNode >= nodes) {
            System.out.println("Invalid start node. Please enter a valid node between 0 and " + (nodes - 1));
            return;
        }
        boolean[]   visited = new boolean[nodes];
        System.out.println("DFS Traversal");
        System.out.println("Start Node: " + startNode);
        dfsHelper(startNode, visited);
        System.out.println();
    }
    
    private void dfsHelper(int node, boolean[] visited) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void bfs(int startNode) {
        if (startNode < 0 || startNode >= nodes) {
            System.out.println("Invalid start node. Please enter a valid node between 0 and " + (nodes - 1));
            return;
        }
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;

        System.out.println("BFS Traversal");
        System.out.println("Start Node: " + startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adjacencyList.get(node)) {
                if (neighbor >= 0 && neighbor < nodes && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter the number of nodes in the HyperRing Graph:");
            int nodes = scanner.nextInt();
            System.out.println("****************************************************");

            HyperRingGraph hyperRingGraph = new HyperRingGraph(nodes);
            hyperRingGraph.displayAdjencenyMatrix();
            hyperRingGraph.displayGraph();

            System.out.println("****************************************************");
            System.out.println("Choose traversal type (DFS or BFS): ");
            String traversalType = scanner.next().toUpperCase();

            System.out.println("Enter the start node: ");
            int startNode = scanner.nextInt();

            if (traversalType.equals("DFS")) {
                hyperRingGraph.dfs(startNode);
            } else if (traversalType.equals("BFS")) {
               hyperRingGraph.bfs(startNode);
            } else {
                System.out.println("Invalid choice. Please enter either DFS or BFS.");
            }

            System.out.println("****************************************************");
            System.out.println("Do you want to continue? (Y/N)");
            String continueChoice = scanner.next().toUpperCase();

            if (!continueChoice.equals("Y")) {
                System.out.println("Exiting...");
                break;
            }
        }
        scanner.close();
    }
}
