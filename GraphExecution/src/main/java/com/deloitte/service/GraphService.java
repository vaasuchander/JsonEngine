/**
 * 
 */
package com.deloitte.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.deloitte.model.Edge;
import com.deloitte.model.NoActionNode;
import com.deloitte.model.Node;
import com.deloitte.model.Task;
import com.deloitte.model.TaskExecutionNode;
import com.deloitte.model.json.GraphModel;

/**
 * @author vbejjanki
 *
 */

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GraphService {

	public GraphModel buildGraph(List<Task> tasksList) {

		List<Object> entryPointCollection = new ArrayList<>();
		List<Object> exitPointCollection = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		Set<Node> nodes = new HashSet<>();

		GraphModel graph = new GraphModel();
		Node<Void> rootNode = new NoActionNode();
		Node<Void> antiRootNode = new NoActionNode();
		Node<Task> taskExecutionNode = null;
		Node<Void> noActionNode = null;
		Node entryPointNode = null;
		//TODO Need to change the exitPointNode value
		Node exitPointNode = new NoActionNode();
		Edge edge = null;

		for (Task task : tasksList) {
			taskExecutionNode = new TaskExecutionNode(task);
			if (startToStartFilter(task)) {
				noActionNode = new NoActionNode();
				entryPointNode = noActionNode;
				nodes.add(noActionNode);
				nodes.add(entryPointNode);
				edge = new Edge(noActionNode, entryPointNode);
				edges.add(edge);
			} else {
				entryPointNode = taskExecutionNode;
				entryPointCollection.add(task);
				entryPointCollection.add(taskExecutionNode);
			}
			entryPointCollection.add(entryPointNode);
			// TODO Exit point Node Missing in Algorithm.
			exitPointCollection.add(exitPointNode);
			if (isDependentFilter(task)) {
				nodes.add(rootNode);
				nodes.add(entryPointNode);
				edge = new Edge(rootNode, entryPointNode);
				edges.add(edge);
			}
			if (isIndependentFilter(task)) {
				// TODO Exit point Node Missing in Algorithm.
				nodes.add(exitPointNode);
				nodes.add(antiRootNode);
				edge = new Edge(exitPointNode, antiRootNode);
				edges.add(edge);
			}
		}

		List<Task> predTaskList = tasksList.stream()
				.filter(task -> Objects.nonNull(task.getPredecessorConstraint()))
				.collect(Collectors.toList());
		Map<Long, Task> tasksMap = tasksList.stream().filter(Objects::nonNull)
				.collect(Collectors.toMap(Task::getId, Function.identity(),(t1,t2) -> t1));

		Task task1 = null;
		Task task2 = null;
		Node<Task> node1 = null;
		Node<Task> node2 = null;

		for (Task task : predTaskList) {
			task1 = tasksMap.get(task.getPredecessorConstraint().getId());
			task2 = task;
			node1 = getExitPoint(task1, edges);
			node2 = getEntryPoint(task2, edges);
			nodes.add(node1);
			nodes.add(node2);
			edge = new Edge(node1, node2);
			edges.add(edge);
		}

		graph.setRootNode(rootNode);
		graph.setAntiRootNode(antiRootNode);
		graph.setNodes(nodes);
		graph.setEdges(edges);

		return graph;
	}

	private Node getExitPoint(Task task, List<Edge> edges) {
		Node node = null;
		for (Edge edge : edges) {
			if (Objects.nonNull(edge.getFrom())) {
				long fromId = ((TaskExecutionNode) edge.getFrom()).getNodeId();
				if (fromId == task.getId()) {
					node = edge.getTo();
				}
			}
		}
		return node;
	}

	private Node getEntryPoint(Task task, List<Edge> edges) {
		Node node = null;
		for (Edge edge : edges) {
			if (Objects.nonNull(edge.getTo())) {
				long toId = ((TaskExecutionNode) edge.getFrom()).getNodeId();
				if (toId == task.getId()) {
					node = edge.getFrom();
				}
			}
		}
		return node;
	}

	private boolean startToStartFilter(Task task) {
		
		Predicate<Task> nullPredfilter = t -> Objects.nonNull(t.getPredecessorConstraint());
		Predicate<Task> filter = t -> "start-to-start"
				.equals(t.getPredecessorConstraint().getPredecessorConstraintType().getName());
		return nullPredfilter.and(filter).test(task);
	}

	private boolean isDependentFilter(Task task) {
		Predicate<Task> hasParent = t -> t.isParent();
		Predicate<Task> hasConstarintDateTime = t -> t.getConstraintDateTime() != null;
		Predicate<Task> hasPredecessor = t -> t.getPredecessorConstraint() != null;
		return hasParent.negate().and(hasConstarintDateTime.negate()).and(hasPredecessor.negate()).test(task);
	}

	private boolean isIndependentFilter(Task task) {
		// TODO Independent Filter need to be Changed
		Predicate<Task> isIndependent = t -> t.isParent();
		return isIndependent.test(task);
	}
}
