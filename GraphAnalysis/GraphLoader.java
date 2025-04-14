/*
 * Project Name: Graph Theory Analysis and AI Recommendations
 * File Name: GraphLoader.java
 * Author: Fin Martinez
 * Course: Algorithms and Analysis (CS 4050)
 * Professor: Dr. Alsaffar
 * Date: 12/11/2024
 * Version: 1.0
 * 
 * Description:
 * The `GraphLoader` class provides utility methods for loading graph data into a `Graph` object. 
 * It supports two modes of input:
 * - File-based: Reads graph data from a text file containing nodes, edges, and weights.
 * - Manual: Allows users to input graph data interactively via the console.
 * 
 * Features:
 * - File Input:
 *   - Reads the number of nodes and edges from a file.
 *   - Reads edges in the format: `from`, `to`, `weight`.
 *   - Handles file errors gracefully (e.g., file not found, incorrect format).
 * - Manual Input:
 *   - Prompts the user for the number of nodes and edges.
 *   - Accepts edges interactively with user-provided `from`, `to`, and `weight` values.
 * - Integrates seamlessly with the `Graph` class for creating graph instances.
 * 
 * Usage:
 * - Use `loadGraphFromFile` to load a graph from a file by specifying the file path.
 * - Use `loadGraphManually` to input graph data directly via a `Scanner` instance.
 * 
 * Dependencies:
 * This class depends on the `Graph` class for creating and managing graph instances.
 * Ensure the input file format is correct and matches the expected structure:
 * - First line: Number of nodes.
 * - Subsequent lines: `from`, `to`, `weight` (space-separated integers).
 */

package GraphAnalysis;

import java.io.*;
import java.util.Scanner;

public class GraphLoader {
    public static Graph loadGraphFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))){
            // Read the number of nodes
            int nodes = fileScanner.nextInt();
            Graph graph = new Graph(nodes);

            // Read the edges
            while (fileScanner.hasNextInt()) {
                int from = fileScanner.nextInt();
                int to = fileScanner.nextInt();
                int weight = fileScanner.nextInt();
                graph.addEdge(from, to, weight);
            }

            System.out.println("Graph successfully loaded from file: " + fileName);
            return graph;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            System.err.println("Ensure the file is in the correct directory: " + System.getProperty("user.dir"));
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    public static Graph loadGraphManually(Scanner scanner) {
        // Get the number of nodes
        System.out.println("Enter the number of nodes for the graph: ");
        int nodes = scanner.nextInt();
        Graph graph = new Graph(nodes);

        // Get the number of edges
        System.out.println("Enter the number of edges for the graph: ");
        int edges = scanner.nextInt();

        // Get the input edges
        System.out.println("Enter edges in the format: 'from', 'to', 'weight'... ");
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter 'from' edge: ");
            int from = scanner.nextInt();

            System.out.print("Enter 'to' edge: ");
            int to = scanner.nextInt();

            System.out.print("Enter 'weight': ");
            int weight = scanner.nextInt();
            graph.addEdge(from, to, weight);
        }

        System.out.println("Graph successfully loaded manually.");
        return graph;
    }

}
