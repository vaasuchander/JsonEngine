/**
 * 
 */
package com.deloitte.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vbejjanki
 *
 */

public class Graph {

	private Map<Node, List<Node>> graph = new HashMap<>();

	public void addEdge(Node to, Node from) {
		getGraph().get(to).add(from);
	}

	public void addNode(Node value) {
		getGraph().putIfAbsent(value, new ArrayList<>());
	}

	public List<Node> getAdjNodes(Node node) {
		return graph.get(node);
	}

	public Map<Node, List<Node>> getGraph() {
		return graph;
	}

	public void setGraph(Map<Node, List<Node>> graph) {
		this.graph = graph;
	}

}
