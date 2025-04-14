/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: AIAnalyzerTest.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * The `AIAnalyzerTest` class validates the functionality of the `AIAnalyzer` class 
 * by running predefined test cases on various graph structures, including:
 * - Small directed graphs
 * - Sparse graphs
 * - Complete graphs
 * - Disconnected graphs
 * - Cyclic graphs
 * 
 * Features:
 * - Five test cases representing different graph scenarios.
 * - Verifies the recommendation and analysis results of `AIAnalyzer`.
 * 
 * Usage:
 * - Instantiate the class and call `runTests()` to execute all test cases.
 * 
 * Dependencies:
 * - Requires the `Graph` and `AIAnalyzer` classes.
 */

package GraphAnalysis;

public class AIAnalyzerTest {
    
    public void runTests(){
        System.out.println("Running AIAnalyzer Tests...");
        
        // Test Case 1: Small Directed Graph
        System.out.println("\nTest Case 1: Small Directed Graph");
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1, 4);
        graph1.addEdge(0, 2, 2);
        graph1.addEdge(1, 3, 1);
        graph1.addEdge(2, 3, 7);
        AIAnalyzer analyzer1 = new AIAnalyzer(graph1, 0, 3);
        analyzer1.analyze();

        // Test Case 2: Sparse Graph
        System.out.println("\nTest Case 2: Sparse Graph");
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1, 6);
        graph2.addEdge(2, 3, 3);
        AIAnalyzer analyzer2 = new AIAnalyzer(graph2, 0, 3);
        analyzer2.analyze();

        // Test Case 3: Complete Graph
        System.out.println("\nTest Case 3: Complete Graph");
        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1, 1);
        graph3.addEdge(0, 2, 3);
        graph3.addEdge(0, 3, 5);
        graph3.addEdge(1, 2, 2);
        graph3.addEdge(1, 3, 4);
        graph3.addEdge(2, 3, 6);
        AIAnalyzer analyzer3 = new AIAnalyzer(graph3, 0, 3);
        analyzer3.analyze();

        // Test Case 4: Disconnected Graph
        System.out.println("\nTest Case 4: Disconnected Graph");
        Graph graph4 = new Graph(4);
        graph4.addEdge(0, 1, 2);
        graph4.addEdge(2, 3, 4);
        AIAnalyzer analyzer4 = new AIAnalyzer(graph4, 0, 3);
        analyzer4.analyze();

        // Test Case 5: Cyclic Graph
        System.out.println("\nTest Case 5: Cyclic Graph");
        Graph graph5 = new Graph(4);
        graph5.addEdge(0, 1, 1);
        graph5.addEdge(1, 2, 2);
        graph5.addEdge(2, 3, 3);
        graph5.addEdge(3, 0, 1);
        AIAnalyzer analyzer5 = new AIAnalyzer(graph5, 0, 3);
        analyzer5.analyze();
    }   
}
