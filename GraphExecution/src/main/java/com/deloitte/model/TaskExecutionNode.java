/**
 * 
 */
package com.deloitte.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author vbejjanki
 *
 */
public class TaskExecutionNode implements Node {

	private long nodeId;
	
	private long taskId;

	private String taskName;

	private long executionId;

	private LocalDateTime startTime;
	
	private boolean isReadyToRun;
	
	private String nodeType;
	
	private List<Node> childrenNodesWithOrder;
	
	private boolean hasParent;
	
	private List<Node> parentNodes;
	
	private boolean hasChildren;
	
	private boolean hasTimeConstarint;
	
	private List<String> constraints;
	
	private String executionType;
	
	private boolean isManual;
	
	private String executorInfo;
	
	private String executionStatus;
	
	private CompletableFuture<Task> future;
	
	public TaskExecutionNode(long nodeId,long taskId,long executionId) {
		this.nodeId = nodeId;
		this.taskId = taskId;
		this.executionId = executionId;
	}
	
	@Override
	public long getExecutionId() {
		return executionId;
	}

	public void setExecutionId(long executionId) {
		this.executionId = executionId;
	}

	@Override
	public boolean isReadyToRun() {
		return isReadyToRun;
	}

	@Override
	public String getNodeType() {
		return nodeType;
	}

	@Override
	public List<Node> getChildrenNodesWithOrder() {
		return childrenNodesWithOrder;
	}

	@Override
	public boolean hasParent() {
		return hasParent;
	}

	@Override
	public List<Node> getParentNodes() {
		return parentNodes;
	}

	@Override
	public boolean hasChildren() {
		return hasChildren;
	}

	@Override
	public boolean hasTimeConstraints() {
		return hasTimeConstarint;
	}

	@Override
	public List<String> getConstraints() {
		return constraints;
	}

	@Override
	public String getExecutionType() {
		return executionType;
	}

	@Override
	public boolean isManualJob() {
		return isManual;
	}

	@Override
	public String getExecutorInfo() {
		return executorInfo;
	}

	@Override
	public void run() {

	}

	@Override
	public String getExecutionStatus() {
		return executionStatus;
	}

	@Override
	public CompletableFuture<Task> getFuture() {
		return future;
	}

	@Override
	public long getNodeId() {
		return nodeId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDateTime getStartTime() {
		
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
	}
}