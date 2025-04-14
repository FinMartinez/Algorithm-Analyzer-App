/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: MSTTestCases.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * The `MSTTestCases` class validates the implementation of Minimum Spanning Tree (MST) 
 * algorithms on various graph types:
 * - Small directed graphs
 * - Sparse graphs
 * - Complete graphs
 * - Disconnected graphs
 * - Cyclic graphs
 * 
 * Features:
 * - Menu-driven interface for running test cases interactively.
 * - Validates MST generation and cost calculation.
 * 
 * Usage:
 * - Instantiate the class and call `runTestsMenu()` to execute test cases interactively.
 * 
 * Dependencies:
 * - Requires the `Graph` class with MST implementation (e.g., Prim's Algorithm).
 */

package GraphAnalysis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MSTTestCases {
    
    public void runTestsMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("MST Test Cases Menu:");
            System.out.println("1. Small Directed Graph");
            System.out.println("2. Sparse Graph");
            System.out.println("3. Complete Graph");
            System.out.println("4. Disconnected Graph");
            System.out.println("5. Cyclic Graph");
            System.out.println("6. Exit Test Cases");

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
                    System.out.println("Exiting MST Test Cases.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Test Case 1: Small Directed Graph
    private void runSmallDirectedGraph() {
        System.out.println("Test Case 1: Small Directed Graph (MST)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 7);
        graph.findMST();
    }

    // Test Case 2: Sparse Graph
    private void runSparseGraph() {
        System.out.println("Test Case 2: Sparse Graph (MST)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 6);
        graph.addEdge(2, 3, 3);
        graph.findMST();
    }

    // Test Case 3: Complete Graph
    private void runCompleteGraph() {
        System.out.println("Test Case 3: Complete Graph (MST)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 6);
        graph.findMST();
    }

    // Test Case 4: Disconnected Graph
    private void runDisconnectedGraph() {
        System.out.println("Test Case 4: Disconnected Graph (MST)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(2, 3, 4);
        graph.findMST();
    }

    // Test Case 5: Cyclic Graph
    private void runCyclicGraph() {
        System.out.println("Test Case 5: Cyclic Graph (MST)");
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 1);
        graph.findMST();
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
