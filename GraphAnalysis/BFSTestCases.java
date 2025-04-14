/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: BFSTestCases.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * The `BFSTestCases` class evaluates the functionality of the BFS algorithm 
 * by executing test cases on various graph structures:
 * - Small directed graphs
 * - Sparse graphs
 * - Complete graphs
 * - Disconnected graphs
 * - Cyclic graphs
 * 
 * Features:
 * - Menu-driven interface for selecting test cases.
 * - Verifies BFS traversal results for different graph configurations.
 * 
 * Usage:
 * - Instantiate the class and call `runTestsMenu()` to execute test cases interactively.
 * 
 * Dependencies:
 * - Requires the `Graph` class with a BFS implementation.
 */

package GraphAnalysis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BFSTestCases {
    
    public void runTestsMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("BFS Test Cases Menu:");
            System.out.println("1. Small Directed Graph");
            System.out.println("2. Sparse Graph");
            System.out.println("3. Complete Graph");
            System.out.println("4. Disconnected Graph");
            System.out.println("5. Cyclic Graph");
            System.out.println("6. Exit Test Cases");
            System.out.print("Enter your choice: ");

            int choice = getValidatedInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    runSmallDirectedGraph();
                    break;
                case 2:
                    runSparseGraph();
                    break;
                case 3:
                    runCompleteGraph();
                    break;
                case 4:
                    runDisconnectedGraph();
                    break;
                case 5:
                    runCyclicGraph();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting BFS Test Cases.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Test Case 1: Small Directed Graph
    private void runSmallDirectedGraph() {
        System.out.println("Test Case 1: Small Directed Graph (BFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 7);
        graph.bfs(0);
    }

    // Test Case 2: Sparse Graph
    private void runSparseGraph() {
        System.out.println("Test Case 2: Sparse Graph (BFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 6);
        graph.addEdge(2, 3, 3);
        graph.bfs(0);
    }

    // Test Case 3: Complete Graph
    private void runCompleteGraph() {
        System.out.println("Test Case 3: Complete Graph (BFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 6);
        graph.bfs(0);
    }

    // Test Case 4: Disconnected Graph
    private void runDisconnectedGraph() {
        System.out.println("Test Case 4: Disconnected Graph (BFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(2, 3, 4);
        graph.bfs(0);
    }

    // Test Case 5: Cyclic Graph
    private void runCyclicGraph() {
        System.out.println("Test Case 5: Cyclic Graph (BFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 1);
        graph.bfs(0);
    }

    private static int getValidatedInput(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);

            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
