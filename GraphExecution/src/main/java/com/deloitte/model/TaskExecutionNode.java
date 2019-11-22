/**
 * 
 */
package com.deloitte.model;

/**
 * @author vbejjanki
 *
 */
public class TaskExecutionNode extends Node<Task> {
	
	public TaskExecutionNode(Task task) {
		setTask(task);
	}
	
	public void setNodeId(long value) {
		nodeId = value;
	}

	public long getNodeId() {
		return nodeId;
	}

	public void setTask(Task task) {
		value = task;
	}

	public Task getTask() {
		return value;
	}

}
