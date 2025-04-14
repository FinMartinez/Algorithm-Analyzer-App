
/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: Driver.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * This program serves as the main driver for a Graph Theory analysis tool. It 
 * provides a menu-driven interface for users to:
 * - Load graph data manually or from a file.
 * - Display graph representations (adjacency matrix and list).
 * - Execute graph algorithms such as Dijkstra's, BFS, DFS, and MST.
 * - Utilize AI/ML-based recommendations for search or algorithm choices.
 * - Run and evaluate test cases for algorithms and AI recommendations.
 * 
 * Features:
 * - Interactive main menu for user-friendly operations.
 * - Graph data loading and validation.
 * - Integration of AI for recommending graph algorithms.
 * - Comprehensive error handling and input validation.
 * 
 * Note:
 * Ensure all dependencies such as Graph, GraphLoader, AIAnalyzer, and test case classes 
 * (DijkstraTestCases, BFSTestCases, DFSTestCases, MSTTestCases, AIAnalyzerTest) are implemented 
 * and correctly imported for seamless execution.
 */

package GraphAnalysis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args) {

        Graph graph = null; // My Graph Class (adjacency matrix)
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            //Main Menu
            System.out.println("Graph Theory Analysis - Main Menu");
            System.out.println("1. Load Graph Data"); //Manual or by file?
            System.out.println("2. Display Graph Data");
            System.out.println("3. Run Dijkstra's Algorithm");
            System.out.println("4. Run BFS Algorithm");
            System.out.println("5. Run DFS Algorithm");
            System.out.println("6. Find Minimum Spanning Tree (MST)");
            System.out.println("7. Search Recommendation (AI)");
            System.out.println("8. Exit");

            //Test Case
            System.out.println("Test Cases:");
            System.out.println("9. Run Test");
            //End Test Case

            int choice = getValidatedInput(scanner, "Enter your choice: ");
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Load Graph Data
                    System.out.println("Chose input mode (file/manual): ");
                    String inputMode = scanner.nextLine().toLowerCase();
                    
                    if (inputMode.equals("file")) {
                        System.out.println("Enter the file name: ");
                        String fileName = scanner.nextLine();
                        graph = GraphLoader.loadGraphFromFile(fileName);
                    } else if (inputMode.equals("manual")) {
                        graph = GraphLoader.loadGraphManually(scanner);
                    } else {
                        System.out.println("Invalid input mode. Please enter a valid input mode.");
                    }
                    System.out.println("Graph data loaded successfully.");
                    break;
                case 2:
                    // Display adjacency matrix and list
                    if (graph != null) {
                        graph.displayAdjencenyMatrix();
                        graph.displayAdjencenyList();
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 3:
                    // Run Dijkstra's Algorithm
                    if (graph != null) {
                        int source = getValidatedInput(scanner, "Enter the source node: ");
                        graph.runDijkstra(source);
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 4: 
                    // Run BFS Algorithm
                    if (graph != null) {
                        int source = getValidatedInput(scanner, "Enter the source node: ");
                        graph.bfs(source);
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 5:
                    // Run DFS Algorithm
                    if (graph != null) {
                        int source = getValidatedInput(scanner, "Enter the source node: ");
                        graph.dfs(source);
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 6:
                    // Find MST
                    if (graph != null) {
                        graph.findMST(); // Method for MST (Prim's algorithm)
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 7:
                    // Search Recommenation
                    if (graph != null) {
                        int sourceNode = getValidatedInput(scanner, "Enter the source node: "); 
                        int targetNode = getValidatedInput(scanner, "Enter the target node: ");

                        AIAnalyzer aiAnalyzer = new AIAnalyzer(graph, sourceNode, targetNode);
                        aiAnalyzer.analyze();
                    } else {
                        System.out.println("No graph data loaded. Please load graph data first.");
                    }
                    break;
                case 8:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                case 9:
                    System.out.println("Select Algorithm to test:");
                    System.out.println("1. Dijkstra's Algorithm");
                    System.out.println("2. BFS");
                    System.out.println("3. DFS");
                    System.out.println("4. MST");
                    System.out.println("5. AI Analyzer");
                    System.out.println("6. Exit Test Cases");

                    int algorithmChoice = scanner.nextInt();
                    switch (algorithmChoice) {
                        case 1:
                            // Test Dijkstra's Algorithm
                            DijkstraTestCases dijkstraTestCases = new DijkstraTestCases();
                            dijkstraTestCases.runTestsMenu(scanner);
                            break;
                        case 2:
                            // Test BFS Algorithm
                            BFSTestCases bfsTestCases = new BFSTestCases();
                            bfsTestCases.runTestsMenu(scanner);
                            break;
                        case 3:
                            // Test DFS Algorithm
                            DFSTestCases dfsTestCases = new DFSTestCases();
                            dfsTestCases.runTestsMenu(scanner);
                            break;
                        case 4:
                            // Test MST Algorithm
                            MSTTestCases mstTestCases = new MSTTestCases();
                            mstTestCases.runTestsMenu(scanner);
                            break;
                        case 5:
                            // Test AI Analyzer
                            AIAnalyzerTest aiAnalyzerTestCases = new AIAnalyzerTest();
                            aiAnalyzerTestCases.runTests();
                            break;
                        case 6:
                            System.out.println("Exiting Test Cases...");
                            break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid choice.");
                    }
                    break;

            }
        }
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
