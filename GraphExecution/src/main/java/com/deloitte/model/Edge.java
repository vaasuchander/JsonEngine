/**
 * 
 */
package com.deloitte.model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author vbejjanki
 *
 */

public class Edge implements Comparable<Edge> {

	private Node fromNode;

	private Comparator<Node> nodesOrder = Comparator.comparing(Node::getStartTime,
			Comparator.nullsLast(LocalDateTime::compareTo));

	private Set<Node> toNodes = new TreeSet<>(nodesOrder);

	public Edge(Node fromNode, Node toNode) {
		this.fromNode = fromNode;
		this.getToNodes().add(toNode);
	}

	public Node getFromNode() {
		return fromNode;
	}

	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	public Set<Node> getToNodes() {
		return toNodes;
	}

	@Override
	public int compareTo(Edge edge) {
		int val = 0;

		if (Objects.nonNull(this.getFromNode()) && Objects.nonNull(this.getFromNode().getStartTime())
				&& Objects.nonNull(edge) && Objects.nonNull(edge.getFromNode())
				&& Objects.nonNull(edge.getFromNode().getStartTime())) {
			return this.fromNode.getStartTime().compareTo(edge.getFromNode().getStartTime());
		}

		return val;
	}

}
