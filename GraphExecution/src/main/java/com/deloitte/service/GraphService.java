/**
 * 
 */
package com.deloitte.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.deloitte.model.Edge;
import com.deloitte.model.NoActionNode;
import com.deloitte.model.Node;
import com.deloitte.model.PlayBook;
import com.deloitte.model.Task;
import com.deloitte.model.TaskExecutionNode;
import com.deloitte.model.json.GraphModel;

/**
 * @author vbejjanki
 *
 */

@Service
public class GraphService {

	@Inject
	private CacheService cService;

	private AtomicLong seq = new AtomicLong(1);

	public GraphModel buildGraph(PlayBook playBook) {

		List<Task> tasksList = playBook.getTasks();
		cService.setPlayBookTasks(playBook);
		List<Object> entryPointCollection = new ArrayList<>();
		List<Object> exitPointCollection = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		Set<Node> nodes = new HashSet<>();

		GraphModel graph = new GraphModel();
		Node rootNode = new NoActionNode(getLong(),  getLong());
		Node antiRootNode = new NoActionNode(getLong(), getLong());
		Node taskExecutionNode = null;
		Node noActionNode = null;
		Node entryPointNode = null;
		Node exitPointNode = new NoActionNode(getLong(), getLong());
		Edge edge = null;

		for (Task task : tasksList) {
			taskExecutionNode = new TaskExecutionNode(getLong(), task.getId(), getLong());
			nodes.add(taskExecutionNode);
			if (startToStartFilter(task)) {
				noActionNode = new NoActionNode(getLong(), getLong());
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
			exitPointCollection.add(exitPointNode);
			if (isDependentFilter(task)) {
				nodes.add(rootNode);
				nodes.add(entryPointNode);
				edge = new Edge(rootNode, entryPointNode);
				edges.add(edge);
			}
			if (isIndependentFilter(task)) {
				nodes.add(exitPointNode);
				nodes.add(antiRootNode);
				edge = new Edge(exitPointNode, antiRootNode);
				edges.add(edge);
			}
		}

		List<Task> predTaskList = tasksList.stream().filter(task -> Objects.nonNull(task.getPredecessorConstraint()))
				.collect(Collectors.toList());
		Map<Long, Task> tasksMap = cService.getMapTaskList(playBook.getId());

		Task task1 = null;
		Task task2 = null;
		Node node1 = null;
		Node node2 = null;

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
		Collections.sort(edges);
		graph.setEdges(edges);
		return graph;
	}

	private Node getExitPoint(Task task, List<Edge> edges) {
		Node node = null;
		Edge ed = null;
		for (Edge edge : edges) {
			if (Objects.nonNull(edge.getFromNode())) {
				long fromId = edge.getFromNode().getTaskId();
				ed = edge;
				if (fromId == task.getId()) {
					node = edge.getToNodes().stream().findAny().get();
				}
			}
		}
		if(Objects.isNull(node)) {
			node = ed.getToNodes().stream().findAny().get();
		}
		return node;
	}

	private Node getEntryPoint(Task task, List<Edge> edges) {
		Node node = null;
		for (Edge edge : edges) {
			if (Objects.nonNull(edge.getToNodes())) {
				for (Node eNode : edge.getToNodes()) {
					long toId = eNode.getTaskId();
					if (toId == task.getId()) {
						node = edge.getFromNode();
					}
				}
			}
		}
		if(Objects.isNull(node)) {
			node = new NoActionNode(getLong(), getLong());
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
		Predicate<Task> isIndependent = t -> Objects.nonNull(t.getConstraintDateTime());
		return isIndependent.test(task);
	}

	private long getLong() {

		return seq.getAndIncrement();
	}
}
