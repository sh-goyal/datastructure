package com.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<T> {

	private Map<Vertex<T>, List<Vertex<T>>> adjacencyVertices;

	public Graph() {
		if (adjacencyVertices == null)
			adjacencyVertices = new HashMap<Vertex<T>, List<Vertex<T>>>();
	}

	void addVertex(T label) {
		adjacencyVertices.putIfAbsent(new Vertex<T>(label), new ArrayList<>());
	}

	void removeVertex(T label) {
		Vertex<T> v = new Vertex<>(label);
		adjacencyVertices.values().stream().forEach(e -> e.remove(v));
		adjacencyVertices.remove(new Vertex<T>(label));
	}

	void addEdge(T label1, T label2) {
		Vertex<T> v1 = adjacencyVertices.keySet().stream().filter(v -> v.label.equals(label1)).findFirst().get();
		Vertex<T> v2 = adjacencyVertices.keySet().stream().filter(v -> v.label.equals(label2)).findFirst().get();

		adjacencyVertices.get(v1).add(v2);
		adjacencyVertices.get(v2).add(v1);
	}

	void removeEdge(T label1, T label2) {
		Vertex<T> v1 = new Vertex<>(label1);
		Vertex<T> v2 = new Vertex<>(label2);

		List<Vertex<T>> eV1 = adjacencyVertices.get(v1);
		List<Vertex<T>> eV2 = adjacencyVertices.get(v2);

		if (eV1 != null)
			eV1.remove(v2);
		if (eV2 != null)
			eV2.remove(v1);
	}

	List<Vertex<T>> getAdjacencyVertices(T label) {
		return adjacencyVertices
				.get(adjacencyVertices.keySet().stream().filter(v -> v.label.equals(label)).findFirst().get());
	}

	public Set<T> breadthFirstTraversal(Graph<T> graph, T root) {
		Queue<T> queue = new LinkedList<>();
		Set<T> visited = new LinkedHashSet<>();

		queue.add(root);
		visited.add(root);

		while (!queue.isEmpty()) {
			T vertex = queue.poll();

			for (Vertex<T> v : graph.getAdjacencyVertices(vertex)) {
				if (!visited.contains(v.label)) {
					visited.add(v.label);
					queue.add(v.label);
				}
			}
		}
		return visited;
	}

	public Set<T> depthFirstTraversal(Graph<T> graph, T root) {
		Stack<T> stack = new Stack<>();
		Set<T> visited = new LinkedHashSet<>();

		stack.push(root);

		while (!stack.isEmpty()) {
			T vertex = stack.pop();

			if (!visited.contains(vertex)) {
				visited.add(vertex);

				for (Vertex<T> v : graph.getAdjacencyVertices(vertex)) {
					stack.push(v.label);
				}
			}
		}
		return visited;
	}

}
