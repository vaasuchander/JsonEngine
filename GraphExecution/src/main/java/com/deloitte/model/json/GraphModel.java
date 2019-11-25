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
public class GraphModel {

	private Set<Node> nodes = new HashSet<>();

	private List<Edge> edges = new ArrayList<>();

	private Node rootNode;

	private Node antiRootNode;

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public Node getAntiRootNode() {
		return antiRootNode;
	}

	public void setAntiRootNode(Node antiRootNode) {
		this.antiRootNode = antiRootNode;
	}

}
