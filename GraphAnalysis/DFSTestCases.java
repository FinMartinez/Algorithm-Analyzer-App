/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: DFSTestCases.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * The `DFSTestCases` class evaluates the functionality of the DFS algorithm 
 * by running test cases on different graph types:
 * - Small directed graphs
 * - Sparse graphs
 * - Complete graphs
 * - Disconnected graphs
 * - Cyclic graphs
 * 
 * Features:
 * - Menu-driven interface for interactive testing.
 * - Allows users to specify the starting node for DFS traversal.
 * 
 * Usage:
 * - Instantiate the class and call `runTestsMenu()` to select and run test cases.
 * 
 * Dependencies:
 * - Requires the `Graph` class with a DFS implementation.
 */

package GraphAnalysis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DFSTestCases {
    
    public void runTestsMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("DFS Test Cases Menu:");
            System.out.println("1. Small Directed Graph");
            System.out.println("2. Sparse Graph");
            System.out.println("3. Complete Graph");
            System.out.println("4. Disconnected Graph");
            System.out.println("5. Cyclic Graph");
            System.out.println("6. Exit Test Cases");

            int choice = getValidatedInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    runSmallDirectedGraph(scanner);
                    break;
                case 2:
                    runSparseGraph(scanner);
                    break;
                case 3:
                    runCompleteGraph(scanner);
                    break;
                case 4:
                    runDisconnectedGraph(scanner);
                    break;
                case 5:
                    runCyclicGraph(scanner);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting DFS Test Cases.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Test Case 1: Small Directed Graph
    private void runSmallDirectedGraph(Scanner scanner) {
        System.out.println("Test Case 1: Small Directed Graph (DFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 7);

        System.out.print("Enter the starting node: ");
        int startNode = scanner.nextInt();

        graph.dfs(startNode);
    }

    // Test Case 2: Sparse Graph
    private void runSparseGraph(Scanner scanner) {
        System.out.println("Test Case 2: Sparse Graph (DFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 6);
        graph.addEdge(2, 3, 3);

        System.out.print("Enter the starting node: ");
        int startNode = scanner.nextInt();

        graph.dfs(startNode);
    }

    // Test Case 3: Complete Graph
    private void runCompleteGraph(Scanner scanner) {
        System.out.println("Test Case 3: Complete Graph (DFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 6);

        System.out.print("Enter the starting node: ");
        int startNode = scanner.nextInt();

        graph.dfs(startNode);
    }

    // Test Case 4: Disconnected Graph
    private void runDisconnectedGraph(Scanner scanner) {
        System.out.println("Test Case 4: Disconnected Graph (DFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(2, 3, 4);

        System.out.print("Enter the starting node: ");
        int startNode = scanner.nextInt();

        graph.bfs(startNode);
    }

    // Test Case 5: Cyclic Graph
    private void runCyclicGraph(Scanner scanner) {
        System.out.println("Test Case 5: Cyclic Graph (DFS)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 1);

        System.out.print("Enter the starting node: ");
        int startNode = scanner.nextInt();

        graph.dfs(startNode);
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
