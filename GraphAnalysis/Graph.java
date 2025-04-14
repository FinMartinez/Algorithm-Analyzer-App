/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: Graph.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * The `Graph` class provides an implementation of graph data structures and algorithms. 
 * It supports both adjacency matrix and adjacency list representations and offers methods 
 * for common graph operations including traversal, shortest path calculations, and 
 * minimum spanning tree generation.
 * 
 * Features:
 * - Graph Representations:
 *   - Adjacency Matrix
 *   - Adjacency List
 * - Algorithms:
 *   - Dijkstra's Algorithm for shortest path calculation
 *   - Breadth-First Search (BFS) and Depth-First Search (DFS) for graph traversal
 *   - Prim's Algorithm for Minimum Spanning Tree (MST)
 * - Cost Analysis:
 *   - Shortest path costs via BFS, DFS, and Dijkstra's Algorithm
 *   - Total cost of MST
 * - Modular design for integration with other components (e.g., AI Analyzer, test cases).
 * 
 * Usage:
 * - Instantiate the `Graph` class with the number of nodes.
 * - Use `addEdge` to define edges with weights.
 * - Call methods such as `runDijkstra`, `bfs`, `dfs`, and `findMST` to perform graph operations.
 * 
 * Dependencies:
 * Ensure that supporting classes (e.g., AIAnalyzer, GraphLoader) and test cases 
 * are implemented to maximize the functionality of this class.
 */

package GraphAnalysis;

import java.util.*;

public class Graph {
    private int nodes; // Number of nodes
    private int[][] adjacencyMatrix; // Adjacency matrix representation of the graph
    private Map<Integer, Set<Integer>> adjacencyList; // Adjacency list representation of the graph

    //Constructor
    public Graph(int nodes) {
        this.nodes = nodes;
        adjacencyMatrix = new int[nodes][nodes];
        adjacencyList = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            adjacencyList.put(i, new HashSet<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        // Add an edge to the graph
        adjacencyMatrix[from][to] = weight;
        adjacencyMatrix[to][from] = weight;
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }
    public void displayAdjencenyMatrix() {
        // Print the adjacency matrix
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void displayAdjencenyList(){
        // Print the adjacency list
        System.out.println("Adjacency List:");
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public int runDijkstra(int source) {
        // Implement Dijkstra's algorithm
        int[] distances = new int[nodes];
        boolean[] visited = new boolean[nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (visited[current]) continue;

            visited[current] = true;

            for (int neighbor : adjacencyList.get(current)) {
                int weight = adjacencyMatrix[current][neighbor];
                if (distances[current] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[current] + weight;
                    pq.add(neighbor);
                    }
                }
            }

        //Calculate and return the total cost of the shortest paths
        int totalCost = 0;
        for (int i = 0; i < nodes; i++) {
            if (distances[i] != Integer.MAX_VALUE) {
                totalCost += distances[i];
            }
        }

        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Node " + i + ": " + distances[i]);
        }

        return totalCost;
    }

    public void dfs(int startNode) {
        //DFS Traversal
        if (startNode < 0 || startNode >= nodes) {
            System.out.println("Invalid start node. Please enter a valid node between 0 and " + (nodes - 1));
            return;
        }
        boolean[] visited = new boolean[nodes];
        System.out.println("DFS Traversal");
        dfsHelper(startNode, visited);
        System.out.println();
    }

    private void dfsHelper(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adjacencyList.get(node)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void bfs(int startNode) {
        //BFS Traversal
        if (startNode < 0 || startNode >= nodes) {
            System.out.println("Invalid start node. Please enter a valid node between 0 and " + (nodes - 1));
            return;
        }

        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;

        System.out.println("BFS Traversal");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void findMST() {
        // Implement MST algorithms (Prim's Algorithm)
        boolean[] visited = new boolean[nodes];
        int[] key = new int[nodes];
        int[] parent = new int[nodes];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0; // Start from node 0
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> key[node]));
        pq.add(0);

        while (!pq.isEmpty()) {
            int current = pq.poll();
            visited[current] = true;

            for (int neighbor : adjacencyList.get(current)) {
                int weight = adjacencyMatrix[current][neighbor];
                if (!visited[neighbor] && weight < key[neighbor]) {
                    key[neighbor] = weight;
                    parent[neighbor] = current;
                    pq.add(neighbor);
                }
            }
        }

        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < nodes; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + " (Weight: " + adjacencyMatrix[parent[i]][i] + ")");
            }
        }
    }

    /*
     * Cost analyses
     */

    //Dijsktra cost implemented in @runDijkstra

    //BFS cost
    public int bfsCost(int source, int target){
        boolean[] visited = new boolean[nodes];
        int[] distances = new int[nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while(!queue.isEmpty()){
            int current = queue.poll();

            for(int neighbor : adjacencyList.get(current)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    distances[neighbor] = distances[current] + 1;
                    queue.add(neighbor);
                }
            }
        }

        return distances[target];
    }

    //DFS cost (estimate)
    public int dfsCost(int source, int target){
        boolean[] visited = new boolean[nodes];
        int[] distances = new int[nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        dfsCostHelper(source, visited, distances);
        return distances[target];
    }

    private void dfsCostHelper(int current, boolean[] visited, int[] distances){
        visited[current] = true;

        for(int neighbor : adjacencyList.get(current)){
            if(!visited[neighbor] && distances[neighbor] > distances[current] + 1){
                distances[neighbor] = distances[current] + 1;
                dfsCostHelper(neighbor, visited, distances);
            }
        }
    }

    //MST total cost
    public int findMSTCost(){
        int totalCost = 0;
        boolean[] visited = new boolean[nodes];
        int[] key = new int[nodes];
        int[] parent = new int[nodes];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        key[0] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> key[node]));
        pq.add(0);

        while(!pq.isEmpty()){
            int current = pq.poll();
            visited[current] = true;

            for(int neighbor : adjacencyList.get(current)){
                int weight = adjacencyMatrix[current][neighbor];
                if(!visited[neighbor] && weight < key[neighbor]){
                    key[neighbor] = weight;
                    parent[neighbor] = current;
                    pq.add(neighbor);
                }
            }
        }

        //Calculate total cost of MST
        for(int i = 1; i < nodes; i++){
            if(parent[i] != -1){
                totalCost += adjacencyMatrix[parent[i]][i];
            }
        }
        
    return totalCost;
    }
    
}

