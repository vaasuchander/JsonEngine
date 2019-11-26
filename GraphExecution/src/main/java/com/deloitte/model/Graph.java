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

	private Map<Node, List<Node>> toVertex = new HashMap<>();

	private Map<Node, List<Node>> fromVertex = new HashMap<>();

	public void addEdge(Node to, Node from) {
		toVertex.get(to).add(from);
		fromVertex.get(from).add(to);
	}

	public void addNode(Node value) {
		toVertex.putIfAbsent(value, new ArrayList<>());
		fromVertex.putIfAbsent(value, new ArrayList<>());
	}

	public Node getEntryNode(Node node) {
		return toVertex.get(node).get(0);
	}

	public Node getExitNode(Node node) {
		return fromVertex.get(node).get(0);
	}

}
