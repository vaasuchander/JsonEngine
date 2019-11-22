/**
 * 
 */
package com.deloitte.model.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.deloitte.model.Edge;
import com.deloitte.model.Node;

/**
 * @author vbejjanki
 *
 */
@SuppressWarnings("rawtypes")
public class GraphModel<T, R> {

	private Set<Node> nodes = new HashSet<>();

	private List<Edge> edges = new ArrayList<>();

	private Node<T> rootNode;

	private Node<R> antiRootNode;

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public Node<T> getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node<T> rootNode) {
		this.rootNode = rootNode;
	}

	public Node<R> getAntiRootNode() {
		return antiRootNode;
	}

	public void setAntiRootNode(Node<R> antiRootNode) {
		this.antiRootNode = antiRootNode;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}
