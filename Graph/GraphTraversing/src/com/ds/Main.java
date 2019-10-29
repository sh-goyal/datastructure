package com.ds;

import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Graph<String> graph = createGraph();

		Set<String> bfsTraversalTree = graph.breadthFirstTraversal(graph, "Bob");

		System.out.println("BFS ->");
		printSet(bfsTraversalTree);

		System.out.println();

		Set<String> dfsTraversalTree = graph.depthFirstTraversal(graph, "Bob");
		System.out.println("DFS ->");
		printSet(dfsTraversalTree);
	}

	private static Graph<String> createGraph() {
		Graph<String> graph = new Graph<>();
		graph.addVertex("Bob");
		graph.addVertex("Alice");
		graph.addVertex("Mark");
		graph.addVertex("Rob");
		graph.addVertex("Maria");
		graph.addEdge("Bob", "Alice");
		graph.addEdge("Bob", "Rob");
		graph.addEdge("Alice", "Mark");
		graph.addEdge("Rob", "Mark");
		graph.addEdge("Alice", "Maria");
		graph.addEdge("Rob", "Maria");

		return graph;
	}

	private static <T> void printSet(Set<T> list) {
		for (T t : list) {
			System.out.println(t.toString());
		}
	}

}
